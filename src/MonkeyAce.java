import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class MonkeyAce extends Monkey {
	int radius = 100, width = 60, height = 60;
	double angle = 0, omega = 36, x, y;
	Image img;
	public MonkeyAce(int a, int b) {
		super(a,b,100,"Monkey_Ace.png",8,1,200,1);
		x = a+radius;
		y = b;
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
        	double x = this.x+BIGINT*Math.cos(i*Math.PI/4+angle);
            double y = this.y-BIGINT*Math.sin(i*Math.PI/4+angle);
            gameprojectile.get(random).launch((int) x,(int) y, panel,gameprojectile,random, this.getDamage(), al, pierce, r);
        }
    }
    @Override
    public Projectile getProj(){
        return new Dart(this.getX(),this.getY());
    }
    
    @Override
    public void draw(Graphics g, JPanel panel){
        Graphics2D g2d = (Graphics2D) g.create();
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(angle), x, y);
        at.translate(x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, -width/2, -height/2, width, height, panel);
        g2d.dispose();
        g.drawImage(getImg(), getX(), getY(), 60, 30, null);
    }
}
