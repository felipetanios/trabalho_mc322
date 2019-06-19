package com.pacman.basic;

import java.util.ArrayList;

public class LabyrinthMapLoader {
	private static LabyrinthMapLoader INSTANCE;
	
	private LabyrinthMapLoader() {
		
	}
	public static LabyrinthMapLoader getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new LabyrinthMapLoader();
		}
		return INSTANCE;
	}
	public LabyrinthMap loadMapFromFile(String path) {
		return null;
		
	}
	public LabyrinthMap createDefaultMap() {
		int width = 10;
		int height = 10;
		Player player = new Player(5, 5);
		ArrayList<Wall> walls = new ArrayList<Wall>();
		Checkpoint[] checkpoints = new Checkpoint[4];
		int i;
		
		//cria a parte de cima e de baixo
		for (i = 0; i < width; i++) {
			Wall wall1 = new Wall(i, 0);
			Wall wall2 = new Wall(i, height-1);
			walls.add(wall1);
			walls.add(wall2);
		}
		int j;
		//cria os contornos
		for (j = 0; j < height-2; j++) {
			Wall wall1 = new Wall(0, j+1);
			Wall wall2 = new Wall(width -1, j+1);
			walls.add(wall1);
			walls.add(wall2);
		}
		//cria as 2 paredes do meio
		for (int k = 0; k < 3; k++) {
			Wall wall1 = new Wall(3, k+3);
			Wall wall2 = new Wall(width-3, k+3);
			walls.add(wall1);
			walls.add(wall2);
		}
		//cria os checkpoints
		checkpoints[0] = new Checkpoint(1, 1);
		checkpoints[1] = new Checkpoint(1, height-2);
		checkpoints[2] = new Checkpoint(width -2 , 1);
		checkpoints[3] = new Checkpoint(width -2 , height-2);
		
		Wall[] wallsArr = new Wall[walls.size()];
		wallsArr = walls.toArray(wallsArr);
		
		LabyrinthMap labyrinth = new LabyrinthMap(width,  height,  player,  checkpoints,  wallsArr);
		return labyrinth;
	}
}
