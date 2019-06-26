package com.pacman.main;

import java.io.IOException;

import com.pacman.basic.LabyrinthMap;
import com.pacman.basic.LabyrinthMapLoader;
import com.pacman.engine.GameEngine;
import com.pacman.engine.TextEngine;

public class Jogo {

	private static void runGame(GameEngine gameEngine) {
		gameEngine.gameLoop();
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LabyrinthMap mapa = LabyrinthMapLoader.getInstance().createDefaultMap();
		//LabyrinthMap mapa = LabyrinthMapLoader.getInstance().loadMapFromFile("/home/ec2014/ra156075/workspace/pacman-322/mapa322.txt");
		runGame(new TextEngine(mapa));
	}

}
