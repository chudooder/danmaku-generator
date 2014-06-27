package io;

import chu.engine.Entity;

public interface BulletBehavior {
	
	/**
	 * Performs the behavior on the given entity.
	 * @param e
	 */
	public void doBehavior(Entity entity);
}
