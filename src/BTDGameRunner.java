import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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


	private void start() {
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
//		b.setBounds
		panel.setBackground(Color.WHITE);
		
		panel.setPreferredSize(new Dimension(700, 520));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
//		grid = new boolean[10][18];
		
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
