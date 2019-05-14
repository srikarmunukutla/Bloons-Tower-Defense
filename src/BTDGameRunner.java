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
	private ArrayList<GameObject> gameobjects = new ArrayList<>();
	private HashMap<Integer,Projectile> gameprojectiles = new HashMap<Integer, Projectile>();
	private boolean startedGame = false;
	private Image userselection;
	private int userx,usery = 0;
	private boolean clicked = false;
//	private int width = 18, height = 10;
	public static void main(String[] args) {
		new BTDGameRunner().start();
	}
	private final int SQUARESIZE = 50;
	Timer monkey;
	long ticks = 0;
	private void start() {
		//Testing balloon and Monkey
		gameobjects.add(new Bloon(10,30,200,0,new HashSet<Integer>()));
		BananaFarm bf = new BananaFarm(200,200);
		ArrayList<Banana> alb = new ArrayList<Banana>();
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Iterator it = gameprojectiles.entrySet().iterator();
				if (clicked){
					g.drawImage(userselection,userx,usery,SQUARESIZE,SQUARESIZE,null);
				}
				for (GameObject go : gameobjects){
					go.draw(g,this);
				}
				while (it.hasNext()){
					Map.Entry pair = (Map.Entry)it.next();
					((Projectile)pair.getValue()).draw(g,this);
				}
			}
		};
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				clickedAt(me);
				panel.repaint();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent me){
				if (clicked) {
					userx = me.getX()-SQUARESIZE/2;
					usery = me.getY()-SQUARESIZE/2;
					panel.repaint();

				}
			}
		});
		panel.setBackground(Color.WHITE);

		panel.setPreferredSize(new Dimension(910, 676));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);


		monkey = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < gameobjects.size(); i++){
					gameobjects.get(i).update(gameobjects,0.05,panel,gameprojectiles);
					if(gameobjects.get(i) instanceof Spikes){
						if (((Spikes)gameobjects.get(i)).getHealth() == 0){
							gameobjects.remove(i);
							i--;
						}
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
	private void clickedAt(MouseEvent me){
		if (!clicked){
			userselection = new SuperMonkey(me.getX(),me.getY()).getImg();
			userx = me.getX()-SQUARESIZE/2;
			usery = me.getY()-SQUARESIZE/2;
		}else{
			gameobjects.add(new SniperMonkey(me.getX(),me.getY()));
		}
		clicked = !clicked;
	}

}