import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SpikeFactory {
	private int x;
	private int y;
	private int height = 100;
	private int width = 100;
	private int radius = 300;
	private String PATH_PREFIX = "images/";
	private Rectangle rect;
	private Image spikefactory = null;
	public SpikeFactory(int x,int y) {
		spikefactory = getImage("Spike_Factory_Path.jpg");
		this.x=x;
		this.y=y;
		rect = new Rectangle(x,y,width,height);
	}
	protected Image getImage(String fn) {
        Image img = null;
        fn = PATH_PREFIX+fn;
        try {
            img = ImageIO.read(this.getClass().getResource(fn));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return img;
    }
	public void target(JPanel panel) {
		Spikes toPut = new Spikes((int) rect.getX()+width/2,(int) rect.getY()+height/2);
		double deg = 360*Math.random();
		int changex = (int) (radius*Math.cos(deg));
		int changey = (int) (radius*Math.sin(deg));
		toPut.move(changex,changey);
		
		
	}
}
