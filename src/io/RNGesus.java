package io;

import java.util.Random;

/**
 * Praise be RNGesus, who hath delivered us
 * from the folly of determinism
 * @author Shawn
 *
 */
public class RNGesus {
	
	private static RNGesus instance;
	private Random random;
	
	/**
	 * The one true savior
	 * @return
	 */
	public RNGesus get() {
		return instance;
	}
	
	/**
	 * May He have mercy upon us
	 * @param chance
	 * @return
	 */
	public boolean roll(int chance) {
		return random.nextInt(100) < chance-1;
	}
	
	/**
	 * Float (above water)
	 * @param lower
	 * @param upper
	 * @return
	 */
	public float getFloat(float lower, float upper) {
		return (upper-lower)*random.nextFloat() + lower;
	}
	
	/**
	 * I don't have a good joke for this
	 * @param lower
	 * @param upper
	 * @return
	 */
	public int getInt(int lower, int upper) {
		return (upper-lower)*random.nextInt() + lower;
	}
}
