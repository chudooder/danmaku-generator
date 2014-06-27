package io;

import java.util.ArrayList;
import java.util.List;

/**
 * Default pattern that fires a single bullet straight forward
 * @author Shawn
 *
 */
public class DefaultPattern implements EmitterPattern {

	@Override
	public List<Location> getLocations() {
		List<Location> locs = new ArrayList<>();
		locs.add(new Location(0, 0, 0, false));
		return locs;
	}

}
