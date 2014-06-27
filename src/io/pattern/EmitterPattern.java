package io.pattern;

import io.Location;

import java.util.List;

/**
 * Defines where bullets are to be spawned
 * @author Shawn
 *
 */
public interface EmitterPattern {
	public List<Location> getLocations();
}
