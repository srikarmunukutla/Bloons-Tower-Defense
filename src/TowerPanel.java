import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TowerPanel {
	private int height, width;
	private Image img = null;
	private String PATH_PREFIX = "images/";
	
	public TowerPanel(int h, int w) {
		height = h;
		width = w;
		img = getImage("Panel.png");
	}
	
	public void draw(Graphics g, BTDMap m) {
		g.drawImage(img, m.getWidth(), 0, width, height, null);
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
