import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Banana {
	int money, radius = 20, width = 39, height = 35;
	double angle, distance, x, y, time = 0;
    private final static String PATH_PREFIX = "images/";
	Image img = getImage(PATH_PREFIX + "Banana.png");
	public Banana(int value, double angle, double distance, int a, int b) {
		money = value;
		this.angle = angle;
		this.distance = distance;
		x = a;
		y = b;
	}
	
    protected Image getImage(String fn) {
        Image img = null;
        try {
            img = ImageIO.read(this.getClass().getResource(fn));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    
    public void update(double timePassed) {
    	time += timePassed;
    	double theta = Math.PI*angle/180;
    	double a = x + distance*(1-Math.exp(time)) * Math.cos(theta);
    	double b = x - distance*(1-Math.exp(time)) * Math.sin(theta);
    	x = a;
    	y = b;
    }
    
    public void draw(Graphics g) {
    	g.drawImage(img, (int) (x-width/2), (int) (y-height/2), width, height, null);
    }

}
