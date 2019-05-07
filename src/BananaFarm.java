import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BananaFarm {
	int bananaOutput, numBananas, x, y, secsbeforereload;
    private final static String PATH_PREFIX = "images/";
	Image img;
	Rectangle rect;
	private final static int CONSTANT = 7800;
	public BananaFarm(int a, int b, String imgString) {
		bananaOutput = 20;
		numBananas = 4;
		x = a;
		y = b;
		secsbeforereload = CONSTANT/numBananas;
		img = getImage(PATH_PREFIX + imgString);
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
    
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    public int getBananaValue() {
    	return bananaOutput;
    }
    
    public int getNumBananas() {
    	return numBananas;
    }
    
    public void draw(Graphics g) {
    	
    }
    
    public Rectangle getRect() {
    	return rect;
    }
    
    public void makeBananas() {
    	if (secsbeforereload > 0) {
    		secsbeforereload--;
    		return;
    	}
    	Banana b = new Banana(bananaOutput);
    }
}
