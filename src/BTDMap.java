import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.*;

public abstract class BTDMap {
	protected Pixel[][] grid;
	protected ArrayList<GameObject> gameobjects;
	protected HashMap<Integer, Projectile> gameprojectiles;
	protected Image img = null;
	protected Monkey userselection;
	protected int userx, usery;
	protected TowerPanel tp;
	protected boolean clicked;
	protected int height, width;
	protected double Hratio, Wratio;
	private String PATH_PREFIX = "images/";
	private Level level = new Level();
	public static double origH = 520, origW = 700;
	protected int SQUARESIZE = 50;
	protected int health;
	private int spawnx;
	private int spawny;
	private boolean isselectionvalid;
	private boolean monkeyclicked;
	private int money;
	private Timer tim;
	protected long ticks = 0;
	protected Rectangle playbutton;
	protected boolean playbuttonclicked, fastforwardclicked;
	protected Image playbuttonimg;
	protected int levelnum;
	
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
		money = 650;
		spawnx = spx;
		spawny = spy;
		isselectionvalid = false;
		monkeyclicked = false;
		playbutton = new Rectangle(width - 50, 10, 40, 41);
		playbuttonimg = getImage("Play_Button.png");
		playbuttonclicked = false;
		fastforwardclicked = false;
	}

	private BTDMap getMap(){
		return this;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMonkeyClicked() {
		monkeyclicked = !monkeyclicked;
	}
	
	public boolean isMonkeyClicked() {
		return monkeyclicked;
	}
	
	public int getLevelNum() {
		return level.getLevelNum();
	}

	private void startLevel() {
		tim = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				level.spawn(gameobjects, getMap(), ticks);
				ticks++;
				if (level.getWave() > 20){
					ticks = 0;
					level.changeSpawn(gameobjects);
				}
				if (level.getWave() == 21){
					money += 100;
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
		health -= b.liveslost[b.getRank() - 1];
	}

	public Monkey getUserSelection() {
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
		g.drawImage(playbuttonimg, width - 50, 10, 40, 41, null);
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
		if(playbutton.contains(me.getX(), me.getY())) {
			if(!playbuttonclicked) {
				playbuttonimg = getImage("Fast_Forward.png");
				playbuttonclicked = true;
				startLevel();
				return;
			}
			else if(!fastforwardclicked) {
				fastforwardclicked = !fastforwardclicked;
				playbuttonimg = getImage("Fast_Forward_Clicked.png");
				new FastForward().fastforward();
				for (GameObject go : gameobjects){
					if (go instanceof Monkey){
						((Monkey)(go)).speedup();
					}
				}
				level.speedup();
			}
			else if(fastforwardclicked) {
				fastforwardclicked = !fastforwardclicked;
				playbuttonimg = getImage("Fast_Forward.png");
				new FastForward().slowdown();
				for (GameObject go : gameobjects){
					if (go instanceof Monkey){
						((Monkey)(go)).slowdown();
					}
				}
				level.slowdown();
			}
		}
		if(!clicked) {
			int ind = -1;
			for(int i = 0; i < 10; i++) {
				if(tp.monkeyarr[i].imgrect.contains(me.getX(), me.getY())) {
					ind = i;
					break;
				}
			}

			switch(ind) {
				case 0:
					userselection = new DartMonkey(me.getX(), me.getY());
					break;
				case 1:
					userselection = new TackShooter(me.getX(), me.getY());
					break;
				case 2:
					userselection = new MonkeyApprentice(me.getX(), me.getY());
					break;
				case 3:
					userselection = new MonkeyAce(me.getX(), me.getY(), false);
					break;
				case 4:
					userselection = new BananaFarm(me.getX(), me.getY(), false);
					break;
				case 5:
					userselection = new SuperMonkey(me.getX(), me.getY());
					break;
				case 6:
					userselection = new NinjaMonkey(me.getX(), me.getY());
					break;
				case 7:
					userselection = new RichardHanson(me.getX(), me.getY());
					break;
				case 8:
					userselection = new BoomerMonkey(me.getX(), me.getY(), false);
					break;
				case 9:
					userselection = new SniperMonkey(me.getX(), me.getY());
					break;
				default:
					return;
			}
			userx = me.getX() - userselection.width/2;
			usery = me.getY() - userselection.height/2;
			isselectionvalid = true;
		}
		else {
			if(me.getX() >= width) {
				clicked = !clicked;
				userselection = null;
				return;
			}
			if(!isPlacementValid(me)) {
				return;
			}
			money -= userselection.getCost();
			userselection.setLoc(me.getX(), me.getY());
			gameobjects.add(userselection);
			flipCover(userselection.getImgRect());
			userselection = null;
		}
		clicked = !clicked;
	}
	
	private boolean isPlacementValid(MouseEvent me) {
		if(!onTheMap(userselection, me.getX(), me.getY())) {
			isselectionvalid = false;
			return false;
		}
		if (userselection.getCost() > money){
			isselectionvalid = false;
			return false;
		}
		for(int r = getUserY(); r < getUserY() + userselection.height; r++) {
			for(int c = getUserX(); c < getUserX() + userselection.width; c++) {
				if(r < height && c < width && grid[r][c].coveredUp()) {
					isselectionvalid = false;
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean onTheMap(Monkey m, int x, int y) {
		return (x + m.width/2 < width) && (x - m.width/2 >= 0) && (y - m.height/2 >= 0) && (y + m.height/2 < height);
	}

	public boolean isValid() {
		return isselectionvalid;
	}

	public void mouseMoved(MouseEvent me) {
		if (clicked) {
			userx = me.getX()-userselection.width/2;
			usery = me.getY()-userselection.height/2;
			userselection.setRangeRect(me.getX(), me.getY());
			isselectionvalid = isPlacementValid(me);
		}
	}

	public Bloon createBloon(int num, int offset) {
		return new Bloon(num,spawnx-offset,spawny,0,new HashSet<Integer>());
	}

	protected void flipCover(Rectangle rect) {
		for(int r = rect.y; r < rect.y + rect.height; r++) {
			for(int c = rect.x; c < rect.x + rect.width; c++) {
				if(r < height && c < width) {
					grid[r][c].flipCover();
				}
			}
		}
	}
	
	public void increaseMoney(int amount) {
		money += amount;
	}
}