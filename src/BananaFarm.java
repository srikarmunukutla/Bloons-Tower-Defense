import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class BananaFarm extends Monkey {
	private int bananaOutput, numBananas, secsbeforereload;
	private final static int CONSTANT = 7800;
	private boolean ontowerpanel;
	
	public BananaFarm(int a, int b, boolean tp) {
		super(a, b, 0, "Banana_Farm.png", 1, 1, 1, 1, 1000);
		bananaOutput = 20;
		numBananas = 25;
		secsbeforereload = CONSTANT/numBananas;
		hasrange = false;
		ontowerpanel = tp;
		if(ontowerpanel) {
			width = 50;
			height = 50;
		}
		else {
			width = 75;
			height = 75;
		}
		setImgRect();
	}
    
    public int getBananaValue() {
    	return bananaOutput;
    }
    
    public int getNumBananas() {
    	return numBananas;
    }

	public void update(ArrayList<GameObject> al, Pixel[][] grid, BTDMap m, double time, JPanel panel, HashMap<Integer, Projectile> gameprojectile) {
    	if (secsbeforereload > 0) {
    		secsbeforereload--;
    		return;
    	}
    	double randomAngle = 360 * Math.random();
    	double random = 200 * Math.random();
    	Banana b = new Banana(bananaOutput, randomAngle, random, getX(), getY(), getX(), getY());
    	secsbeforereload = CONSTANT/numBananas;
    	al.add(b);
    }

}