package com.pacman.basic;

import java.util.Random;

import com.pacman.engine.LabyrinthObjectVisitor;

public class RandomGhost extends Ghost{
	private boolean dead = false;

	RandomGhost(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);		
	}

	@Override
	public void move(LabyrinthMap map) {
		//direção aleatória
		Random r = new Random();
		int x;
		x = r.nextInt(4);
		boolean foundWall = false;
		Wall walls[] = map.getWall();
		//verifica se nao é parede
		for(int i = 0; i < map.getWall().length && !foundWall; i++) {
			switch (x) {
			case 0:
				if(walls[i].isSameCoordinates(super.getX(), super.getY() + 1))
					foundWall = true;
				break;
			case 1:
				if(walls[i].isSameCoordinates(super.getX(), super.getY()-1))
					foundWall = true;
				break;
			case 2:
				if(walls[i].isSameCoordinates(super.getX() + 1, super.getY()))
					foundWall = true;
				break;
			case 3:
				if(walls[i].isSameCoordinates(super.getX() - 1, super.getY()))
					foundWall = true;
				break;
			}
		}
		//fazer verifica jogador e outro fantasma
		
		if(!foundWall) {
			switch (x) {
			case 0:
				super.getCoordinate().changeCoordinates(super.getX(), super.getY() + 1);
				break;
			case 1:
				super.getCoordinate().changeCoordinates(super.getX(), super.getY() - 1);
				break;
			case 2:
				super.getCoordinate().changeCoordinates(super.getX() + 1, super.getY());
				break;
			case 3:
				super.getCoordinate().changeCoordinates(super.getX() - 1, super.getY());
				break;
			}
		}
	}
	
	@Override
	public void killGhost() {
		this.dead = true;
	}
	
	public boolean isDead() {
		return dead;
	}

	@Override
	public int getPoints() {
		return points;
	}

}
