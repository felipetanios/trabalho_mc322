package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public class PrestigiousGhost extends Ghost{
	private boolean dead = false;

	PrestigiousGhost(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
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
