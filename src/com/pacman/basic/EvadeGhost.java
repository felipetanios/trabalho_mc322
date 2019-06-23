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
	public boolean isDead() {
		return dead;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void move(LabyrinthMap map) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void killGhost() {
		this.dead = true;
	}
}
