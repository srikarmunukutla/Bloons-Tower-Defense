import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class BTDMap {
	protected Pixel[][] grid;
	protected ArrayList<Bloon> bloonsList;
	protected Image img = null;
	protected int height, width;
	protected double Hratio, Wratio;
	private String PATH_PREFIX = "images/";
	public static double origH = 520, origW = 700;
	
	public BTDMap(int r, int c) {
		height = r;
		width = c;
		grid = new Pixel[height][width];
		Hratio = (height/origH);
		Wratio = (width/origW);
		bloonsList = new ArrayList<Bloon>();
	}
	
	protected abstract void initializeTrack();
	
	protected void createLevel() {
		
	}
	
	protected void initializeGrid() {
		for(int r = 0; r < height; r++) {
			for(int c = 0; c < width; c++) {
				grid[r][c] = new Pixel(0, false);
			}
		}
	}
	
	public void addBloon(Bloon b) {
		bloonsList.add(b);
	}
	
	public void removeBloon(int i) {
		bloonsList.remove(i);
	}
	
	public ArrayList<Bloon> getBloonList() {
		return bloonsList;
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
