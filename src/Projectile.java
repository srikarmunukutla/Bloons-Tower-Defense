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
    private int height;
    private int width;
    private Image img;
    private double angle = 40;
    boolean finish;
    private final int PROJSPEED = 1;
    private int ticks;
    private final static String PATH_PREFIX = "images/";
    
    public Projectile(int a, int b, String str, int w, int h) {
        x = a;
        y = b;
        width = w;
        height = h;
        r = new Rectangle(x, y, width, height);
        img = getImage(str);
        finish =false;
        ticks = 0;
    }

    public void draw(Graphics g, JPanel panel) {
//        Graphics2D g2d = (Graphics2D) g.create();
//        AffineTransform at = new AffineTransform();
//        at.setToRotation(Math.toRadians(angle), x, y);
//        at.translate(x, y);
//        g2d.setTransform(at);
//        g2d.drawImage(img, 0, 0, WIDTH, HEIGHT, panel);
//        g2d.dispose();
        Graphics2D g2 = (Graphics2D) g.create();// get a copy
        g2.translate(x, y);// translate this card's (x,y)
        g2.rotate(Math.toRadians(angle));// rotate around this card
        g2.drawImage(img, 0, 0, width, height, null);// draw my image on the rotated Graphics
        g2.dispose();// dispose so the other cards are not affected.
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

    protected Image getImage(String fn) {
        Image img = null;
        fn = PATH_PREFIX + fn;
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
    private int multiplier;
    private int pierce;
    public void launch(int bx, int by, JPanel panel, HashMap<Integer,Projectile> hm, int random , int dmg, ArrayList<GameObject> al, int p, Rectangle re) {
        pierce = p;
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
        if(slopey == 1){
            if (bx < x){
                multiplier = -1;
            }else{
                multiplier = 1;
            }
        }else{
            if (by < y){
                multiplier = -1;
            }else{
                multiplier = 1;
            }
        }
        timer = new Timer(REFRESH, new ActionListener() {
            double dx = x;
            double dy = y;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!re.intersects(r)){
                    hm.remove(random);
                    timer.stop();
                }
                for (int j = al.size()-1; j >= 0; j--){
                    if (al.get(j) instanceof Bloon) {
                        Bloon bloon = (Bloon) al.get(j);
                        if (bloon.getRect().intersects(r)) {
                            if (bloon.checkDart(random)) {
                                continue;
                            }
                            bloon.addDart(random);
                            al.addAll(bloon.hit((int) bx, (int) by, dmg));
                            al.remove(j);
                            pierce--;
                            if (pierce == 0) {
                                hm.remove(random);
                                for (int i = al.size() - 1; i >= 0; i--) {
                                    if (al.get(i) instanceof Bloon) {
                                        ((Bloon) al.get(i)).removeDart(random);
                                    }
                                }
                                timer.stop();
                                return;
                            }
                        }
                    }
                }
                dx += multiplier*PROJSPEED*slopey;
                dy += multiplier*PROJSPEED*slopex;
                moveTo((int) dx, (int) dy);
                ticks++;
                panel.repaint();
            }
        });
        timer.start();

    }
}