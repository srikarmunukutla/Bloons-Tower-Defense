import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.io.*;
public class Spikes {
	private int x;
	private int y;
	private int width = 37;
	private int height = 37;
	private int health = 10;
	private String PATH_PREFIX = "images/";
	private Rectangle rect;
	private Image spikes = null;
	public Spikes(int x,int y) {
		spikes = getImage("Road_Spikes.png");
		this.x=x;
		this.y=y;
		rect = new Rectangle(x,y,height,width);
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
	public int getHealth(){
		return health;
	}
	public void draw(Graphics g) {
		g.drawImage(spikes, x, y, width,height, null);
	}
	public void collided(ArrayList<Bloon> al, ArrayList<Spikes> spikeal) {
		if(al.size()==0) {
			return;
		}
		for(int i= al.size()-1;i >= 0;i--) {
			if(al.get(i).getRect().intersects(rect)) {
				health--;
				al.addAll(al.get(i).hit((int)al.get(i).getX(),(int)al.get(i).getY(),1));
				al.remove(i);
				if(health==0) {
					return;
				}
			}
		}

	}
	
	
}