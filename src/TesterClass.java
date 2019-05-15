//import java.awt.*;
//import java.awt.event.*;
//import java.util.*;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.Timer;
//
//public class TesterClass {
//	private JPanel panel;
//	private JFrame frame = new JFrame("Bloons Tower Defense");
//	private static Pixel[][] grid;
//	private int ticks = 0, numBloons = 0;
//	private Timer timer, bloonsTimer;
//	private static int height = 676, width = 910;
//	private Image userselection;
//	private int userx,usery = 0;
//	private boolean clicked = false;
//	private final int SQUARESIZE = 50;
//	BTDMap m1 = new Map1(height, width);
//	
//	public static void main(String[] args) {
//		new TesterClass().start();
//	}
//
//	private void start() {
//		TowerPanel tp = new TowerPanel(height, 145);
//		m1.initializeTrack();
//		grid = m1.getGrid();
////		m1.addSpikes(new Spikes((int) (268 * m1.getWratio()), 100));
////		m1.addBloon(new Bloon(12,0,93,0, new HashSet<Integer>()));
////		m1.addBloon(new Bloon(10,0,93,0, new HashSet<Integer>()));
//		panel = new JPanel() {
//			@Override
//			public void paintComponent(Graphics g) {
//				super.paintComponent(g);
//				g.setColor(Color.BLACK);
//				m1.draw(g);
////				for(int r = 0; r < grid.length; r++) {
////					for(int c = 0; c < grid[0].length; c++) {
////						if(grid[r][c].coveredByTrack()) {
////							g.drawRect(c, r, 1, 1);
////						}
////						if(grid[r][c].getAngle() != 0) {
////							g.setColor(Color.GREEN);
////							g.drawRect(c, r, 1, 1);
////							g.setColor(Color.BLACK);
////						}
////					}
////				}
//				for(GameObject go: m1.getGameObjectsList()) {
//					go.draw(g, panel);
//				}
//				if (clicked){
//					g.drawImage(userselection,userx,usery,SQUARESIZE,SQUARESIZE,null);
//				}
//				tp.draw(g, m1);
//			}
//		};
//		panel.setBackground(Color.WHITE);
//		panel.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent me) {
//				clickedAt(me);
//				panel.repaint();
//			}
//		});
//		panel.addMouseMotionListener(new MouseMotionAdapter(){
//			public void mouseMoved(MouseEvent me){
//				if (clicked) {
//					userx = me.getX()-SQUARESIZE/2;
//					usery = me.getY()-SQUARESIZE/2;
//					panel.repaint();
//
//				}
//			}
//		});
//		panel.setPreferredSize(new Dimension(width + 145, height));
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//		frame.add(panel);
//		frame.pack();
//		frame.setVisible(true);
//
//		bloonsTimer = new Timer(500, new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				numBloons++;
//				m1.addBloon(new Bloon(1, 0, 93, 0, new HashSet<Integer>()));
//				if(numBloons == 25) {
//					bloonsTimer.stop();
//				}
//			}
//		});
//
//		timer = new Timer(1, new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ticks++;
//				ArrayList<GameObject> gameobjects = m1.getGameObjectsList();
//				for(int i = 0; i < gameobjects.size(); i++) {
//					if(gameobjects.get(i) instanceof Spikes) {
//						gameobjects.get(i).update(gameobjects, 0, panel, new HashMap<>());
//					}
//					if(gameobjects.get(i) instanceof Bloon) {
//						gameobjects.get(i).update(gameobjects, 0.5, panel, new HashMap<>());
//						int x = (int) ((Bloon) gameobjects.get(i)).getX();
//						int y = (int) ((Bloon) gameobjects.get(i)).getY();
//						if(x >= grid[0].length || y >= grid.length) {
//							m1.removeBloon(i);
//							if(m1.getBloonsList().size() == 0) {
//								timer.stop();
//								System.out.println("Done");
//							}
//							continue;
//						}
//						if(grid[y][x].getAngle() != ((Bloon) gameobjects.get(i)).getAngle()) {
//							((Bloon) gameobjects.get(i)).setAngle(grid[y][x].getAngle());
//						}
//					}
//					
//				}
//				panel.repaint();
////				if(ticks == 5) {
////					timer.stop();
//////					frame.dispose();
////					System.exit(0);
////				}
//			}
//		});
//
//		timer.start();
//		bloonsTimer.start();
//
//	}
//	private void clickedAt(MouseEvent me){
//		if (!clicked){
//			userselection = new SniperMonkey(me.getX(),me.getY()).getImg();
//			userx = me.getX()-SQUARESIZE/2;
//			usery = me.getY()-SQUARESIZE/2;
//		}else{
//			m1.gameobjects.add(new SniperMonkey(me.getX(),me.getY()));
//		}
//		clicked = !clicked;
//	}
//
//}