import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TowerPanel {
	public final static int truewidth = 150;
	private int height, width;
	private Image moneylivesimg, towerstitle;
	private String PATH_PREFIX = "images/";
	public Monkey[] monkeyarr;
	
	public TowerPanel(int h, int w, BTDMap m) {
		height = h;
		width = w;
		moneylivesimg = getImage("Money_and_Lives.png");
		towerstitle = getImage("Towers_Title.png");
		monkeyarr = new Monkey[8];
		initializeMonkeys(m);
	}
	
	public void draw(Graphics g, BTDMap m, JPanel panel) {
		g.setColor(Color.BLUE);
		g.fillRect(m.getWidth(), 0, width, height);
		g.drawImage(towerstitle, m.getWidth(), 0, width, 143, null);
		g.drawImage(moneylivesimg, m.getWidth(), 143, width, 76, null);
		for(Monkey monkey: monkeyarr) {
			monkey.draw(g, panel);
		}
	}
	
	public void initializeMonkeys(BTDMap m) {
		monkeyarr[0] = new DartMonkey(m.getWidth(), 219);
//		monkeyarr[1] = new TackShooter(m.getWidth() + )
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
