import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TesterClass {
	private JPanel panel;
	private JFrame frame = new JFrame("Bloons Tower Defense");
	private static Pixel[][] grid;
	private int ticks = 0, numBloons = 0;
	private Timer timer, bloonsTimer;
	private static int height = 676, width = 910;

	public static void main(String[] args) {
		new TesterClass().start();
	}

	private void start() {
		BTDMap m1 = new Map1(height, width);
		TowerPanel tp = new TowerPanel(height, 145);
		m1.initializeTrack();
		grid = m1.getGrid();
		m1.addSpikes(new Spikes((int) (268 * m1.getWratio()), 100));
//		m1.addBloon(new Bloon(12,0,93,0, new HashSet<Integer>()));
//		m1.addBloon(new Bloon(10,0,93,0, new HashSet<Integer>()));
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				m1.draw(g);
//				for(int r = 0; r < grid.length; r++) {
//					for(int c = 0; c < grid[0].length; c++) {
//						if(grid[r][c].coveredByTrack()) {
//							g.drawRect(c, r, 1, 1);
//						}
//						if(grid[r][c].getAngle() != 0) {
//							g.setColor(Color.GREEN);
//							g.drawRect(c, r, 1, 1);
//							g.setColor(Color.BLACK);
//						}
//					}
//				}
				for(GameObject go: m1.getGameObjectsList()) {
					go.draw(g, panel);
				}
				tp.draw(g, m1);
			}
		};
		panel.setBackground(Color.WHITE);

		panel.setPreferredSize(new Dimension(width + 145, height));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		bloonsTimer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numBloons++;
				m1.addBloon(new Bloon(12, 0, 93, 0, new HashSet<Integer>()));
				if(numBloons == 25) {
					bloonsTimer.stop();
				}
			}
		});

		timer = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ticks++;
				ArrayList<Bloon> bloons = m1.getBloonList();
				for(int i = 0; i < spikes.size(); i++) {
					spikes.get(i).update(gameobjects,0,panel,new HashMap<>());
				}
				for(int i = 0; i < bloons.size(); i++) {
					bloons.get(i).update(gameobjects, 0.5,panel,new HashMap<>());
					if((int) bloons.get(i).getX() >= grid[0].length || (int) bloons.get(i).getY() >= grid.length) {
						m1.removeBloon(i);
						if(m1.getBloonList().size() == 0) {
							timer.stop();
							System.out.println("Done");
						}
						continue;
					}
					if(grid[(int) bloons.get(i).getY()][(int) bloons.get(i).getX()].getAngle() != bloons.get(i).getAngle()) {
						bloons.get(i).setAngle(grid[(int) bloons.get(i).getY()][(int) bloons.get(i).getX()].getAngle());
					}
				}
				panel.repaint();
//				if(ticks == 5) {
//					timer.stop();
////					frame.dispose();
//					System.exit(0);
//				}
			}
		});

		timer.start();
		bloonsTimer.start();

	}

}