import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.imageio.ImageIO;

public class BTDGameRunner {
	private JPanel panel;
	private JFrame frame = new JFrame("Bloons Tower Defense");
	private static final String PATH_PREFIX = "images/";
	HashMap<Integer,Projectile> gameprojectiles = new HashMap<Integer, Projectile>();
	private ArrayList<Bloon> bloonal = new ArrayList<Bloon>();
	ArrayList<Monkey> monkeyal = new ArrayList<Monkey>();
	private boolean startedGame = false;
//	private int width = 18, height = 10;

	public static void main(String[] args) {
		new BTDGameRunner().start();
	}

	Timer monkey;
	long ticks = 0;
	private void start() {
		//Testing balloon and Monkey
		bloonal.add(new Bloon(5,200,300,0));
		monkeyal.add(new DartMonkey(500,400));
		monkeyal.add(new DartMonkey(600,200));
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
				while (it.hasNext()){
					Map.Entry pair = (Map.Entry)it.next();
					((Dart)pair.getValue()).draw(g,this);
				}
			}
		};
		JButton button = new JButton("Play");
		panel.setBackground(Color.WHITE);

		panel.setPreferredSize(new Dimension(1800, 960));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);


		monkey = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Monkey mo : monkeyal) {
					if (ticks%mo.getReloadRate() == 0) {
						mo.target(bloonal, panel, gameprojectiles);
					}
				}
				for (Bloon bl : bloonal) {
					bl.update(0.15);
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