import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Projectile {
    Rectangle r;
    private int x;
    private int y;
    private final int HEIGHT = 20;
    private final int WIDTH = 45;
    private Image img;
    private double angle = 40;

    public Projectile(int a, int b, String str) {
        x = a;
        y = b;
        r = new Rectangle(x, y, WIDTH, HEIGHT);
        img = getImage(PATH_PREFIX + str);
    }

    public void draw(Graphics g, JPanel panel) {
        Graphics2D g2d = (Graphics2D) g.create();
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(angle), x, y);
        at.translate(x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, WIDTH, HEIGHT, panel);
        g2d.dispose();
    }

    public Rectangle getRect() {
        return r;
    }

    public void setAngle(double deg) {
        angle = deg;

    }

    private void moveTo(int a, int b) {
        r.translate(a - x, b - y);
        x = a;
        y = b;
    }

    private final static String PATH_PREFIX = "images/";

    protected Image getImage(String fn) {
        Image img = null;
        try {

            img = ImageIO.read(this.getClass().getResource(fn));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    Timer timer;
    private final int REFRESH = 1;

    public void launch(Bloon b, JPanel panel, HashMap<Integer,Projectile> hm, int random , int dmg, ArrayList<Bloon> al) {
        timer = new Timer(REFRESH, new ActionListener() {
            double dx = x;
            double dy = y;

            @Override
            public void actionPerformed(ActionEvent e) {
                double slope = 1.0 * (b.getY() - y) / (b.getX() - x);
                if (b.getRect().intersects(getRect())){
                    hm.remove(random);
                    b.decreaseHealth(dmg);
                    for (int j = 0; j < al.size(); j++){
                        if (b.equals(al.get(j))){
                            if (al.get(j).getHealth() <= 0){
                                al.remove(j);
                                j--;
                            }
                        }
                    }
                }
                if (b.getX() < x) {
                    dx -= 5;
                    dy -= 5*slope;
                    setAngle(180*Math.atan(slope)/Math.PI+180);
                } else {
                    dx += 5;
                    dy += 5*slope;
                    setAngle(180*Math.atan(slope)/Math.PI);
                }
                moveTo((int) dx, (int) dy);
                panel.repaint();
            }
        });
        timer.start();

    }
}