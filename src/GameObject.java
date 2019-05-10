import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public interface GameObject{
    void update(ArrayList<Bloon> bloonal, ArrayList<Spikes> spikeal, double time, JPanel panel, HashMap<Integer,Projectile> gameprojectile);
    void clickedAt();
    void draw(Graphics g, JPanel panel);
}
