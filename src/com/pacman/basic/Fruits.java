package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public class Fruits extends LabyrinthObject{
	private boolean conquered = false;
	private static final int points = 10;
	
	Fruits(int x, int y) {
		super(x, y);
		this.conquered = false;
	}
	public int getPoints() {
		return points;
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
}
