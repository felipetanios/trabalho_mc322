package com.pacman.engine;

import com.pacman.basic.Checkpoint;
import com.pacman.basic.LabyrinthMap;
import com.pacman.basic.LabyrinthObject;
import com.pacman.basic.Player;
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
	public void visit(Checkpoint checkpoint) {
		if(checkpoint.isConquered()) {
			
			setSymbol(checkpoint, 'T');
		}
		else
			setSymbol(checkpoint, 'C');

	}
}
