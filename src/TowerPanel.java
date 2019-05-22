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
		g.setColor(Color.BLUE);
		g.fillRect(m.getWidth(), 0, width, height);
		g.drawImage(towerstitle, m.getWidth(), 0, width, 143, null);
		g.drawImage(moneylivesimg, m.getWidth(), 143, width, 76, null);
		monkeyarr[0].draw(g, panel);
//		System.out.println(monkeyarr[0].getX() + " " + monkeyarr[0].getY());
//		for(Monkey monkey: monkeyarr) {
//			monkey.draw(g, panel);
//		}
	}
	
	public void initializeMonkeys(BTDMap m) {
		monkeyarr[0] = new DartMonkey(m.getWidth() + 30, 205);
		monkeyarr[1] = new TackShooter(m.getWidth() + 90, 205);
		monkeyarr[2] = new MonkeyApprentice(m.getWidth() + 30, 265);
		monkeyarr[3] = new MonkeyAce(m.getWidth() + 90, 265);
		monkeyarr[4] = new BananaFarm(m.getWidth() + 30, 325);
		monkeyarr[5] = new SuperMonkey(m.getWidth() + 90, 325);
		monkeyarr[6] = new NinjaMonkey(m.getWidth() + 30, 390);
		monkeyarr[7] = new RichardHanson(m.getWidth() + 90, 390);
		monkeyarr[8] = new GlueGunner(m.getWidth() + 30, 470);
		monkeyarr[9] = new SniperMonkey(m.getWidth() + 90, 470);
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
