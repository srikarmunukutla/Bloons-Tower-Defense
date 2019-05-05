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
	private int width = 70;
	private int height = 70;
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
	
	public void draw(Graphics g, JPanel panel) {
		g.drawImage(spikes, x, y, width,height, null);
	}
	public void collided(ArrayList<Bloon> al, JPanel panel) {
		if(al.size()==0) {
			return;
		}
		for(int i=0;i<al.size();i++) {
			if(al.get(i).getRect().intersects(rect)) {
				health--;
				al.get(i).hit((int) al.get(i).getX(),(int) al.get(i).getY(),1);
				if(health==0) {
					panel.repaint();
				}
			}
		}
	}
	
	
}