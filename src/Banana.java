import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Banana implements GameObject {
	int money, radius = 20, width = 39, height = 35;
	double angle, distance, x, y, srcx, srcy, time = 0;
    private final static String PATH_PREFIX = "images/";
    private Rectangle r;
	Image img;
	public Banana(int value, double angle, double distance, int a, int b, int c, int d) {
		money = value;
		this.angle = angle;
		this.distance = distance;
		x = a;
		y = b;
		srcx = c;
		srcy = d;
		img = getImage("Banana.png");
		r = new Rectangle((int)(x-width/2),(int)(y-height/2),width,height);
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
    private void moveRect(int aa, int bb) {
    	r.translate(aa-width/2-r.x, bb-height/2-r.y);
    }
    
    public void update(ArrayList<GameObject> al, Pixel[][] grid, BTDMap m, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile) {
		if (getDistance() > distance){
			return;
		}
    	time += time;
    	double theta = Math.PI*angle/180;
    	double a = x + distance*(1-Math.exp(time/20)) * Math.cos(theta);
    	double b = y - distance*(1-Math.exp(time/20)) * Math.sin(theta);
    	x = a;
    	y = b;
    	moveRect((int)a,(int)b);
    }
    public Rectangle getImgRect() {
    	return r;
    }
    private double getDistance(){
		return Math.sqrt(Math.pow(srcx-x,2) + Math.pow(srcy-y,2));
	}
    public void draw(Graphics g, JPanel panel) {
    	g.drawImage(img, (int) (x-width/2), (int) (y-height/2), width, height, null);
    }
    public void clickedAt(BTDMap btdm){
    	btdm.increaseMoney(money);
    	ArrayList<GameObject> al = btdm.getGameObjectsList();
    	for(int i = 0; i < al.size(); i++) {
    		if(al.get(i).equals(this)) {
    			al.remove(i);
    			return;
    		}
    		
    	}
    }


}
