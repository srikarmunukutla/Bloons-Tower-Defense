import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.*;

public abstract class BTDMap {
	protected Pixel[][] grid;
	protected ArrayList<GameObject> gameobjects;
	protected HashMap<Integer, Projectile> gameprojectiles;
	protected Image img = null;
	protected Image userselection;
	protected int userx, usery;
	protected TowerPanel tp;
	protected boolean clicked;
	protected int height, width;
	protected double Hratio, Wratio;
	private String PATH_PREFIX = "images/";
	Level level = new Level();
	public static double origH = 520, origW = 700;
	protected int SQUARESIZE = 50;
	protected int health;
	private int spawnx;
	private int spawny;
	
	public BTDMap(int r, int c, int spx, int spy) {
		height = r;
		width = c;
		tp = new TowerPanel(height, TowerPanel.truewidth, this);
		grid = new Pixel[height][width];
		Hratio = (height/origH);
		Wratio = (width/origW);
		gameobjects = new ArrayList<GameObject>();
		gameprojectiles = new HashMap<Integer, Projectile>();
		clicked = false;
		health = 200;
		spawnx = spx;
		spawny = spy;
		startLevel();
	}
	private BTDMap getMap(){
		return this;
	}
	Timer tim;
	long ticks = 0;
	private void startLevel(){
		tim = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				level.spawn(gameobjects,getMap(), ticks);
				ticks++;
				if (level.getWave() > 20){
					ticks = 0;
					level.changeSpawn(gameobjects);
				}
			}

		});
		tim.start();
	}
	protected abstract void initializeTrack();

	
	protected void initializeGrid() {
		for(int r = 0; r < height; r++) {
			for(int c = 0; c < width; c++) {
				grid[r][c] = new Pixel(0, false);
			}
		}
	}
	
	public void reduceHealth(Bloon b) {
		health -= b.liveslost[b.getRank()-1];
		System.out.println(health);
	}
	
	public Image getUserSelection() {
		return userselection;
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
	public int getUserX() {
		return userx;
	}
	
	public int getUserY() {
		return usery;
	}
	
	public void addBloon(Bloon b) {
		gameobjects.add(b);
	}
	
	public void removeBloon(int i) {
		if(gameobjects.get(i) instanceof Bloon) {
			gameobjects.remove(i);
		}
	}
	
	public void addSpikes(Spikes s) {
		gameobjects.add(s);
	}
	
	public void removeSpikes(int i) {
		if(gameobjects.get(i) instanceof Spikes) {
			gameobjects.remove(i);
		}
	}
	
	public void addBanana(Banana b) {
		gameobjects.add(b);
	}
	
	public void removeBanana(int i) {
		if(gameobjects.get(i) instanceof Banana) {
			gameobjects.remove(i);
		}
	}
	
	public void addMonkey(Monkey m) {
		gameobjects.add(m);
	}
	
	public void removeMonkey(int i) {
		if(gameobjects.get(i) instanceof Monkey) {
			gameobjects.remove(i);
		}
	}
	
	public ArrayList<GameObject> getGameObjectsList() {
		return gameobjects;
	}
	
	public HashMap<Integer, Projectile> getGameProjectilesList() {
		return gameprojectiles;
	}
	
	public ArrayList<Bloon> getBloonsList() {
		ArrayList<Bloon> bloons = new ArrayList<Bloon>();
		for(int i = 0; i < gameobjects.size(); i++) {
			if(gameobjects.get(i) instanceof Bloon) {
				bloons.add((Bloon) gameobjects.get(i));
			}
		}
		return bloons;
	}
	
	public ArrayList<Monkey> getMonkeyList() {
		ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
		for(int i = 0; i < gameobjects.size(); i++) {
			if(gameobjects.get(i) instanceof Monkey) {
				monkeys.add((Monkey) gameobjects.get(i));
			}
		}
		return monkeys;
	}
	
	public ArrayList<Spikes> getSpikesList() {
		ArrayList<Spikes> spikes = new ArrayList<Spikes>();
		for(int i = 0; i < gameobjects.size(); i++) {
			if(gameobjects.get(i) instanceof Spikes) {
				spikes.add((Spikes) gameobjects.get(i));
			}
		}
		return spikes;
	}
	
	public ArrayList<Banana> getBananaList() {
		ArrayList<Banana> bananas = new ArrayList<Banana>();
		for(int i = 0; i < gameobjects.size(); i++) {
			if(gameobjects.get(i) instanceof Banana) {
				bananas.add((Banana) gameobjects.get(i));
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
	
	public void draw(Graphics g, JPanel panel) {
		g.drawImage(img, 0, 0, width, height, null);
		tp.draw(g, this, panel);
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
	
	public void clickedAt(MouseEvent me) {
		if(!clicked) {
			userselection = new BananaFarm(me.getX(), me.getY()).getImg();
			userx = me.getX() - SQUARESIZE/2;
			usery = me.getY() - SQUARESIZE/2;
		}
		else {
			gameobjects.add(new BananaFarm(me.getX(),me.getY()));
		}
		clicked = !clicked;
	}
	
	public void mouseMoved(MouseEvent me){
		if (clicked) {
			userx = me.getX()-SQUARESIZE/2;
			usery = me.getY()-SQUARESIZE/2;
		}
	}
	public Bloon createBloon(int num, int offset){
		return new Bloon(num,spawnx-offset,spawny,0,new HashSet<Integer>());
	}
}
