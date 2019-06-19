package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public class Checkpoint extends LabyrinthObject{
	private boolean conquered;
	
	Checkpoint(int x, int y) {
		super(x, y);
		this.conquered = false;
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
