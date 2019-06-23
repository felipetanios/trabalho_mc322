package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public abstract class Ghost extends LabyrinthObject{
	protected static final int points = 100;

	Ghost(int x, int y) {
		super(x, y);
	}

	@Override
	public abstract void accept(LabyrinthObjectVisitor visitor);
	public abstract int getPoints();	
	public abstract boolean isDead();
	public abstract void killGhost();
	public abstract void move(LabyrinthMap map);
}
