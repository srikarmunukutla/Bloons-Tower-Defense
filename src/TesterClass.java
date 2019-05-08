import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TesterClass {
	private JPanel panel;
	private JFrame frame = new JFrame("Bloons Tower Defense");
	private static Pixel[][] grid;
	private int ticks = 0;
	private Timer timer;
	private static int height = 676, width = 910;

	public static void main(String[] args) {
		new TesterClass().start();
	}

	private void start() {
		BTDMap m1 = new Map1(height, width);
		m1.initializeTrack();
		grid = m1.getGrid();
		m1.addBloon(new Bloon(12,30,93,0, new HashSet<Integer>()));
		m1.addBloon(new Bloon(10,30,93,0, new HashSet<Integer>()));
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
				for(Bloon b: m1.getBloonList()) {
					b.draw(g);
				}
			}
		};
		panel.setBackground(Color.WHITE);

		panel.setPreferredSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		timer = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ticks++;
				for(Bloon b: m1.getBloonList()) {
					b.update(0.25);
					if(grid[(int) b.getY()][(int) b.getX()].getAngle() != b.getAngle()) {
						b.setAngle(grid[(int) b.getY()][(int) b.getX()].getAngle());
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
		
	}

}
