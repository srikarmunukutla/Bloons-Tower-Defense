import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Banana {
	int money;
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
