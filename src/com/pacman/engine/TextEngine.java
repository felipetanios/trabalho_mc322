package com.pacman.engine;


import java.util.Scanner;

import com.pacman.basic.Direction;
import com.pacman.basic.LabyrinthMap;


public class TextEngine extends GameEngine{
	private TextRenderManager renderManager;
	
	
	public TextEngine(LabyrinthMap labyrinthMap) {
		
		super(labyrinthMap);
		this.renderManager = new TextRenderManager(labyrinthMap.getWidht(), labyrinthMap.getHeight());
	}
	
	public Direction readCommandFromKeyboard(Scanner scanner) {
		switch(scanner.nextInt()) {
		case 1:
			return Direction.UP;
		case 2:
			return Direction.DOWN;
		case 3:
			return Direction.RIGH;
		case 4:
			return Direction.LEFT;
		default:
			throw new NullPointerException("Entrada invalida!!");
		}
	}
	public void gameLoop() {
		Scanner scanner = new Scanner(System.in);
		LabyrinthMap labMap = getLabyrinthMap();
		
		Direction newDirection;
		while(!labMap.isFinished() && !labMap.isGameOver()) {
			newDirection = null;
			renderManager.render(labMap);
			
			try {
				newDirection = readCommandFromKeyboard(scanner);
			}catch(NullPointerException  e){
			      System.out.println(e.getMessage());
			}
			
			if (newDirection != null)
				labMap.updateMap(newDirection);
		}
		System.out.println("Fim de jogo");
		scanner.close();
	}
	
}
