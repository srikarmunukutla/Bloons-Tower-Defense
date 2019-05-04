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

    public void launch(Bloon b, JPanel panel, HashMap<Integer,Projectile> hm, int random , int dmg, ArrayList<Bloon> bloons) {
        timer = new Timer(REFRESH, new ActionListener() {
            double dx = x;
            double dy = y;

            @Override
            public void actionPerformed(ActionEvent e) {
                double slopex = 0;
                double slopey = 0;
                if (Math.abs(b.getY()-y) < Math.abs(b.getX()-x)){
                    slopex = 1.0 * (b.getY() - y) / (b.getX() - x);
                    slopey = 1;
                }else{
                    slopey = 1.0*(b.getX()-x) / (b.getY()-y);
                    slopex = 1;
                }
                if (!finish && (b.getRect().intersects(getRect()) || ticks > 50)){
                    hm.remove(random);
                    for (int j = bloons.size()-1; j >= 0; j--){
                        if (b.equals(bloons.get(j))){
                            bloons.addAll(bloons.get(j).hit((int)b.getX(),(int)b.getY(),dmg));
                            bloons.remove(j);
                            break;
                        }
                    }
                    finish = true;
                }

                if(slopey == 1){
                    if (b.getX() < x){
                        dx -= PROJSPEED*slopey;
                        dy -= PROJSPEED*slopex;
                    }else{
                        dx += PROJSPEED*slopey;
                        dy += PROJSPEED*slopex;
                    }
                }else{
                    if (b.getY() < y){
                        dx -= PROJSPEED*slopey;
                        dy -= PROJSPEED*slopex;
                    }else{
                        dx += PROJSPEED*slopey;
                        dy += PROJSPEED*slopex;
                    }
                }
                if (b.getX() < x){
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
                moveTo((int) dx, (int) dy);
                ticks++;
                panel.repaint();
            }
        });
        timer.start();

    }
}