import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class MonkeyAce extends Monkey {
	private int radius, platwidth, platheight;
	private double angle = 0, omega = 10, x, y;
	private Image platform;
    
	public MonkeyAce(int a, int b) {
		super(a,b,100,"Plane.png",8,1,200,1,900);
		x = a+radius;
		y = b;
		platform = getImage("Monkey_Ace.png");
		width = 50;
		height = 50;
		platheight = 50;
		platwidth = 100;
		radius = 200;
		setImgRect();
	}
	
	@Override
    public void update(ArrayList<GameObject> al, Pixel[][] grid, BTDMap m, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile) {
		//runs regardless if there are bloons
		angle += omega * time;
		setAngle(angle);
		x = getX()+radius*Math.cos(angle*Math.PI/180);
		y = getY()-radius*Math.sin(angle*Math.PI/180);
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
    
    public void draw(Graphics g, JPanel panel, boolean tp) {
    	Graphics2D g2 = (Graphics2D) g.create();// get a copy
        g2.translate(x, y);// translate this card's (x,y)
        g2.rotate(Math.toRadians(angle));// rotate around this card
        g2.drawImage(getImg(), -width/2, -height/2, width, height, null);
        if(!tp) {
        	g2.drawImage(platform, getX()-platwidth/2, getY()-platheight/2, platwidth, platheight, null);
        }
        g2.dispose();
    }
}