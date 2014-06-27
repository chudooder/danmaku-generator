package io;

import java.util.List;

import chu.engine.Entity;
import chu.engine.Game;

/**
 * Shoots bullets. Bullet type is determined by its BulletData field.
 * @author Shawn
 *
 */
public class Emitter extends Entity {
	
	private float angle;
	private String[] commands;
	private EmitterPattern pattern;
	private BulletData bulletData;
	private int currentCmd;
	private float timer;
	
	public Emitter(float x, float y, String[] commands, EmitterPattern pattern, BulletData bulletData) {
		super(x, y);
		currentCmd = 0;
		timer = -1;
		angle = -90;
		this.commands = commands;
		this.pattern = pattern;
		this.bulletData = bulletData;
	}
	
	public void beginStep() {
		//Parse commands
		String cmd = commands[currentCmd];
		if(cmd.startsWith("FIRE")) {
			fire();
			nextCmd();
		} else if(timer < 0 && cmd.startsWith("WAIT")) {
			float time = Float.parseFloat(cmd.split(" ")[1]);
			timer = time;
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
			if(!location.absolute) { 
				bullet = new Bullet(
						x + location.x,
						y + location.y,
						angle + location.angle,
						bulletData);
			} else {
				bullet = new Bullet(
						location.x,
						location.y,
						location.angle,
						bulletData);
			}
			stage.addEntity(bullet);
		}
	}
	
}
