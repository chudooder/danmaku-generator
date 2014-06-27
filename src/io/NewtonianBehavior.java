package io;

import chu.engine.Game;

/**
 * Bullet behavior that uses linear and angular velocities
 * @author Shawn
 *
 */
public class NewtonianBehavior implements Behavior {
	
	public float linVeloc;
	public float linAccel;
	public float angVeloc;
	public float angAccel;
	public float minLV;
	public float maxLV;
	public float minAV;
	public float maxAV;
	
	public NewtonianBehavior(float linVeloc, float linAccel, float angVeloc,
			float angAccel) {
		super();
		this.linVeloc = linVeloc;
		this.linAccel = linAccel;
		this.angVeloc = angVeloc;
		this.angAccel = angAccel;
		this.minLV = Float.NEGATIVE_INFINITY;
		this.maxLV = Float.POSITIVE_INFINITY;
		this.minAV = Float.NEGATIVE_INFINITY;
		this.maxAV = Float.POSITIVE_INFINITY;
	}

	@Override
	public Location doBehavior(float x, float y, float angle) {
		/* Bullet velocity and position */
		float delta = Game.getDeltaSeconds();
		linVeloc += linAccel*delta;
		angVeloc += angAccel*delta;
		linVeloc = clamp(linVeloc, minLV, maxLV);
		angVeloc = clamp(angVeloc, minAV, maxAV);
		float dx = (float) (linVeloc * Math.cos(angle));
		float dy = (float) (linVeloc * Math.sin(angle));
		return new Location(dx, dy, angVeloc, false);
	}
	
	private float clamp(float val, float min, float max) {
		return Math.max(min, Math.min(max, val));
	}
	
	public Behavior copy() {
		NewtonianBehavior nb = new NewtonianBehavior(
				linVeloc, linAccel, angVeloc, angAccel);
		nb.minLV = minLV;
		nb.minAV = minAV;
		nb.maxLV = maxLV;
		nb.maxAV = maxAV;
		return nb;
	}

}
