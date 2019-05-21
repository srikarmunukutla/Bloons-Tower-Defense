import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class BTDGameRunner {
	private int panelheight = 676;
	private int panelwidth = 910;
	private JPanel panel;
	private JFrame frame = new JFrame("Bloons Tower Defense");
	private static final String PATH_PREFIX = "images/";
	private BTDMap m1;
	Integer money = 650;
	private final int SQUARESIZE = 50;
	Timer monkey;
	long ticks = 0;

	public static void main(String[] args) {
		new BTDGameRunner().start();
	}

	private void start() {
		//Testing balloon and Monkey
		m1 = new Map1(panelheight,panelwidth);
		m1.addBloon(m1.createBloon(4));
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				m1.draw(g);
				if (m1.isClicked()){
					g.drawImage(m1.getUserSelection(),m1.getUserX(),m1.getUserY(),SQUARESIZE,SQUARESIZE,null);
				}
				for (GameObject go : m1.getGameObjectsList()){
					go.draw(g,this);
				}
				Iterator it = m1.getGameProjectilesList().entrySet().iterator();
				while (it.hasNext()){
					Map.Entry pair = (Map.Entry)it.next();
					((Projectile)pair.getValue()).draw(g,this);
				}
			}
		};
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				m1.clickedAt(me);
				panel.repaint();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent me){
				m1.mouseMoved(me);
				panel.repaint();
			}
		});
		panel.setBackground(Color.WHITE);

		panel.setPreferredSize(new Dimension(panelwidth + TowerPanel.truewidth, panelheight));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);


		monkey = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < m1.getGameObjectsList().size(); i++){
					m1.getGameObjectsList().get(i).update(m1.getGameObjectsList(), m1.getGrid(),m1,0.15, panel, m1.getGameProjectilesList());
//					if (m1.getGameObjectsList().get(i) instanceof Spikes) {
//						if (((Spikes) m1.getGameObjectsList().get(i)).getHealth() == 0) {
//							m1.getGameObjectsList().remove(i);
//							i--;
//						}
//					}
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