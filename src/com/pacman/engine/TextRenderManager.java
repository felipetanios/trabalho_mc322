package com.pacman.engine;

import com.pacman.basic.ChaseGhost;
import com.pacman.basic.EvadeGhost;
import com.pacman.basic.Fruits;
import com.pacman.basic.LabyrinthMap;
import com.pacman.basic.LabyrinthObject;
import com.pacman.basic.Pellet;
import com.pacman.basic.Player;
import com.pacman.basic.PrestigiousGhost;
import com.pacman.basic.RandomGhost;
import com.pacman.basic.Wall;

class TextRenderManager implements LabyrinthObjectVisitor {
	private char charMap[][];
	
	public TextRenderManager(int mapWidth, int mapHeight) {
		this.charMap = new char[mapHeight][mapWidth];
	}
	private void clearMap() {
		for(int i = 0; i < charMap.length; i++) {
			for(int j = 0; j < charMap[0].length; j++) {
				charMap[i][j] = ' ';
			}
		}
	}
	private void printMap() {
		for(int i = 0; i < charMap.length; i++) {
			for(int j = 0; j < charMap[0].length; j++) {
				System.out.print(charMap[i][j] + " ");
			}
			System.out.println();
		}
	}
	private void setSymbol(LabyrinthObject obj, char character) {
		charMap[obj.getY()][obj.getX()] = character;
	}
	public void render(LabyrinthMap labMap) {
		clearMap();
		labMap.accept(this);
		printMap();
	}
	@Override
	public void visit(Player player) {
		setSymbol(player, 'J');
	}
	@Override
	public void visit(Wall wall) {
		setSymbol(wall, 'X');
	}
	@Override
	public void visit(Fruits fruit) {
		if(fruit.isConquered()) {
			setSymbol(fruit, ' ');
		}
		else
			setSymbol(fruit, 'F');

	}
	@Override
	public void visit(Pellet pellet) {
		if(pellet.isConquered()) {
			setSymbol(pellet, ' ');
		}
		else
			setSymbol(pellet, 'P');
	}
	@Override
	public void visit(EvadeGhost evadeGhost) {
		if(evadeGhost.isDead()) {
			
			setSymbol(evadeGhost, ' ');
		}
		else
			setSymbol(evadeGhost, 'E');		
	}
	@Override
	public void visit(ChaseGhost chaseGhost) {
		if(chaseGhost.isDead()) {
			
			setSymbol(chaseGhost, ' ');
		}
		else
			setSymbol(chaseGhost, 'C');		
	}
	@Override
	public void visit(PrestigiousGhost prestigiousGhost) {
		if(prestigiousGhost.isDead()) {
			
			setSymbol(prestigiousGhost, ' ');
		}
		else
			setSymbol(prestigiousGhost, 'G');
	}
	@Override
	public void visit(RandomGhost randomGhost) {
		if(randomGhost.isDead()) {
			
			setSymbol(randomGhost, ' ');
		}
		else
			setSymbol(randomGhost, 'R');		
	}
}
