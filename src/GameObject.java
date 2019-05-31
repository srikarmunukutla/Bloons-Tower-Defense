import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public interface GameObject {
    void update(ArrayList<GameObject> al, Pixel[][] grid, BTDMap m, double time, JPanel panel, HashMap<Integer, Projectile> gameprojectile);
    void clickedAt(BTDMap btdm);
    void draw(Graphics g, JPanel panel);
    Rectangle getImgRect();
}