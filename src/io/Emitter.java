package io;

import java.util.List;

import org.newdawn.slick.Color;

import chu.engine.Entity;
import chu.engine.Game;
import chu.engine.anim.Renderer;

/**
 * Shoots bullets. Bullet type is determined by its BulletData field.
 * @author Shawn
 *
 */
public class Emitter extends Entity {
	
	private float angle;
	private float angVeloc;
	private String[] commands;
	private EmitterPattern pattern;
	private Behavior[] bulletBehaviors;
	private Behavior[] emmiterBehaviors;
	private int currentCmd;
	
	//For commands
	private float timer;
	private int loopCount;
	
	public Emitter(float x, float y, String[] commands, 
			EmitterPattern pattern, Behavior[] bulletBehaviors, Behavior[] emmiterBehaviors) {
		super(x, y);
		currentCmd = 0;
		timer = -1;
		loopCount = 0;
		angle = -1.5708f;
		angVeloc = 0.0f;
		this.commands = commands;
		this.pattern = pattern;
		this.bulletBehaviors = bulletBehaviors;
		this.emmiterBehaviors = emmiterBehaviors;
	}
	
	public void beginStep() {
		//Behaviors
		float dx = 0, dy = 0, da = 0;
		for(Behavior behavior : emmiterBehaviors) {
			Location loc = behavior.doBehavior(x, y, angle);
			dx += loc.x;
			dy += loc.y;
			da += loc.angle;
		}
		float delta = Game.getDeltaSeconds();
		x += dx * delta;
		y += dy * delta;
		angle += da * delta;
		
		//Parse commands
		String cmd = commands[currentCmd];
		if(cmd.startsWith("FIRE")) {
			fire();
			nextCmd();
		} else if(timer < 0 && cmd.startsWith("WAIT")) {
			float time = Float.parseFloat(cmd.split(" ")[1]);
			timer = time;
		} else if(cmd.startsWith("LOOP")) {
			String[] split = cmd.split(" ");
			loopCount++;
			if(loopCount >= Integer.parseInt(split[2])) {
				nextCmd();
				loopCount = 0;
			} else {
				currentCmd -= Integer.parseInt(split[1]);
			}
		}
		
		//Timer
		if(timer > 0) {
			timer -= Game.getDeltaSeconds();
			if(timer < 0) {
				nextCmd();
			}
		}
	}
	
	private void nextCmd() {
		currentCmd = (currentCmd + 1)%commands.length;
	}

	public void fire() {
		List<Location> locations = pattern.getLocations();
		for(Location location : locations) {
			Bullet bullet;
			Behavior[] copy = new Behavior[bulletBehaviors.length];
			for(int i=0; i<copy.length; i++) {
				copy[i] = bulletBehaviors[i].copy();
			}
			if(!location.absolute) { 
				bullet = new Bullet(
						x + location.x,
						y + location.y,
						angle + location.angle,
						copy);
			} else {
				bullet = new Bullet(
						location.x,
						location.y,
						location.angle,
						copy);
			}
			stage.addEntity(bullet);
		}
	}
	
	public void render() {
		Renderer.drawCircle(x, y, 3, 8, false, Color.blue);
	}
}
