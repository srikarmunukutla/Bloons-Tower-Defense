import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class BTDMap {
	protected Pixel[][] grid;
	protected ArrayList<GameObject> gameObjects;
	protected Image img = null;
	protected int height, width;
	protected double Hratio, Wratio;
	private String PATH_PREFIX = "images/";
	public static double origH = 520, origW = 700;
	protected int level;
	
	public BTDMap(int r, int c) {
		height = r;
		width = c;
		grid = new Pixel[height][width];
		Hratio = (height/origH);
		Wratio = (width/origW);
		gameObjects = new ArrayList<GameObject>();
		level = 1;
	}
	
	protected abstract void initializeTrack();
	
	protected void createLevel() {
		int bursts = (int) (Math.random() * Math.ceil(level/5)) + 1;
//		int totalBloons = (int) (Math.random() * Math.ceil(level/5));
	}
	
	protected void initializeGrid() {
		for(int r = 0; r < height; r++) {
			for(int c = 0; c < width; c++) {
				grid[r][c] = new Pixel(0, false);
			}
		}
	}
	
	public void addBloon(Bloon b) {
		gameObjects.add(b);
	}
	
	public void removeBloon(int i) {
		if(gameObjects.get(i) instanceof Bloon) {
			gameObjects.remove(i);
		}
	}
	
	public void addSpikes(Spikes s) {
		gameObjects.add(s);
	}
	
	public void removeSpikes(int i) {
		if(gameObjects.get(i) instanceof Spikes) {
			gameObjects.remove(i);
		}
	}
	
	public void addBanana(Banana b) {
		gameObjects.add(b);
	}
	
	public void removeBanana(int i) {
		if(gameObjects.get(i) instanceof Banana) {
			gameObjects.remove(i);
		}
	}
	
	public void addMonkey(Monkey m) {
		gameObjects.add(m);
	}
	
	public void removeMonkey(int i) {
		if(gameObjects.get(i) instanceof Monkey) {
			gameObjects.remove(i);
		}
	}
	
	public ArrayList<GameObject> getGameObjectsList() {
		return gameObjects;
	}
	
	public ArrayList<Bloon> getBloonsList() {
		ArrayList<Bloon> bloons = new ArrayList<Bloon>();
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Bloon) {
				bloons.add((Bloon) gameObjects.get(i));
			}
		}
		return bloons;
	}
	
	public ArrayList<Monkey> getMonkeyList() {
		ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Monkey) {
				monkeys.add((Monkey) gameObjects.get(i));
			}
		}
		return monkeys;
	}
	
	public ArrayList<Spikes> getSpikesList() {
		ArrayList<Spikes> spikes = new ArrayList<Spikes>();
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Spikes) {
				spikes.add((Spikes) gameObjects.get(i));
			}
		}
		return spikes;
	}
	
	public ArrayList<Banana> getBananaList() {
		ArrayList<Banana> bananas = new ArrayList<Banana>();
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i) instanceof Banana) {
				bananas.add((Banana) gameObjects.get(i));
			}
		}
		return bananas;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public double getHratio() {
		return Hratio;
	}
	
	public double getWratio() {
		return Wratio;
	}
	
	public Pixel[][] getGrid() {
		return grid;
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, 0, 0, width, height, null);
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
}
