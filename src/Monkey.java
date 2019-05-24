import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public abstract class Monkey implements GameObject{
    protected Rectangle rangerect, imgrect;
    private int x, y;
    protected int width, height;
    private Image img;
    private int range;
    private double angle = 0;
    private int damage;
    private int numtarget;
    private int reloadrate;
    int pierce;
    int secsbefreload = 0;
    private int cost;
    private final static String PATH_PREFIX = "images/";
    
    public Monkey(int a, int b, int ra, String str, int target, int dmg, int reload, int p, int co){
        x = a;
        y = b;
        img = getImage(str);
        range = ra;
        rangerect = new Rectangle(x-range, y-range, range*2, range*2);
        numtarget = target;
        damage = dmg;
        reloadrate = reload;
        pierce = p;
        cost = co;
    }
    
    protected void setRangeRect(int x, int y) {
    	rangerect = new Rectangle(x-range, y-range, range*2, range*2);
    }
    
    protected void fillRangeRect(Graphics g, boolean isValid) {
    	if(isValid) {
    		g.setColor(new Color(0, 250, 0, 100));
    	}
    	else {
    		g.setColor(new Color(255, 0, 0, 100));
    	}
    	g.fillRect(rangerect.x, rangerect.y, range*2, range*2);
    }
    
    protected void setImgRect() {
    	imgrect = new Rectangle(x-(width/2), y-(height/2), width, height);
    }
    
    public void draw(Graphics g, JPanel panel){
//    	g.drawImage(img, x, y, SQUARESIZE, SQUARESIZE, null);
//        Graphics2D g2d = (Graphics2D) g.create();
//        AffineTransform at = new AffineTransform();
//        at.setToRotation(Math.toRadians(angle), x, y);
//        at.translate(x, y);
//        g2d.setTransform(at);
//        g2d.drawImage(img, -SQUARESIZE/2, -SQUARESIZE/2,SQUARESIZE,SQUARESIZE, panel);
//        g2d.dispose();
        Graphics2D g2 = (Graphics2D) g.create();// get a copy
        g2.translate(x, y);// translate this card's (x,y)
        g2.rotate(Math.toRadians(angle));// rotate around this card
        g2.drawImage(img, -width/2, -height/2, width, height, null);// draw my image on the rotated Graphics
        g2.dispose();// dispose so the other cards are not affected.
    }
    
    public void setLoc(int x, int y) {
    	this.x = x;
    	this.y = y;
    	setRangeRect(x, y);
    	setImgRect();
    }

    public int getCost(){
        return cost;
    }
    
    public ArrayList<Bloon> getBloons(ArrayList<GameObject> al){
        ArrayList<Bloon> ret = new ArrayList<Bloon>();
        for(int i = 0; i < al.size(); i++){
            if(al.get(i) instanceof Bloon){
                ret.add((Bloon)al.get(i));
            }
        }
        return ret;
    }
    
    public ArrayList<Spikes> getSpikes(ArrayList<GameObject> al){
        ArrayList<Spikes> ret = new ArrayList<Spikes>();
        for(int i = 0; i < al.size(); i++){
            if(al.get(i) instanceof Spikes){
                ret.add((Spikes) al.get(i));
            }
        }
        return ret;
    }

    public Rectangle getRangeRect(){
        return rangerect;
    }
    
    public Rectangle getImgRect() {
    	return imgrect;
    }
    
    public void setAngle(double deg){
        angle = deg;
    }
    
    protected Image getImage(String fn) {
        Image img = null;
        fn = PATH_PREFIX + fn;
        try {
            img = ImageIO.read(this.getClass().getResource(fn));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getReloadRate(){
        return reloadrate;
    }
    
    public int getDamage(){
        return damage;
    }
    
    public PriorityQueue<Bloon> getTargets(ArrayList<Bloon> al){
        PriorityQueue<Bloon> pq = new PriorityQueue<>(new Comparator<Bloon>() {
            @Override
            public int compare(Bloon o1, Bloon o2) {
                if (o2.getDistance()>o1.getDistance()){
                    return 1;
                }else if (o2.getDistance() < o1.getDistance()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        pq.addAll(al);
        return pq;
    }
    
    public Projectile getProj(){
        return null;
    }
    
    public void update(ArrayList<GameObject> al, Pixel[][] grid, BTDMap m, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile) {
        //If no balloons, no code to run
        ArrayList<Bloon> bloonal = getBloons(al);
        ArrayList<Spikes> spikeal = getSpikes(al);
        if (bloonal.size() == 0){
            return;
        }
        if (secsbefreload > 0){
            secsbefreload--;
            return;
        }
        PriorityQueue<Bloon> pq = this.getTargets(bloonal);
        for (int i = 0; i < numtarget; i++) {
            if (pq.isEmpty()){
                break;
            }
            Bloon b = pq.remove();
            if (!b.getRect().intersects(rangerect)){
                i--;
                continue;
            }
            if (secsbefreload == 0){
                secsbefreload = getReloadRate();
            }
            if (b.getX() < x) {
                setAngle(180*Math.atan(1.0*(b.getY() - y) / (b.getX() - x))/Math.PI-90);
            } else {
                setAngle(180*Math.atan(1.0*(b.getY() - y) / (b.getX() - x))/Math.PI+90);
            }
            Projectile pr = getProj();
            int random = (int) ((Math.random()) * Integer.MAX_VALUE);
            gameprojectile.put(random, pr);
            gameprojectile.get(random).launch((int)b.getX(), (int)b.getY(), panel, gameprojectile, random, damage, al, pierce, rangerect);
        }
    }
    
    public void clickedAt(){

    }
    
    public Image getImg(){
        return img;
    }
}
