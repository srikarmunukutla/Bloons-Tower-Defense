import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Banana implements GameObject {
	private int money, width, height;
	private double angle, distance, x, y, srcx, srcy, time = 0;
    private final static String PATH_PREFIX = "images/";
    private Rectangle rect;
	private Image img;
	
	public Banana(int value, double angle, double distance, int a, int b, int c, int d) {
		money = value;
		this.angle = angle;
		this.distance = distance;
		x = a;
		y = b;
		srcx = c;
		srcy = d;
		width = 39;
		height = 35;
		img = getImage("Banana.png");
		rect = new Rectangle((int) (x-width/2), (int) (y-height/2), width, height);
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
    	rect.translate(aa - (width/2) - rect.x, bb - (height/2) - rect.y);
    }
    
    public void update(ArrayList<GameObject> al, Pixel[][] grid, BTDMap m, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile) {
		if (getDistance() > distance) {
			return;
		}
    	time += time;
    	double theta = Math.toRadians(angle);
    	double a = x + distance * (1 - Math.exp(time/20)) * Math.cos(theta);
    	double b = y - distance * (1 - Math.exp(time/20)) * Math.sin(theta);
    	x = a;
    	y = b;
    	moveRect((int) a, (int) b);
    }
    
    public Rectangle getImgRect() {
    	return rect;
    }
    
    private double getDistance() {
		return Math.sqrt(Math.pow(srcx - x, 2) + Math.pow(srcy - y, 2));
	}
    
    public void draw(Graphics g, JPanel panel) {
    	g.drawImage(img, (int) (x - width/2), (int) (y - height/2), width, height, null);
    }
    
    public void clickedAt(BTDMap btdm) {
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