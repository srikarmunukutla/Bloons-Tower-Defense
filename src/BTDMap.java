import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class BTDMap {
	protected boolean[][] grid;
	protected ArrayList<Bloon> bloons;
	protected Image img = null;
	protected int height, width;
	protected double Hratio, Wratio;
	private String PATH_PREFIX = "images/";
	public static double origH = 520, origW = 700;
	
	public BTDMap(int r, int c) {
		height = r;
		width = c;
		grid = new boolean[height][width];
		Hratio = (height/origH);
		Wratio = (width/origW);
		bloons = new ArrayList<Bloon>();
	}
	
	protected abstract void initializeTrack();
	
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
	
	public boolean[][] getGrid() {
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
