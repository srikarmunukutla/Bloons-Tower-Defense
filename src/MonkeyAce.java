import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class MonkeyAce extends Monkey {
	private int radius, platwidth, platheight;
	private double angle = 0, omega = 3, x, y;
	private Image platform;
	private boolean ontowerpanel;
	
	public MonkeyAce(int a, int b, boolean tp) {
		super(a,b,100,"Plane.png",8,1,200,1,900);
		x = a+radius;
		y = b;
		platform = getImage("Monkey_Ace.png");
		width = 50;
		height = 50;
		platheight = 50;
		platwidth = 100;
		radius = 200;
		hasrange = true;
		setImgRect();
		ontowerpanel = tp;
	}
	
	@Override
    public void update(ArrayList<GameObject> al, Pixel[][] grid, BTDMap m, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile) {
		//runs regardless if there are bloons
		angle += omega * time;
		setAngle(angle);
		x = getX()-radius*Math.cos(Math.toRadians(angle));
		y = getY()+radius*Math.sin(Math.toRadians(angle));
        if (secsbefreload > 0){
            secsbefreload--;
            return;
        }
        secsbefreload = getReloadRate();
        for (int i = 0; i < 8; i++) {
        	Projectile pr = getProj();
        	int random = (int) ((Math.random()) * Integer.MAX_VALUE);
        	gameprojectile.put(random, pr);
        	int BIGINT = 3780;
        	double a = x+BIGINT*Math.cos(i*Math.PI/4+angle);
            double b = y-BIGINT*Math.sin(i*Math.PI/4+angle);
            gameprojectile.get(random).launch((int) a,(int) b, panel, gameprojectile, random, this.getDamage(), al, pierce, rangerect);
        }
    }
	
    @Override
    public Projectile getProj(){
        return new Dart((int) x, (int) y);
    }
    
    public void draw(Graphics g, JPanel panel) {
    	if(!ontowerpanel) {
        	g.drawImage(platform, getX()-platwidth/2, getY()-platheight/2, platwidth, platheight, null);
        }
    	Graphics2D g2 = (Graphics2D) g.create();// get a copy
        g2.translate(x, y);// translate this card's (x,y)
        g2.rotate(Math.toRadians(-angle));// rotate around this card
        g2.drawImage(getImg(), -width/2, -height/2, width, height, null);
        g2.dispose();
    }
}