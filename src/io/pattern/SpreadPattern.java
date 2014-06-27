package io.pattern;

import io.Location;

import java.util.ArrayList;
import java.util.List;

public class SpreadPattern implements EmitterPattern {
	
	// Number of degrees
	public float spread;
	public int numBullets;
	
	public SpreadPattern(float spread, int numBullets) {
		this.spread = spread;
		this.numBullets = numBullets;
	}

	@Override
	public List<Location> getLocations() {
		float k = spread/numBullets;
		float j = -spread/2;
		List<Location> locations = new ArrayList<>();
		for(int i=0; i<numBullets; i++) {
			Location loc = new Location(0, 0, j, false);
			locations.add(loc);
			j += k;
		}
		return locations;
	}

}
