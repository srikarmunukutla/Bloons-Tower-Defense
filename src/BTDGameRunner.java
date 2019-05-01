import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

public class BTDGameRunner {
	private JPanel panel;
	private JFrame frame = new JFrame("Bloons Tower Defense");
	private static final String PATH_PREFIX = "images/";
	
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
				g.drawImage(getImage("bloon1.png"), 20, 20, 20, 20, null);
//				g.drawImage(getImage("BTD Home Screen.png"), 0, 0, 815, 600, null);
			}
		};
		JButton button = new JButton("Play");
//		b.setBounds
		panel.setBackground(Color.BLACK);
		
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