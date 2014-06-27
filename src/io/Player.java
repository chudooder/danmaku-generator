package io;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import chu.engine.Collidable;
import chu.engine.Entity;
import chu.engine.Game;
import chu.engine.KeyboardEvent;
import chu.engine.Resources;
import chu.engine.anim.Renderer;
import chu.engine.anim.Sprite;
import chu.engine.collision.HitboxFactory;

public class Player extends Entity implements Collidable {
	
	private float speed;
	private static Texture shipTex;
	
	static {
		shipTex = Resources.getTexture("res/img/ship.png");
	}

	public Player(float x, float y) {
		super(x, y);
		speed = 125;
		sprite = new Sprite();
		sprite.addAnimation("DEFAULT", shipTex);
		renderDepth = 1.0f;
		hitbox = HitboxFactory.rectangle(4, 4);
	}
	
	public void beginStep() {
		List<KeyboardEvent> keyEvents = Game.getKeys();
		float dx = 0, dy = 0;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) { 
			dy += speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){ 
			dy -= speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){ 
			dx -= speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){ 
			dx += speed;
		}
		x += dx * Game.getDeltaSeconds();
		y += dy * Game.getDeltaSeconds();
		hitbox.setLocation(x+14, y+14);
	}

	@Override
	public void doCollisionWith(Entity entity) {
		if(entity instanceof Bullet) {
			System.out.println("YOU LOSER");
			stage.addEntity(new HitMarker(entity.x+6, entity.y+6, 1));
		}
	}
	
	public void render() {
		super.render();
		Renderer.drawRectangle(x+14, y+14, x+18, y+18, 0.0f, Color.red);
	}

}
