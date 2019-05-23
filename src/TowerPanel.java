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
		g.drawImage(towerstitle, m.getWidth(), 0, width, 114, null);
		g.drawImage(moneylivesimg, m.getWidth(), 114, width, 61, null);
//		monkeyarr[0].draw(g, panel);
//		System.out.println(monkeyarr[0].getX() + " " + monkeyarr[0].getY());
		for(Monkey monkey: monkeyarr) {
			if(monkey instanceof MonkeyAce) {
				((MonkeyAce) monkey).draw(g, panel, true);
			}
			else {
				monkey.draw(g, panel);
			}
		}
	}
	
	public void initializeMonkeys(BTDMap m) {
		monkeyarr[0] = new DartMonkey(m.getWidth() + 30, 210);
		monkeyarr[1] = new TackShooter(m.getWidth() + 90, 210);
		monkeyarr[2] = new MonkeyApprentice(m.getWidth() + 30, 280);
		monkeyarr[3] = new MonkeyAce(m.getWidth() + 90, 280);
		monkeyarr[4] = new BananaFarm(m.getWidth() + 30, 350);
		monkeyarr[5] = new SuperMonkey(m.getWidth() + 90, 350);
		monkeyarr[6] = new NinjaMonkey(m.getWidth() + 30, 420);
		monkeyarr[7] = new RichardHanson(m.getWidth() + 90, 420);
		monkeyarr[8] = new GlueGunner(m.getWidth() + 30, 510);
		monkeyarr[9] = new SniperMonkey(m.getWidth() + 90, 510);
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
