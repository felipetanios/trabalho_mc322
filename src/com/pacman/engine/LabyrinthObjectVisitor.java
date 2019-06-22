package com.pacman.engine;

import com.pacman.basic.*;

public interface LabyrinthObjectVisitor {
	public void visit(Player player);
	public void visit(Wall wall);
	public void visit(Fruits fruit);
	public void visit(Pellet pellet);
	public void visit(EvadeGhost evadeGhost);
	public void visit(PrestigiousGhost prestigiousGhost);
	public void visit(RandomGhost randomGhost);
	public void visit(ChaseGhost chaseGhost);
}
