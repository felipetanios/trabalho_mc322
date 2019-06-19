package com.pacman.engine;

import com.pacman.basic.*;

public interface LabyrinthObjectVisitor {
	public void visit(Player player);
	public void visit(Wall wall);
	public void visit(Checkpoint checkpoint);
}
