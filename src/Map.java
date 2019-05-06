import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Map {
	protected boolean[][] grid;
	protected String PATH_PREFIX = "images/";
	protected Image img = null;
	protected int height, width;
	protected int origH = 520, origW = 700;
	protected int Hratio, Wratio;
	
	public Map(int r, int c) {
		height = r;
		width = c;
		grid = new boolean[height][width];
		Hratio = (height/origH);
		Wratio = (width/origW);
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
	
	public void draw(Graphics g) {
		g.drawImage(img, 0, 0, width, height, null);
	}
	
	public boolean[][] getGrid() {
		return grid;
	}
	
	protected abstract void initializeTrack();
}
