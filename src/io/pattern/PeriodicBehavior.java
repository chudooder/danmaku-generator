package io.pattern;

import io.Location;
import chu.engine.Game;

/**
 * Sinusoidal movement
 * @author Shawn
 *
 */
public class PeriodicBehavior implements Behavior {
	
	public float period;
	public float amplitude;
	public boolean parallel;
	private float timer;
	
	public PeriodicBehavior(float period, float amplitude, boolean parallel) {
		this.period = period;
		this.amplitude = amplitude;
		this.parallel = parallel;
		timer = 0;
	}

	@Override
	public Location doBehavior(float x, float y, float angle) {
		float delta = Game.getDeltaSeconds();
		float moveAngle = parallel ? angle : angle + 1.570f;
		float v = (float) (amplitude / period * 6.283f * 
				Math.cos(6.283f / period * timer));
		float dx = (float) (v * Math.cos(moveAngle));
		float dy = (float) (v * Math.sin(moveAngle));
		timer += delta;
		return new Location(dx, dy, 0, false);
	}

	@Override
	public Behavior copy() {
		return new PeriodicBehavior(period, amplitude, parallel);
	}

}
