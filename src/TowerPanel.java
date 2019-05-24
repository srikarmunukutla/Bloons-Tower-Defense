import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TowerPanel {
	public final static int truewidth = 120;
	private int height, width;
	private Image moneylivesimg, towerstitle;
	private String PATH_PREFIX = "images/";
	public Monkey[] monkeyarr;
	
	public TowerPanel(int h, int w, BTDMap m) {
		height = h;
		width = w;
		moneylivesimg = getImage("Money_and_Lives.png");
		towerstitle = getImage("Towers_Title.png");
		monkeyarr = new Monkey[10];
		initializeMonkeys(m);
	}
	
	public void draw(Graphics g, BTDMap m, JPanel panel) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(m.getWidth(), 0, width, height);
		g.drawImage(towerstitle, m.getWidth(), 0, width, 114, null);
		g.drawImage(moneylivesimg, m.getWidth(), 114, width, 61, null);
		g.setColor(Color.BLACK);
		g.drawLine(m.getWidth() + width/2, 175, m.getWidth() + width/2, height);
		for(int i = 1; i <= 3; i++) {
			g.drawLine(m.getWidth(), 175 + (90 * i), m.getWidth() + width, 175 + (90 * i));
		}
		g.drawLine(m.getWidth(), 535, m.getWidth() + width, 535);
		g.drawLine(m.getWidth(), 625, m.getWidth() + width, 625);
		for(Monkey monkey: monkeyarr) {
			monkey.draw(g, panel);
		}
	}
	
	public void initializeMonkeys(BTDMap m) {
		monkeyarr[0] = new DartMonkey(m.getWidth() + 30, 220);
		monkeyarr[1] = new TackShooter(m.getWidth() + 90, 220);
		monkeyarr[2] = new MonkeyApprentice(m.getWidth() + 30, 310);
		monkeyarr[3] = new MonkeyAce(m.getWidth() + 90, 310, true);
		monkeyarr[4] = new BananaFarm(m.getWidth() + 30, 400);
		monkeyarr[5] = new SuperMonkey(m.getWidth() + 90, 400);
		monkeyarr[6] = new NinjaMonkey(m.getWidth() + 30, 490);
		monkeyarr[7] = new RichardHanson(m.getWidth() + 90, 490);
		monkeyarr[8] = new GlueGunner(m.getWidth() + 30, 580);
		monkeyarr[9] = new SniperMonkey(m.getWidth() + 90, 580);
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