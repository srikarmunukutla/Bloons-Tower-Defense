import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class MonkeyAce extends Monkey {
	int radius = 200, width = 100, height = 100, platwidth = 100, platheight = 50;
	double angle = 0, omega = 10, x, y;
	Image img;
    private final static String PATH_PREFIX = "images/";
	public MonkeyAce(int a, int b) {
		super(a,b,100,"Monkey_Ace.png",8,1,200,1,900);
		x = a+radius;
		y = b;
		img = getImage(PATH_PREFIX + "Plane.png");
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
            gameprojectile.get(random).launch((int) a,(int) b, panel,gameprojectile,random, this.getDamage(), al, pierce, r);
        }
    }
    @Override
    public Projectile getProj(){
        return new Dart((int) x, (int) y);
    }
    
    @Override
    public void draw(Graphics g, JPanel panel){
        Graphics2D g2d = (Graphics2D) g.create();
        AffineTransform at = new AffineTransform();
        at.setToRotation(-Math.toRadians(angle), x, y);
        at.translate(x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, -width/2, -height/2, width, height, panel);
        g2d.dispose();
        g.drawImage(getImg(), getX()-platwidth/2, getY()-platheight/2, platwidth, platheight, null);
    }
}
