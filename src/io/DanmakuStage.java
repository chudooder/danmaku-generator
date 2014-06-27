package io;

import io.pattern.Behavior;
import io.pattern.NewtonianBehavior;
import io.pattern.PeriodicBehavior;
import io.pattern.SpreadPattern;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import chu.engine.Collidable;
import chu.engine.Entity;
import chu.engine.Stage;
import chu.engine.collision.QuadTree;

public class DanmakuStage extends Stage {
	
	public DanmakuStage() {
		super();
		String[] cmds = new String[]{"FIRE", "WAIT 0.08"};
		NewtonianBehavior behavior = new NewtonianBehavior(200, 0, 2, -1);
		behavior.minAV = 0;
		PeriodicBehavior behavior2 = new PeriodicBehavior(2, 50, false);
		Behavior[] b = new Behavior[]{behavior};
		Behavior[] b2 = new Behavior[]{behavior2};
		addEntity(new Emitter(320, 400, cmds, new SpreadPattern(6.283f, 24), b, b2));
	
		addEntity(new Player(320, 20));
	}

	@Override
	public void beginStep() {
		for (Entity e : entities) {
			e.beginStep();
		}
		processAddStack();
		processRemoveStack();
	}

	@Override
	public void onStep() {
		for (Entity e : entities) {
			e.onStep();
		}
		processAddStack();
		processRemoveStack();
		processCollisions();
	}

	@Override
	public void endStep() {
		for (Entity e : entities) {
			e.endStep();
		}
		processAddStack();
		processRemoveStack();
	}
	
	private void processCollisions() {
		//Quad Tree implementation
		//*
		QuadTree qt = new QuadTree(0, new Rectangle(0, 0, 1280, 720));
		for(Entity e : entities) {
			if(e.hitbox != null) {
				qt.insert(e);
			}
		}
		
		for(Entity e : entities) {
			if(e instanceof Collidable && e.hitbox != null) {
				List<Entity> list = new ArrayList<Entity>();
				qt.retrieve(list, e);
				for(Entity other : list) {
					if(e != other && other.hitbox.intersects(e.hitbox)) {
						((Collidable)e).doCollisionWith(other);
					}
				}
			}
		}
	}
}
