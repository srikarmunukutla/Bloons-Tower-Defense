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
	
	public ArrayList<Bloon> getBloons(ArrayList<GameObject> al){
		ArrayList<Bloon> ret = new ArrayList<Bloon>();
		for(int i = 0; i < al.size(); i++){
			if(al.get(i) instanceof Bloon){
				ret.add((Bloon) al.get(i));
			}
		}
		return ret;
	}
	
	public ArrayList<Spikes> getSpikes(ArrayList<GameObject> al){
		ArrayList<Spikes> ret = new ArrayList<Spikes>();
		for(int i = 0; i < al.size(); i++){
			if(al.get(i) instanceof Spikes){
				ret.add((Spikes) al.get(i));
			}
		}
		return ret;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void draw(Graphics g, JPanel panel) {
		g.drawImage(spikes, x, y, width, height, null);
	}
	
	public void update(ArrayList<GameObject> al, Pixel[][] grid, BTDMap m, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile) {
		ArrayList<Bloon> bloonal = getBloons(al);
		ArrayList<Spikes> spikeal = getSpikes(al);
		if(bloonal.size()==0) {
			return;
		}
		for(int i = bloonal.size()-1; i >= 0; i--) {
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
	
	public void move(int dx, int dy) {
		
	}
	
}