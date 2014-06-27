package io;

import chu.engine.Entity;

public interface Behavior {
	
	/**
	 * Returns the delta for the bullet
	 */
	public Location doBehavior(float x, float y, float angle);
	/**
	 * Returns a copy of this behavior
	 * @return
	 */
	public Behavior copy();
}
