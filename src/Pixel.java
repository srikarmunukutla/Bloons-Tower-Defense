
public class Pixel {
	private boolean iscovered;
	private int angle;
	
	public Pixel(int theta, boolean bool) {
		angle = theta;
		iscovered = bool;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public boolean coveredUp() {
		return iscovered;
	}
	
	public void setAngle(int theta) {
		angle = theta;
	}
	
	public void coverUp() {
		iscovered = true;
	}
	
	public void setPixel(int theta, boolean bool) {
		angle = theta;
		iscovered = bool;
	}
}