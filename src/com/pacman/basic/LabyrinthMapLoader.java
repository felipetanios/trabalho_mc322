package com.pacman.basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
	public LabyrinthMap loadMapFromFile(String path) throws IOException {
		File file = new File (path) ;
		String line = null;
		int width = 0;
		int height = 0;
		int nWalls = 0, nFruits = 0, nGhosts = 0, nPellets = 0;
		Player player = null;
		ArrayList<Wall> walls = null;
		ArrayList<Fruits> fruits = null;
		ArrayList<Ghost> ghosts = null;
		ArrayList<Pellet> pellets = null;
		String ghostType = null;
		LabyrinthMap map = null;
		try {
			Scanner sc = new Scanner(file);
			width = sc.nextInt();
			height = sc.nextInt();
			player = new Player(sc.nextInt(), sc.nextInt());
			nWalls = sc.nextInt();
			walls = new ArrayList<Wall>();
			for (int i = 0; i < nWalls; i++) {
				walls.add(new Wall(sc.nextInt(), sc.nextInt()));
			}
			nFruits = sc.nextInt();
			fruits = new ArrayList<Fruits>();
			for (int i = 0; i < nFruits; i++) {
				fruits.add(new Fruits(sc.nextInt(), sc.nextInt()));
			}
			nGhosts = sc.nextInt();
			ghosts = new ArrayList<Ghost>();
			for (int i = 0; i < nGhosts; i++) {
				ghostType = sc.next();
				if(ghostType.compareTo("Prestigious") == 0) {
					ghosts.add(new PrestigiousGhost(sc.nextInt(), sc.nextInt()));
				}
				else if(ghostType.compareTo("Random") == 0) {
					ghosts.add(new RandomGhost(sc.nextInt(), sc.nextInt()));
				}
				else if(ghostType.compareTo("Chase") == 0) {
					ghosts.add(new ChaseGhost(sc.nextInt(), sc.nextInt()));
				}
				else if(ghostType.compareTo("Evade") == 0) {
					ghosts.add(new EvadeGhost(sc.nextInt(), sc.nextInt()));
				}
				else {
					System.out.println("Ghost type not available");
				}
			}
			nPellets = sc.nextInt();
			pellets = new ArrayList<Pellet>();
			for (int i = 0; i < nPellets; i++) {
				pellets.add(new Pellet(sc.nextInt(), sc.nextInt()));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Wall[] wallsArr = new Wall[nWalls];
		wallsArr = walls.toArray(wallsArr);
		
		Fruits[] fruitsArr = new Fruits[nFruits];
		fruitsArr = fruits.toArray(fruitsArr);
		
		Ghost[] ghostArr = new Ghost[nGhosts];
		ghostArr = ghosts.toArray(ghostArr);
		
		Pellet[] pelletsArr = new Pellet [nPellets];
		pelletsArr = pellets.toArray(pelletsArr);
		
		map = new LabyrinthMap(width,  height,  player,  wallsArr, ghostArr, fruitsArr, pelletsArr);;
		return map;
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
