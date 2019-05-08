
public class Pixel {
	private boolean isTrack;
	private int angle;
	
	public Pixel(int theta, boolean bool) {
		angle = theta;
		isTrack = bool;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public boolean coveredByTrack() {
		return isTrack;
	}
	
	public void setAngle(int theta) {
		angle = theta;
	}
	
	public void setTrack(boolean bool) {
		isTrack = bool;
	}
	
	public void setPixel(int theta, boolean bool) {
		angle = theta;
		isTrack = bool;
	}
}