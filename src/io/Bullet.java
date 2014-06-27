package io;

import org.newdawn.slick.opengl.Texture;

import chu.engine.Entity;
import chu.engine.Game;
import chu.engine.Resources;
import chu.engine.anim.Renderer;
import chu.engine.anim.Transform;

public class Bullet extends Entity {
	
	private static Texture bulletTex;

	private float angle;
	private Behavior[] behaviors;
	
	static {
		bulletTex = Resources.getTexture("res/img/bullet.png");
	}

	public Bullet(float x, float y, float angle, Behavior[] behaviors) {
		super(x, y);
		this.angle = angle;
		this.behaviors = behaviors;
	}
	
	public void beginStep() {
		float dx = 0, dy = 0, da = 0;
		for(Behavior behavior : behaviors) {
			Location loc = behavior.doBehavior(x, y, angle);
			dx += loc.x;
			dy += loc.y;
			da += loc.angle;
		}
		float delta = Game.getDeltaSeconds();
		x += dx * delta;
		y += dy * delta;
		angle += da * delta;
	}
	
	public void endStep() {
		if(x < 0 || x > 640 || y < 0 || y > 480) {
			destroy();
		}
	}
	
	public void render() {
		Transform t = new Transform();
		t.setRotation(angle);
		Renderer.render(bulletTex, 0, 0, 1, 1, x-8, y-8, x+8, y+8, renderDepth, t);
	}

}
