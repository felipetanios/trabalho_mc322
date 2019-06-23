package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public class Fruits extends LabyrinthObject{
	private boolean conquered;
	private static final int points = 10;
	
	Fruits(int x, int y) {
		super(x, y);
		this.conquered = false;
	}
	public int getPoints() {
		return points;
	}
	public boolean isConquered() {
		return this.conquered;
	}
	void conquer() {
		this.conquered = true;
	}
	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);
	}
}
