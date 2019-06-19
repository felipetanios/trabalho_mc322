package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public abstract class Ghost extends LabyrinthObject{

	Ghost(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract void accept(LabyrinthObjectVisitor visitor);
	
	public abstract void move();

}
