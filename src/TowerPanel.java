import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TowerPanel {
	public final static int truewidth = 200;
	private int height, width;
	private Image moneylivesimg;
	private String PATH_PREFIX = "images/";
	
	public TowerPanel(int h, int w) {
		height = h;
		width = w;
		moneylivesimg = getImage("Money_and_Lives.png");
	}
	
	public void draw(Graphics g, BTDMap m) {
		g.setColor(Color.BLUE);
		g.fillRect(m.getWidth(), 0, width, height);
//		g.drawImage(img, m.getWidth(), 0, width, height, null);
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
