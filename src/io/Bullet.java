package io;

import org.newdawn.slick.opengl.Texture;

import chu.engine.Entity;
import chu.engine.Game;
import chu.engine.Resources;
import chu.engine.anim.Renderer;
import chu.engine.anim.Transform;

public class Bullet extends Entity {
	
	private static Texture bulletTex;
	
	private float linVeloc;
	private float linAccel;
	private float angVeloc;
	private float angAccel;
	private float minLV, maxLV, minAV, maxAV;
	private float angle;
	
	static {
		bulletTex = Resources.getTexture("res/img/bullet.png");
	}

	public Bullet(float x, float y, float angle, BulletData initData) {
		super(x, y);
		linVeloc = initData.linVeloc;
		linAccel = initData.linAccel;
		angVeloc = initData.angVeloc;
		angAccel = initData.angAccel;
		minLV = initData.minLV;
		maxLV = initData.maxLV;
		minAV = initData.minAV;
		maxAV = initData.maxAV;
		this.angle = angle;
	}
	
	public void beginStep() {
		/* Bullet velocity and position */
		float delta = Game.getDeltaSeconds();
		linVeloc += linAccel*delta;
		angVeloc += angAccel*delta;
		linVeloc = clamp(linVeloc, minLV, maxLV);
		angVeloc = clamp(angVeloc, minAV, maxAV);
		angle += angVeloc*delta;
		x += linVeloc * Math.cos(angle) * delta;
		y += linVeloc * Math.sin(angle) * delta;
	}
	
	public void endStep() {
		if(x < 0 || x > 640 || y < 0 || y > 480) {
			destroy();
		}
	}
	
	private float clamp(float val, float min, float max) {
		return Math.max(min, Math.min(max, val));
	}
	
	public void render() {
		Transform t = new Transform();
		t.setRotation(angle);
		Renderer.render(bulletTex, 0, 0, 1, 1, x-8, y-8, x+8, y+8, renderDepth, t);
	}

}
