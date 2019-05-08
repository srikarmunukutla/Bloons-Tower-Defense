
public class Map1 extends BTDMap {
	private int trackSize = (int) (37 * Hratio);
	
	public Map1(int r, int c) {
		super(r, c);
		img = getImage("Map1.png");
	}
	
	public int getTrackSize() {
		return trackSize;
	}

	protected void initializeTrack() {
		// first portion
		for(int c = 0; c < (int) (154 * Wratio); c++) {
			for(int r = (int) (53 * Hratio); r < (int) (90 * Hratio); r++) {
				grid[r][c].setTrack(true);
			}
		}
		
		// second portion
		for(int c = (int) (154 * Wratio); c < (int) (268 * Wratio); c++) {
			int r = (int) (53 * Hratio);
			if(c > (int) (193 * Wratio)) {
				r = (int) (140 * Hratio);
			}
			while(r < (int) (177 * Hratio)) {
				grid[r][c].setTrack(true);
				r++;
			}
		}
		
		// third portion
		for(int c = (int) (268 * Wratio); c < (int) (389 * Wratio); c++) {
			int r = (int) (177 * Hratio);
			if(c > (int) (304 * Wratio)) {
				r = (int) (90 * Hratio);
			}
			while(r > (int) (53 * Hratio)) {
				r--;
				grid[r][c].setTrack(true);
			}
		}
		
		// fourth portion
		for(int c = (int) (389 * Wratio); c < (int) (426 * Wratio); c++) {
			for(int r = (int) (53 * Hratio); r < (int) (276 * Hratio); r++) {
				grid[r][c].setTrack(true);
			}
		}
		
		// fifth portion
		for(int c = (int) (273 * Wratio); c < (int) (389 * Wratio); c++) {
			int r = (int) (461 * Hratio);
			if(c > (int) (309 * Wratio)) {
				r = (int) (276 * Hratio);
			}
			while(r > (int) (239 * Hratio)) {
				r--;
				grid[r][c].setTrack(true);
			}
		}
		
		// sixth portion
		for(int c = (int) (309 * Wratio); c < (int) (431 * Wratio); c++) {
			int r = (int) (424 * Hratio);
			if(c > (int) (395 * Wratio)) {
				r = (int) (338 * Hratio);
			}
			while(r < (int) (461 * Hratio)) {
				grid[r][c].setTrack(true);
				r++;
			}
		}
		
		// seventh portion
		for(int c = (int) (431 * Wratio); c < (int) (544 * Wratio); c++) {
			int r = (int) (375 * Hratio);
			if(c > (int) (506 * Wratio)) {
				r = (int) (461 * Hratio);
			}
			while(r > (int) (338 * Hratio)) {
				r--;
				grid[r][c].setTrack(true);
			}
		}
		
		// eight portion
		for(int c = (int) (544 * Wratio); c < (int) (700 * Wratio); c++) {
			for(int r = (int) (424 * Hratio); r < (int) (461 * Hratio); r++) {
				grid[r][c].setTrack(true);
			}
		}

	}
}
