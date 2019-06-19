package com.pacman.engine;

import com.pacman.basic.LabyrinthMap;

public class GameEngine {
	private LabyrinthMap labyrinthMap;
	
	public GameEngine(LabyrinthMap labyrinthMap) {
		this.labyrinthMap = labyrinthMap;
		
	}
	protected LabyrinthMap getLabyrinthMap() {
		return labyrinthMap;
		
	}
	public void gameLoop() {
		
	}
	
}
