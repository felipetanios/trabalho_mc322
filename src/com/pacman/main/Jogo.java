package com.pacman.main;

import com.pacman.basic.LabyrinthMap;
import com.pacman.basic.LabyrinthMapLoader;
import com.pacman.engine.GameEngine;
import com.pacman.engine.TextEngine;

public class Jogo {

	private static void runGame(GameEngine gameEngine) {
		gameEngine.gameLoop();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LabyrinthMap mapa = LabyrinthMapLoader.getInstance().createDefaultMap();
		runGame(new TextEngine(mapa));
	}

}
