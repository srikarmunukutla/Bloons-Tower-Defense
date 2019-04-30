import javafx.scene.layout.Priority;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public abstract class Monkey {
    Rectangle r;
    private int x;
    private int y;
    private final int SQUARESIZE = 50;
    private Image img;
    private int range;
    private double angle = 0;
    private int damage;
    private int numtarget;
    public Monkey(int a, int b, int ra, String str, int target, int dmg){
        x = a;
        y = b;
        img = getImage(PATH_PREFIX + str);
        range = ra;
        r = new Rectangle(a-range,b-range,range*2,range*2);
        numtarget = target;
        damage = dmg;
    }
    public void draw(Graphics g, JPanel panel){
        Graphics2D g2d = (Graphics2D) g.create();
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(angle), x, y );
        at.translate(x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, panel);
        g2d.dispose();
    }
    public Rectangle getRect(){
        return r;
    }
    public void setAngle(double deg){
        angle = deg;
    }
    private final static String PATH_PREFIX = "res/images/";
    protected Image getImage(String fn) {
        Image img = null;
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
        for (int i = 0; i < al.size(); i++){
            pq.add(al.get(0));
        }
        return pq;
    }
    public void target(ArrayList<Bloon> al, JPanel panel, HashMap<Integer,Projectile> gameprojectiles){
        //If no balloons, no code to run
        if (al.size() == 0){
            return;
        }
        PriorityQueue<Bloon> pq = this.getTargets(al);
        for (int i = 0; i < numtarget; i++) {
            if (pq.isEmpty()){
                break;
            }
            Bloon b = pq.remove();
            if (!b.getRect().intersects(r)){
                i--;
                continue;
            }
            if (b.getX() < x) {
                setAngle(180*Math.atan(1.0*(b.getY() - y) / (b.getX() - x))/Math.PI-90);
            } else {
                setAngle(180*Math.atan(1.0*(b.getY() - y) / (b.getX() - x))/Math.PI+90);
            }
            Dart dart = new Dart(getX(), getY());
            int random = (int) ((Math.random()) * Integer.MAX_VALUE);
            gameprojectiles.put(random, dart);
            gameprojectiles.get(random).launch(b, panel,gameprojectiles,random);
            for (int j = 0; j < al.size(); j++){
                if (b.equals(al.get(j))){
                    al.get(j).decreaseHealth(damage);
                    if (al.get(j).getHealth() <= 0){
                        al.remove(j);
                        j--;
                    }
                }
            }

        }
    }

}
