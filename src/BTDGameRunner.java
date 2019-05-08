import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class BTDGameRunner {
	private JPanel panel;
	private JFrame frame = new JFrame("Bloons Tower Defense");
	private static final String PATH_PREFIX = "images/";
	HashMap<Integer,Projectile> gameprojectiles = new HashMap<Integer, Projectile>();
	private ArrayList<Bloon> bloonal = new ArrayList<Bloon>();
	ArrayList<Monkey> monkeyal = new ArrayList<Monkey>();
	private ArrayList<Spikes> spikeal = new ArrayList<Spikes>();

	private boolean startedGame = false;
//	private int width = 18, height = 10;
	private Spikes spik;
	public static void main(String[] args) {
		new BTDGameRunner().start();
	}

	Timer monkey;
	long ticks = 0;
	private void start() {
		//Testing balloon and Monkey
		bloonal.add(new Bloon(10,30,200,0,new HashSet<Integer>()));
		monkeyal.add(new NinjaMonkey(100,100));
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Iterator it = gameprojectiles.entrySet().iterator();
				for (Bloon bl : bloonal) {
					if (bl.getHealth() > 0) {
						bl.draw(g);
					}
				}
				for (Monkey mo : monkeyal){
					mo.draw(g,this);
				}
				for (Spikes sp : spikeal){
					sp.draw(g,this);
				}
				while (it.hasNext()){
					Map.Entry pair = (Map.Entry)it.next();
					((Projectile)pair.getValue()).draw(g,this);
				}
			}
		};
		panel.setBackground(Color.WHITE);

		panel.setPreferredSize(new Dimension(910, 676));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);


		monkey = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Monkey mo : monkeyal) {
					mo.target(bloonal, panel, gameprojectiles);
				}
				for (Bloon bl : bloonal) {
					bl.update(0.05);
					System.out.println(bl.getHealth());
				}
				for (int i = 0; i < spikeal.size(); i++){
					spikeal.get(i).collided(bloonal,spikeal);
					if (spikeal.get(i).getHealth() == 0){
						spikeal.remove(i);
						i--;
					}
				}
				panel.repaint();
				ticks++;
			}

		});
		monkey.start();

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