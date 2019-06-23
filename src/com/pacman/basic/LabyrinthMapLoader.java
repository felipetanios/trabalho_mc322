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
		int width = 27;
		int height = 40;
		Player player = new Player(13, 31);
		ArrayList<Wall> walls = new ArrayList<Wall>();
		Fruits[] fruits = new Fruits[4];
		Ghost[] ghosts = new Ghost[4];
		Pellet[] pellets = new	 Pellet[1];
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
		//cria os as frutas especiais
		fruits[0] = new Fruits(1, 1);
		fruits[1] = new Fruits(1, height-2);
		fruits[2] = new Fruits(width -2 , 1);
		fruits[3] = new Fruits(width -3 , height-2);
		
		//cria os fantasmas
		ghosts[0] = new EvadeGhost(1, 1);
		ghosts[1] = new ChaseGhost(1, height-2);
		ghosts[2] = new PrestigiousGhost(width -2 , 1);
		ghosts[3] = new RandomGhost(width -2 , height-2);
				
		//cria os pontinhos
		pellets[0] = new Pellet(14, 31);
		
		Wall[] wallsArr = new Wall[walls.size()];
		wallsArr = walls.toArray(wallsArr);
		
		LabyrinthMap labyrinth = new LabyrinthMap(width,  height,  player,  wallsArr, ghosts, fruits, pellets);
		return labyrinth;
	}
}
