
public class Map1 extends Map {
	private int trackSize = 37;
	
	public Map1(int r, int c) {
		super(r, c);
		img = getImage("Map1.png");
	}

	public Map1() {
		super();
		img = getImage("Map1.png");
	}

	protected void initializeTrack() {
		// first portion
		for(int c = 0; c < 154; c++) {
			for(int r = 53; r < 90; r++) {
				grid[r][c] = true;
			}
		}
		
		// second portion
		for(int c = 154; c < 268; c++) {
			int r = 53;
			if(c > 193) {
				r = 140;
			}
			while(r < 177) {
				grid[r][c] = true;
				r++;
			}
		}
		
		// third portion
		for(int c = 268; c < 389; c++) {
			int r = 176;
			if(c > 304) {
				r = 90;
			}
			while(r > 53) {
				r--;
				grid[r][c] = true;
			}
		}
		
		// fourth portion
		for(int c = 389; c < 426; c++) {
			for(int r = 53; r < 275; r++) {
				grid[r][c] = true;
			}
		}
		
		// fifth portion
		for(int c = 273; c < 389; c++) {
			int r = 461;
			if(c > 309) {
				r = 276;
			}
			while(r > 239) {
				r--;
				grid[r][c] = true;
			}
		}
		
		// sixth portion
		for(int c = 309; c < 431; c++) {
			int r = 424;
			if(c > 395) {
				r = 338;
			}
			while(r < 461) {
				grid[r][c] = true;
				r++;
			}
		}
		
		// seventh portion
		for(int c = 431; c < 544; c++) {
			int r = 375;
			if(c > 506) {
				r = 461;
			}
			while(r > 338) {
				r--;
				grid[r][c] = true;
			}
		}
		
		// eight portion
		for(int c = 544; c < 700; c++) {
			for(int r = 424; r < 461; r++) {
				grid[r][c] = true;
			}
		}

	}
}
