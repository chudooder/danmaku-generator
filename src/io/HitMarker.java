package io;

import org.newdawn.slick.Color;

import chu.engine.Particle;
import chu.engine.anim.Renderer;

public class HitMarker extends Particle {

	public HitMarker(float x, float y, int lifetime) {
		super(x, y, lifetime);
	}
	
	public void render() {
		Renderer.drawCircle(x, y, 2, 4, false, Color.blue);
	}
	
}
