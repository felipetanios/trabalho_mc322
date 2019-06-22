package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public class EvadeGhost extends Ghost{
	private boolean dead = false;

	EvadeGhost(int x, int y) {
		super(x, y);
	}

	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void move() {
		
	}

	@Override
	public boolean isDead() {
		return dead;
	}

	@Override
	public int getPoints() {
		return points;
	}

}
