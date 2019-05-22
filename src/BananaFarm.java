import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BananaFarm extends Monkey {
	int bananaOutput, numBananas, x, y, secsbeforereload;
    private final static String PATH_PREFIX = "images/";
    String str = PATH_PREFIX + "Banana_Farm.png";
	Image img = getImage(str);
	Rectangle rect;
	private final static int CONSTANT = 7800;
	private final static int SQUARESIZE = 100;
	public BananaFarm(int a, int b) {
		super(a,b,1000,"Banana_Farm.png",1,1,1,1,1000);
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
    
    public void draw(Graphics g, JPanel panel) {
    	g.drawImage(img, x-SQUARESIZE/2, y-SQUARESIZE/2, SQUARESIZE, SQUARESIZE, null);
    }
    
    public void clickedAt() {
    	
    }
    
    public Rectangle getRect() {
    	return rect;
    }
	public Image getImg(){
		return img;
	}

	public void update(ArrayList<GameObject> al, Pixel[][] grid, BTDMap m, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile) {
    	if (secsbeforereload > 0) {
    		secsbeforereload--;
    		return;
    	}
    	double randomAngle = 360 * Math.random();
    	double random = (int) 200 * Math.random();
    	Banana b = new Banana(bananaOutput, randomAngle, random, x, y,x,y);
    	secsbeforereload = CONSTANT/numBananas;
    	al.add(b);
    }
}
