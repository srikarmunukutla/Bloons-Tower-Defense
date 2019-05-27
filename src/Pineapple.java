import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Pineapple {
	private int x;
	private int y;
	private final int HEIGHT = 40;
	private final int WIDTH = 40;
	private final int RADIUS = 40;
	private final int DAMAGE = 1;
	private final Image pineapple;
	private int ticks;
	private String PATH_PREFIX = "images/";
	//need to not damage black || zebra
	public Pineapple(int x,int y) {
		pineapple = getImage("Road_Spikes.png");
		this.x=x;
		this.y=y;
		ticks=0;
		
	}
	Timer timer;
	private final int REFRESH = 1;

	public void go(JPanel panel, ArrayList<Bloon> al, Graphics g) {
		timer = new Timer(REFRESH, new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("bayus" + ticks);
                draw(g);
                if(ticks==100000) {
                	//draw image of pineapple with 2
                	draw(g);
                	panel.repaint();
                }
                if(ticks==200000) {
                	//draw image of pineapple with 1
                	draw(g);
                	panel.repaint();
                }
                if (ticks==300000){
                	draw(g);
                    explode(al);
                    panel.repaint();
                 }
                
               
                ticks++;
            }
        });
        timer.start();

	}
	public void draw(Graphics g) {
		g.drawImage(pineapple,x,y,HEIGHT,WIDTH,null);
	}
	public void explode(ArrayList<Bloon> al) {
		for(int i=al.size()-1;i>=0;i--) {
//			if(al.get(i).distance(al.get(i).getX(), al.get(i).getY(), x+HEIGHT/2, y+HEIGHT/2)<RADIUS) {
//				al.addAll(al.get(i).hit((int)al.get(i).getX(),(int)al.get(i).getY(),1));
//				al.remove(i);
//
//			}
		}
		
		
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
}