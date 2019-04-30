import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.scene.shape.Circle;

public class Bloon {
	private int rank, radius, health;
	private Image img;
	public final static String PATH_PREFIX = "res/images/";
	private double x, y, angle, distance = 0;
	int[] width = {24,25,27,29,30,21,21,30,30,30,30,80,150,250};
	int[] height = {32,34,36,38,40,28,28,40,40,40,40,50,100,150};
	int[] healthArr = {1,1,1,1,1,1,1,1,1,1,10,200,700,4000};
	double[] speed = {3,4,5,10,11,5,6,5,3,7,8,3,1,0.5};
	private Rectangle rect;
	private boolean isRegrow = false, isCamo = false;

	public Bloon(int r, double x, double y, double angle) {
		rank = r;
		this.x = x;
		this.y = y;
		this.angle = angle;
		String s = "bloon"+r+".png";
		img = getImage(s);
		radius = (width[rank-1]+height[rank-1])/4;
		health = healthArr[rank-1];
		rect = new Rectangle((int) (x-radius), (int) (y-radius), 2*radius, 2*radius);
	}

	public Bloon(int r, double x, double y, double angle, double distance) {
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
	}

	public Bloon(int r, double x, double y, double angle, boolean regrow, boolean camo) {
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

	public ArrayList<Bloon> hit(int a, int b) {
		ArrayList<Bloon> bloons = new ArrayList<Bloon>();
		if (rank == 1) {
			return bloons;
		}
		double theta = angle * Math.PI/180;
		if (rank < 6) {
			bloons.add(new Bloon(rank-1, x, y, angle, distance));
		}
		else if (rank == 6 || rank == 7 || rank == 10 || rank == 11) {
			if (rank == 6 || rank == 11) {
				bloons.add(new Bloon(rank-1, (int) (x-10*Math.cos(theta)), y+10*Math.sin(theta), angle, distance-10));
				bloons.add(new Bloon(rank-1, (int) (x+10*Math.cos(theta)), y-10*Math.sin(theta), angle, distance+10));
			}
			else {
				bloons.add(new Bloon(rank-2, (int) (x-10*Math.cos(theta)), y+10*Math.sin(theta), angle, distance-10));
				bloons.add(new Bloon(rank-2, (int) (x+10*Math.cos(theta)), y-10*Math.sin(theta), angle, distance+10));
			}
		}
		else if (rank == 8) {
			bloons.add(new Bloon(6, (int) (x-5*Math.cos(theta)), y+5*Math.sin(theta), angle, distance-5));
			bloons.add(new Bloon(7, (int) (x+5*Math.cos(theta)), y-5*Math.sin(theta), angle, distance+5));
		}
		else if (rank == 9) {
			bloons.add(new Bloon(6, (int) (x-5*Math.cos(theta)), y+5*Math.sin(theta), angle, distance-5));
			bloons.add(new Bloon(6, (int) (x+5*Math.cos(theta)), y-5*Math.sin(theta), angle, distance+5));
		}
		else if (rank >= 12 && rank <= 14) {
			int[] arr = {10, 15, 20};
			int index = rank-12;
			bloons.add(new Bloon(rank-1, (int) (x-3*arr[index]*Math.cos(theta)), y+3*arr[index]*Math.sin(theta), angle, distance-3*arr[index]));
			bloons.add(new Bloon(rank-1, (int) (x-arr[index]*Math.cos(theta)), y+arr[index]*Math.sin(theta), angle, distance-arr[index]));
			bloons.add(new Bloon(rank-1, (int) (x+arr[index]*Math.cos(theta)), y-arr[index]*Math.sin(theta), angle, distance+arr[index]));
			bloons.add(new Bloon(rank-1, (int) (x+3*arr[index]*Math.cos(theta)), y-3*arr[index]*Math.sin(theta), angle, distance+3*arr[index]));
		}
		return bloons;
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

	public void draw(Graphics g) {
		g.drawImage(img, (int) (x-width[rank-1]/2), (int) (y-height[rank-1]/2), width[rank-1], height[rank-1], null);
	}

	public double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
	}

	public int getRank() {
		return rank;
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

	public void update(double time) {
		double v = speed[rank-1];
		double theta = angle*Math.PI/180;
		x += v*time*Math.cos(theta);
		y -= v*time*Math.sin(theta);
		distance += v*time;
		rect.translate((int) (x-rect.x), (int) (y-rect.y));
	}

}
