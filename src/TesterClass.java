//import java.awt.*;
//import java.awt.event.*;
//
//import javax.swing.*;
//import java.io.*;
//import java.util.*;
//import javax.imageio.ImageIO;
//import javax.swing.Timer;
//
//public class TesterClass {
//	private int panelheight = 676;
//	private int panelwidth = 910;
//	private JPanel panel;
//	private JFrame frame = new JFrame("Bloons Tower Defense");
//	private BTDMap m1;
//	private final int SQUARESIZE = 50;
//	Timer monkey;
//	JLabel selectioncost, levelnum;
//	JButton jb;
//	long ticks = 0;
//
//	public static void main(String[] args) {
//		new TesterClass().start();
//	}
//
//	private void start() {
//		m1 = new Map1(panelheight,panelwidth);
//		panel = new JPanel() {
//			@Override
//			public void paintComponent(Graphics g) {
//				boolean monksel = false;
//				super.paintComponent(g);
//				m1.draw(g, panel);
//				for (GameObject go : m1.getGameObjectsList()) {
//					if(go instanceof Monkey && ((Monkey) go).isclicked && ((Monkey) go).hasRange() && !m1.clicked) {
//						g.setColor(new Color(0, 255, 0, 100));
//						g.fillRect(((Monkey) go).rangerect.x, ((Monkey) go).rangerect.y, ((Monkey) go).rangerect.width, ((Monkey) go).rangerect.height);
//						monksel = true;
//					}
//					go.draw(g, this);
//				}
//				jb.setVisible(monksel);
//				if (m1.getUserSelection() == null && !monksel) {
//					selectioncost.setText("Level " + m1.getLevelNum());
//				}
//				Iterator it = m1.getGameProjectilesList().entrySet().iterator();
//				while (it.hasNext()){
//					Map.Entry pair = (Map.Entry)it.next();
//					((Projectile)pair.getValue()).draw(g,this);
//				}
//				if (m1.isClicked()) {
////					m1.getUserSelection().fillRangeRect(g, m1.isValid());
//					if(m1.isValid()) {
//			    		g.setColor(new Color(0, 255, 0, 100));
//			    	}
//			    	else {
//			    		g.setColor(new Color(255, 0, 0, 100));
//			    	}
//					if(m1.getUserSelection().hasRange()) {
//						g.fillRect((int) m1.getUserSelection().getRangeRect().getX(), (int) m1.getUserSelection().getRangeRect().getY(), (int) m1.getUserSelection().getRangeRect().getWidth(), (int) m1.getUserSelection().getRangeRect().getHeight());
//					}
//					g.drawImage(m1.getUserSelection().getImg(), m1.getUserX(), m1.getUserY(), m1.getUserSelection().width, m1.getUserSelection().height, null);
//				}
//			}
//		};
//		panel.setLayout(null);
//		jb = new JButton("Sell");
//		jb.setBounds(m1.getWidth() + 10,625,100,50);
//		jb.setVisible(false);
//		jb.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ArrayList<GameObject> al = m1.getGameObjectsList();
//				for (int i = al.size()-1; i >= 0; i--){
//					if (al.get(i) instanceof Monkey && ((Monkey)(al.get(i))).isclicked){
//						m1.increaseMoney((int) (((Monkey)al.get(i)).getCost() * 0.8));
//						m1.flipCover(((Monkey) al.get(i)).getImgRect());
//						al.remove(i);
//						break;
//					}
//				}
//			}
//		});
//		panel.add(jb);
//		selectioncost = new JLabel("");
//		selectioncost.setFont(new Font("Serif",Font.PLAIN,24));
//		selectioncost.setForeground(Color.WHITE);
//		selectioncost.setBounds(panelwidth*920/910,panelheight*600/676,200,100);
//		panel.add(selectioncost);
//		panel.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent me) {
//				ArrayList<GameObject> alobj = m1.getGameObjectsList();
//				for(int i = alobj.size()-1; i >= 0; i--) {
//					if(alobj.get(i) instanceof Monkey && ((Monkey) alobj.get(i)).getImgRect().contains(me.getX(), me.getY())) {
//						((Monkey) alobj.get(i)).setClicked();
//					}
//					else if(alobj.get(i) instanceof Monkey && ((Monkey) alobj.get(i)).isclicked) {
//						((Monkey) alobj.get(i)).setClicked();
//					}
//					if(alobj.get(i).getImgRect().contains(me.getX(),me.getY())) {
//						alobj.get(i).clickedAt(m1);
//					}
//				}
//				m1.clickedAt(me);
//				if (m1.getUserSelection() != null){
//					selectioncost.setText("Cost: " + m1.getUserSelection().getCost());
//				}else{
//					selectioncost.setText("");
//				}
//				panel.repaint();
//			}
//		});
//		panel.addMouseMotionListener(new MouseMotionAdapter(){
//			public void mouseMoved(MouseEvent me){
//				m1.mouseMoved(me);
//				panel.repaint();
//			}
//		});
//		
//		panel.setBackground(Color.WHITE);
//
//		panel.setPreferredSize(new Dimension(panelwidth + TowerPanel.truewidth, panelheight));
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//		frame.add(panel);
//		frame.pack();
//		frame.setVisible(true);
//		
//		monkey = new Timer(1, new ActionListener() {
//			int seconds = 5;
//			@Override
//			public void actionPerformed(ActionEvent e) {
//			for (int i = 0; i < m1.getGameObjectsList().size(); i++){
//					m1.getGameObjectsList().get(i).update(m1.getGameObjectsList(), m1.getGrid(),m1,0.15*(new FastForward()).speedrate, panel, m1.getGameProjectilesList());
//					if(m1.getHealth() <= 0) {
//						monkey.stop();
//						Timer stopTimer = new Timer(1000, new ActionListener() {
//							@Override
//							public void actionPerformed(ActionEvent e) {
//								seconds--;
//								if(seconds == 0) {
//									System.exit(0);
//								}
//							}
//						});
//						stopTimer.start();
//						JOptionPane.showMessageDialog(null, "You ran out of lives on level " + m1.getLevelNum() + "! :(\n"
//						+ "The game will close in " + seconds + " seconds.");
//					}
////					if (m1.getGameObjectsList().get(i) instanceof Spikes) {
////						if (((Spikes) m1.getGameObjectsList().get(i)).getHealth() == 0) {
////							m1.getGameObjectsList().remove(i);
////							i--;
////						}
////					}
//				}
//				panel.repaint();
//				ticks++;
//			}
//
//		});
//		monkey.start();
//
//	}
//}