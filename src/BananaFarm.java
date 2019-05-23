import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BananaFarm extends Monkey {
	int bananaOutput, numBananas, secsbeforereload;
	private final static int CONSTANT = 7800;
	public BananaFarm(int a, int b) {
		super(a,b,1000,"Banana_Farm.png",1,1,1,1,1000);
		bananaOutput = 20;
		numBananas = 25;
		secsbeforereload = CONSTANT/numBananas;
		width = 50;
		height = 50;
		setImgRect();
	}
    
    public int getBananaValue() {
    	return bananaOutput;
    }
    
    public int getNumBananas() {
    	return numBananas;
    }

	public void update(ArrayList<GameObject> al, Pixel[][] grid, BTDMap m, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile) {
    	if (secsbeforereload > 0) {
    		secsbeforereload--;
    		return;
    	}
    	double randomAngle = 360 * Math.random();
    	double random = (int) 200 * Math.random();
    	Banana b = new Banana(bananaOutput, randomAngle, random, getX(), getY(), getX(), getY());
    	secsbeforereload = CONSTANT/numBananas;
    	al.add(b);
    }
}
