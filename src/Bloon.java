import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Bloon implements GameObject{
    private int rank, radius, health;
    private Image img;
    public final static String PATH_PREFIX = "images/";
    private double x, y, angle, distance = 0;
    int[] width = {24,25,27,29,30,21,21,30,30,30,30,80,120,170};
    int[] height = {32,34,36,38,40,28,28,40,40,40,40,50,80,102};
    int[] healthArr = {1,1,1,1,1,1,1,1,1,1,10,200,700,4000};
    double[] speed = {3,4,5,10,11,5,6,5,3,7,8,3,1,0.5};
    int[] count = {1,1,1,1,1,2,2,4,4,8,16,64,256,1024};
    int[] sep = {6,7,8,9,10,5,5,10,10,10,10,10,15,20};
    private Rectangle rect;
    private boolean isRegrow = false, isCamo = false;
    private HashSet<Integer> darthit;
    public Bloon(int r, double x, double y, double angle, HashSet<Integer> hm) {
        rank = r;
        this.x = x;
        this.y = y;
        this.angle = angle;
        String s = "bloon"+r+".png";
        img = getImage(s);
        radius = (width[rank-1]+height[rank-1])/4;
        health = healthArr[rank-1];
        rect = new Rectangle((int) (x-radius), (int) (y-radius), 2*radius, 2*radius);
        darthit = hm;
    }

    public Bloon(int r, double x, double y, double angle, double distance, HashSet<Integer> hm) {
        rank = r;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.distance = distance;
        String s = "bloon"+r+".png";
        img = getImage(s);
        radius = (width[rank-1]+height[rank-1])/4;
        health = healthArr[rank-1];
        rect = new Rectangle((int) (x-radius), (int) (y-radius), 2*radius, 2*radius);
        darthit = hm;
    }
    
    public Bloon(int r, double x, double y, double angle, double distance, int health, HashSet<Integer> hm) {
        rank = r;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.distance = distance;
        String s = "bloon"+r+".png";
        img = getImage(s);
        radius = (width[rank-1]+height[rank-1])/4;
        this.health = health;
        rect = new Rectangle((int) (x-radius), (int) (y-radius), 2*radius, 2*radius);
        darthit = hm;
    }

    public Bloon(int r, double x, double y, double angle, boolean regrow, boolean camo, HashSet<Integer> hm) {
        rank = r;
        this.x = x;
        this.y = y;
        this.angle = angle;
        isRegrow = regrow;
        isCamo = camo;
        String s = "bloon"+r+".png";
        img = getImage(s);
        radius = (width[rank-1]+height[rank-1])/4;
        health = healthArr[rank-1];
        rect = new Rectangle((int) (x-radius), (int) (y-radius), 2*radius, 2*radius);
    }

    public boolean checkHit(double x, double y) {
        if (distance(this.x,this.y,x,y) <= radius) {
            return true;
        }
        return false;
    }

    public ArrayList<Bloon> hit(int a, int b, int damage) {
        ArrayList<Bloon> bloons = new ArrayList<Bloon>();
        int addmoney = 0;
    	int startrank = rank;
		health -= damage;
		while (rank > 0 && health <= 0) {
			if (rank == 10|| rank == 7 || rank == 8) {
				rank--;
			}
			else if (rank == 9) {
				rank -= 2;
			}
			rank--;
			if (rank == 0) {
				return bloons;
			}
			health+=healthArr[rank-1];
		}
		int numBloons = count[startrank-1]/count[rank-1];
		double theta = angle * Math.PI/180;
		if (numBloons == 1) {
			bloons.add(new Bloon(rank, x, y, angle, distance, health,(HashSet<Integer>) darthit.clone()));
		}
		else {
			if (rank != 6 || (rank == 6 && startrank == 9)) {
				for (int i = numBloons/2-1; i >= 0; i--) {
					bloons.add(new Bloon(rank, (int) (x-(1+2*i)*sep[rank-1]*Math.cos(theta)), (int) (y+(1+2*i)*sep[rank-1]*Math.sin(theta)), angle, distance-(1-2*i)*sep[rank-1], health,(HashSet<Integer>) darthit.clone()));
				}
				for (int i = 0; i < numBloons/2; i++) {
					bloons.add(new Bloon(rank, (int) (x+(1+2*i)*sep[rank-1]*Math.cos(theta)), (int) (y-(1+2*i)*sep[rank-1]*Math.sin(theta)), angle, distance+(1+2*i)*sep[rank-1], health,(HashSet<Integer>) darthit.clone()));
				}
			}
			else {
				if (numBloons == 2) {
					bloons.add(new Bloon(rank+1, (int) (x-sep[rank-1]*Math.cos(theta)), (int) (y+sep[rank-1]*Math.sin(theta)), angle, distance-sep[rank-1], health,(HashSet<Integer>) darthit.clone()));
					bloons.add(new Bloon(rank, (int) (x+sep[rank-1]*Math.cos(theta)), (int) (y-sep[rank-1]*Math.sin(theta)), angle, distance+sep[rank-1], health,(HashSet<Integer>) darthit.clone()));
				}
				else {
					for (int i = numBloons/2-1; i >= 0; i--) {
						bloons.add(new Bloon(rank+(i%2), (int) (x-(1+2*i)*sep[rank-1]*Math.cos(theta)), (int) (y+(1+2*i)*sep[rank-1]*Math.sin(theta)), angle, distance-(1-2*i)*sep[rank-1], health,(HashSet<Integer>) darthit.clone()));
					}
					for (int i = 0; i < numBloons/2; i++) {
						bloons.add(new Bloon(rank+1-(i%2), (int) (x+(1+2*i)*sep[rank-1]*Math.cos(theta)), (int) (y-(1+2*i)*sep[rank-1]*Math.sin(theta)), angle, distance+(1+2*i)*sep[rank-1], health,(HashSet<Integer>) darthit.clone()));
					}
				}
			}
		}
		return bloons;
    }

    protected Image getImage(String fn) {
        Image img = null;
        fn = PATH_PREFIX+fn;
        try {
            img = ImageIO.read(this.getClass().getResource(fn));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public void draw(Graphics g, JPanel panel) {
        g.drawImage(img, (int) (x-width[rank-1]/2), (int) (y-height[rank-1]/2), width[rank-1], height[rank-1], null);
//        Graphics2D g2d = (Graphics2D) g.create();
//        AffineTransform at = new AffineTransform();
//        at.setToRotation(Math.toRadians(angle), x, y);
//        at.translate(x, y);
//        g2d.setTransform(at);
//        g2d.drawImage(img, (int) (x-width[rank-1]/2), (int) (y-height[rank-1]/2), width[rank-1], height[rank-1], null);
//        g2d.dispose();
    }

    public double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
    }

    public int getRank() {
        return rank;
    }
    
    public Rectangle getRect(){
        return rect;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int decrease) {
        health -= decrease;
    }

    public double getDistance() {
        return distance;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void update(ArrayList<GameObject> al, Pixel[][] grid, BTDMap m, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile) {
        double v = speed[rank-1];
        double theta = angle*Math.PI/180;
        x += v*time*Math.cos(theta);
        y -= v*time*Math.sin(theta);
        distance += v*time;
        rect.translate((int) (x-rect.x), (int) (y-rect.y));
		if(x >= grid[0].length || y >= grid.length) {
			for (int i = al.size()-1; i >= 0; i--){
			    if (this.equals(al.get(i))){
			        
			        al.remove(i);
			        return;
                }
            }
		}
		if(grid[(int) y][(int) x].getAngle() != getAngle()) {
			setAngle(grid[(int) y][(int) x].getAngle());
		}
    }
    public void addDart(int a){
        darthit.add(a);
    }
    public void removeDart(int a){
        if (darthit.contains(a)){
            darthit.remove(a);
        }
    }
    public boolean checkDart(int a){
        if (darthit.contains(a)){
            return true;
        }
        return false;
    }
    public void clickedAt(){

    }

}