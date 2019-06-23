package com.pacman.basic;

import java.util.Random;

import com.pacman.engine.LabyrinthObjectVisitor;

public class PrestigiousGhost extends Ghost{
	private boolean dead = false;

	PrestigiousGhost(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean isDead() {
		return dead;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void move(LabyrinthMap map) {
		//direção aleatória
		Random r = new Random();
		int x, y;
		x = r.nextInt(map.getWidht());
		y = r.nextInt(map.getHeight());
		boolean found = false;
		Wall walls[] = map.getWall();
		//verifica se nao é parede
		for(int i = 0; i < map.getWall().length && !found; i++) {
			if(walls[i].isSameCoordinates(x, y))
				found = true;
		}
		//fazer verifica jogador e outro fantasma
		
		if(!found) {
			super.getCoordinate().changeCoordinates(x, y);
		}
	}
	
	@Override
	public void killGhost() {
		this.dead = true;
	}
}
