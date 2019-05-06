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
    boolean finish;
    private final int PROJSPEED = 20;
    private int ticks;
    public Projectile(int a, int b, String str) {
        x = a;
        y = b;
        r = new Rectangle(x, y, WIDTH, HEIGHT);
        img = getImage(PATH_PREFIX + str);
        finish =false;
        ticks = 0;
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
    private double slopex;
    private double slopey;
    public void launch(int bx, int by, JPanel panel, HashMap<Integer,Projectile> hm, int random , int dmg, ArrayList<Bloon> bloons) {
        if (Math.abs(by-y) < Math.abs(bx-x)){
            slopex = 1.0 * (by - y) / (bx - x);
            slopey = 1;
        }else{
            slopey = 1.0*(bx-x) / (by-y);
            slopex = 1;
        }
        if (bx < x){
            if (slopey == 1) {
                setAngle(180 * Math.atan(slopex) / Math.PI + 180);
            }else{
                setAngle(180 * Math.atan(1/slopey) / Math.PI + 180);
            }
        }else {
            if (slopey == 1) {
                setAngle(180 * Math.atan(slopex) / Math.PI);
            } else {
                setAngle(180 * Math.atan(1 / slopey) / Math.PI );
            }
        }
        timer = new Timer(REFRESH, new ActionListener() {
            double dx = x;
            double dy = y;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!finish){
                    for (int j = bloons.size()-1; j >= 0; j--){
                        if (bloons.get(j).getRect().intersects(r)){
                            hm.remove(random);
                            bloons.addAll(bloons.get(j).hit((int)bx,(int)by,dmg));
                            bloons.remove(j);
                            finish = true;
                            return;
                        }
                    }
                }


                if(slopey == 1){
                    if (bx < x){
                        dx -= PROJSPEED*slopey;
                        dy -= PROJSPEED*slopex;
                    }else{
                        dx += PROJSPEED*slopey;
                        dy += PROJSPEED*slopex;
                    }
                }else{
                    if (by < y){
                        dx -= PROJSPEED*slopey;
                        dy -= PROJSPEED*slopex;
                    }else{
                        dx += PROJSPEED*slopey;
                        dy += PROJSPEED*slopex;
                    }
                }

                moveTo((int) dx, (int) dy);
                ticks++;
                panel.repaint();
            }
        });
        timer.start();

    }
}