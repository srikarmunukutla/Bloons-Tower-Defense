import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Map.Entry;
import javax.swing.Timer;

public class BTDGameRunner {
	private int panelheight = 676;
	private int panelwidth = 910;
	private JPanel panel;
	private JFrame frame = new JFrame("Bloons Tower Defense");
	private JLabel levelandcost;
	private JButton sellbutton;
	private BTDMap m1;
	private Timer monkey;

	public static void main(String[] args) {
		new BTDGameRunner().start();
	}

	private void start() {
		m1 = new Map1(panelheight,panelwidth);
		
		panel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				boolean monksel = false;
				super.paintComponent(g);
				m1.draw(g, panel);
				for (GameObject go : m1.getGameObjectsList()) {
					if(go instanceof Monkey && ((Monkey) go).isclicked && !m1.clicked) {
						monksel = true;
						if(((Monkey) go).hasRange()) {
							g.setColor(new Color(0, 255, 0, 100));
							g.fillRect(((Monkey) go).rangerect.x, ((Monkey) go).rangerect.y, ((Monkey) go).rangerect.width, ((Monkey) go).rangerect.height);
						}
					}
					go.draw(g, this);
				}
				sellbutton.setVisible(monksel);
				if (m1.getUserSelection() == null && !monksel) {
					levelandcost.setText("Level " + m1.getLevelNum());
				}
				Iterator<Entry<Integer, Projectile>> it = m1.getGameProjectilesList().entrySet().iterator();
				while (it.hasNext()){
					Map.Entry<Integer, Projectile> pair = (Map.Entry<Integer, Projectile>) it.next();
					((Projectile)pair.getValue()).draw(g,this);
				}
				if (m1.isClicked()) {
//					m1.getUserSelection().fillRangeRect(g, m1.isValid());
					if(m1.isValid()) {
			    		g.setColor(new Color(0, 255, 0, 100));
			    	}
			    	else {
			    		g.setColor(new Color(255, 0, 0, 100));
			    	}
					if(m1.getUserSelection().hasRange()) {
						g.fillRect((int) m1.getUserSelection().getRangeRect().getX(), (int) m1.getUserSelection().getRangeRect().getY(), (int) m1.getUserSelection().getRangeRect().getWidth(), (int) m1.getUserSelection().getRangeRect().getHeight());
					}
					g.drawImage(m1.getUserSelection().getImg(), m1.getUserX(), m1.getUserY(), m1.getUserSelection().width, m1.getUserSelection().height, null);
				}
			}
		};
		panel.setLayout(null);
		
		sellbutton = new JButton("Sell");
		sellbutton.setBounds(m1.getWidth() + 10,625,100,50);
		sellbutton.setVisible(false);
		sellbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<GameObject> al = m1.getGameObjectsList();
				for (int i = al.size()-1; i >= 0; i--) {
					if (al.get(i) instanceof Monkey && ((Monkey)(al.get(i))).isclicked) {
						m1.increaseMoney((int) (((Monkey)al.get(i)).getCost() * 0.8));
						m1.flipCover(((Monkey) al.get(i)).getImgRect());
						al.remove(i);
						break;
					}
				}
			}
		});
		panel.add(sellbutton);
		
		levelandcost = new JLabel("");
		levelandcost.setFont(new Font("Serif", Font.PLAIN, 24));
		levelandcost.setForeground(Color.WHITE);
		levelandcost.setBounds(panelwidth * 920/910, panelheight * 600/676, 200, 100);
		panel.add(levelandcost);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				ArrayList<GameObject> alobj = m1.getGameObjectsList();
				for(int i = alobj.size()-1; i >= 0; i--) {
					if(alobj.get(i).getImgRect().contains(me.getX(), me.getY()) || (alobj.get(i) instanceof Monkey && ((Monkey) alobj.get(i)).isclicked)) {
						alobj.get(i).clickedAt(m1);
					}
				}
				m1.clickedAt(me);
				if (m1.getUserSelection() != null) {
					levelandcost.setText("Cost: " + m1.getUserSelection().getCost());
				}
				else {
					levelandcost.setText("");
				}
				panel.repaint();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent me) {
				m1.mouseMoved(me);
				panel.repaint();
			}
		});
		
		panel.setBackground(Color.WHITE);

		panel.setPreferredSize(new Dimension(panelwidth + TowerPanel.truewidth, panelheight));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		monkey = new Timer(1, new ActionListener() {
			int seconds = 5;
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < m1.getGameObjectsList().size(); i++) {
					m1.getGameObjectsList().get(i).update(m1.getGameObjectsList(), m1.getGrid(), m1, 0.15 * FastForward.speedrate, panel, m1.getGameProjectilesList());
					if(m1.getHealth() <= 0) {
						monkey.stop();
						Timer stopTimer = new Timer(1000, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								seconds--;
								if(seconds == 0) {
									System.exit(0);
								}
							}
						});
						stopTimer.start();
						JOptionPane.showMessageDialog(null, "You ran out of lives on level " + m1.getLevelNum() + "! :(\n"
						+ "The game will close in " + seconds + " seconds.");
					}
//					if (m1.getGameObjectsList().get(i) instanceof Spikes) {
//						if (((Spikes) m1.getGameObjectsList().get(i)).getHealth() == 0) {
//							m1.getGameObjectsList().remove(i);
//							i--;
//						}
//					}
				}
				panel.repaint();
			}

		});
		monkey.start();

	}
}