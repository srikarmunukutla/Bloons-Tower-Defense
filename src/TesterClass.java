import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TesterClass {
	private JPanel panel;
	private JFrame frame = new JFrame("Bloons Tower Defense");
	private static boolean[][] grid;
	private int ticks = 0;
	private Timer timer;

	public static void main(String[] args) {
		Map1 m1 = new Map1();
		m1.initializeTrack();
		grid = m1.getGrid();
		new TesterClass().start();
	}

	private void start() {
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				for(int r = 0; r < grid.length; r++) {
					for(int c = 0; c < grid[0].length; c++) {
						if(grid[r][c]) {
							g.drawRect(c, r, 1, 1);
						}
					}
				}
			}
		};
		panel.setBackground(Color.WHITE);

		panel.setPreferredSize(new Dimension(910, 676));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ticks++;
				if(ticks == 5) {
					timer.stop();
//					frame.dispose();
					System.exit(0);
				}
			}
		});
		
		timer.start();
		
	}

}
