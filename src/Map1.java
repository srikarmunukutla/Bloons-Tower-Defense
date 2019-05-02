
public class Map1 extends Map {
	public Map1(int r, int c) {
		super(r, c);
		img = getImage("Map1.png");
	}

	public Map1() {
		super();
		img = getImage("Map1.png");
	}

	protected void initializeTrack() {
		for(int c = 0; c < 154; c++) {
			for(int r = 53; r < 90; r++) {
				grid[r][c] = true;
			}
		}

		for(int c = 154; c < 268; c++) {
			int r = 53;
			if(c > 193) {
				r = 140;
			}
			while(r < 176) {
				grid[r][c] = true;
				r++;
			}
		}


//		for(int c = 0; c < 426; c++) {
//			if(c == 193) {
//				c = 267;
//				continue;
//			}
//			for(int r = 53; r < 90; r++) {
//				grid[r][c] = true;
//			}
//		}

	}
}
