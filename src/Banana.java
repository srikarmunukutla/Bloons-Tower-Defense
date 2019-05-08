import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Banana {
	int money;
    private final static String PATH_PREFIX = "images/";
	Image img = getImage(PATH_PREFIX + "Banana.png");
	public Banana(int value) {
		money = value;
	}
	
    protected Image getImage(String fn) {
        Image img = null;
        try {
            img = ImageIO.read(this.getClass().getResource(fn));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
