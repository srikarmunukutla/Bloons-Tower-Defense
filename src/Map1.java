
public class Map1 extends Map {
//	private int trackSize = 37;
	
	public Map1(int r, int c) {
		super(r, c);
		img = getImage("Map1.png");
	}

	protected void initializeTrack() {
		// first portion
		for(int c = 0; c < 154 * Wratio; c++) {
			for(int r = 53 * Hratio; r < 90 * Hratio; r++) {
				grid[r][c] = true;
			}
		}
		
		// second portion
		for(int c = 154 * Wratio; c < 268 * Wratio; c++) {
			int r = 53 * Hratio;
			if(c > 193 * Wratio) {
				r = 140 * Hratio;
			}
			while(r < 177 * Hratio) {
				grid[r][c] = true;
				r++;
			}
		}
		
		// third portion
		for(int c = 268 * Wratio; c < 389 * Wratio; c++) {
			int r = 177 * Hratio;
			if(c > 304 * Wratio) {
				r = 90 * Hratio;
			}
			while(r > 53 * Hratio) {
				r--;
				grid[r][c] = true;
			}
		}
		
		// fourth portion
		for(int c = 389 * Wratio; c < 426 * Wratio; c++) {
			for(int r = 53 * Hratio; r < 276 * Hratio; r++) {
				grid[r][c] = true;
			}
		}
		
		// fifth portion
		for(int c = 273 * Wratio; c < 389 * Wratio; c++) {
			int r = 461 * Hratio;
			if(c > 309 * Wratio) {
				r = 276 * Hratio;
			}
			while(r > 239 * Hratio) {
				r--;
				grid[r][c] = true;
			}
		}
		
		// sixth portion
		for(int c = 309 * Wratio; c < 431 * Wratio; c++) {
			int r = 424 * Hratio;
			if(c > 395 * Wratio) {
				r = 338 * Hratio;
			}
			while(r < 461 * Hratio) {
				grid[r][c] = true;
				r++;
			}
		}
		
		// seventh portion
		for(int c = 431 * Wratio; c < 544 * Wratio; c++) {
			int r = 375 * Hratio;
			if(c > 506 * Wratio) {
				r = 461 * Hratio;
			}
			while(r > 338 * Hratio) {
				r--;
				grid[r][c] = true;
			}
		}
		
		// eight portion
		for(int c = 544 * Wratio; c < 700 * Wratio; c++) {
			for(int r = 424 * Hratio; r < 461 * Hratio; r++) {
				grid[r][c] = true;
			}
		}

	}
}
