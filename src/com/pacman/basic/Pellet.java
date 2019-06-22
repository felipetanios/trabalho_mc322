package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public class Pellet extends LabyrinthObject{
	private boolean conquered = false;
	private static final int points = 1;
	Pellet(int x, int y) {
		super(x, y);
	}
	
	public boolean isConquered() {
		return conquered;
	}
	void conquer() {
		conquered = true;
	}
	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);
	}
	public int getPoints() {
		return points;
	}
}
