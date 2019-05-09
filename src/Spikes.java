import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.io.*;
import java.util.HashMap;

public class Spikes implements GameObject{
	private int x;
	private int y;
	private int width = (int) (37 * 1.3);
	private int height = 25;
	private int health = 6;
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
	public void draw(Graphics g, JPanel panel) {
		g.drawImage(spikes, x, y, width,height, null);
	}
	public void update(ArrayList<Bloon> bloonal, ArrayList<Spikes> spikeal, double timepassed, JPanel panel, HashMap<Integer,Projectile> gameprojectile) {
		if(bloonal.size()==0) {
			return;
		}
		for(int i= bloonal.size()-1;i >= 0;i--) {
			if(bloonal.get(i).getRect().intersects(rect)) {
				health--;
				bloonal.addAll(bloonal.get(i).hit((int)bloonal.get(i).getX(),(int)bloonal.get(i).getY(),1));
				bloonal.remove(i);
				if(health==0) {
					spikeal.remove(this);
					return;
				}
			}
		}

	}
	public void clickedAt(){

	}
	
}