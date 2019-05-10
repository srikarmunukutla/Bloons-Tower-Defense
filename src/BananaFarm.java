import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BananaFarm{
	int bananaOutput, numBananas, x, y, secsbeforereload;
    private final static String PATH_PREFIX = "images/";
    String str = PATH_PREFIX + "Banana_Farm.png";
	Image img = getImage(str);
	Rectangle rect;
	private final static int CONSTANT = 7800;
	private final static int SQUARESIZE = 100;
	public BananaFarm(int a, int b) {
		bananaOutput = 20;
		numBananas = 25;
		x = a;
		y = b;
		secsbeforereload = CONSTANT/numBananas;
		rect = new Rectangle(a-SQUARESIZE/2, b-SQUARESIZE/2, SQUARESIZE, SQUARESIZE);
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
    	g.drawImage(img, x-SQUARESIZE/2, y-SQUARESIZE/2, SQUARESIZE, SQUARESIZE, null);
    }
    
    public Rectangle getRect() {
    	return rect;
    }
    
    public Banana makeBananas() {
    	if (secsbeforereload > 0) {
    		secsbeforereload--;
    		return null;
    	}
    	double randomAngle = 360 * Math.random();
    	double random = (int) 200 * Math.random();
    	Banana b = new Banana(bananaOutput, randomAngle, random, x, y,x,y);
    	secsbeforereload = CONSTANT/numBananas;
    	return b;
    }
}
