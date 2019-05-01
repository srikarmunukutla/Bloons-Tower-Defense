import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Map {
	protected boolean[][] grid;
	protected String PATH_PREFIX = "images/";
	protected Image img = null;
	
	public Map(int r, int c) {
		grid = new boolean[r][c];
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
//		g.drawImage(img, 0, 0, dx2, dy2, sx1, sy1, sx2, sy2, observer)
	}
}
