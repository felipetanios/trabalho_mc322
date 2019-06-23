package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public class Player extends LabyrinthObject {
	private Direction currentDirection;
	private boolean superPower = false;
	private int points = 0;
	Player(int x, int y) {
		super(x, y);
	}
	
	public Direction getCurrentDirection() {
		return this.currentDirection;
	}
	public int getPoints() {
		return points;
	}
	public boolean isSuper() {
		return superPower;
	}
	public void activateSuper() {
		this.superPower = true;
	}
	public void deactivateSuper() {
		this.superPower = false;
	}
	void move(Direction direction, LabyrinthMap map) {
		boolean canMove = true;
		boolean foundWall = false;
		Ghost foundGhost = null;
		Fruits foundFruit = null;
		Pellet foundPellet = null;

		Wall walls[] = map.getWall();
		Ghost ghosts[] = map.getGhosts();
		Pellet pellets[] = map.getPellet();
		Fruits fruits[] = map.getFruits();
		//verifica se nao é parede
		for(int i = 0; i < map.getWall().length && !foundWall; i++) {
			switch (direction) {
			case UP:
				if(walls[i].isSameCoordinates(super.getX(), super.getY() + 1))
					foundWall = true;
				break;
			case DOWN:
				if(walls[i].isSameCoordinates(super.getX(), super.getY()-1))
					foundWall = true;
				break;
			case RIGH:
				if(walls[i].isSameCoordinates(super.getX() + 1, super.getY()))
					foundWall = true;
				break;
			case LEFT:
				if(walls[i].isSameCoordinates(super.getX() - 1, super.getY()))
					foundWall = true;
				break;
			}
		}
		//verifica se não é fantasma
		for(int i = 0; i < ghosts.length && foundGhost == null; i++) {
			switch (direction) {
			case UP:
				if(ghosts[i].isSameCoordinates(super.getX(), super.getY() + 1))
					foundGhost = ghosts[i];
				break;
			case DOWN:
				if(ghosts[i].isSameCoordinates(super.getX(), super.getY()-1))
					foundGhost = ghosts[i];
				break;
			case RIGH:
				if(ghosts[i].isSameCoordinates(super.getX() + 1, super.getY()))
					foundGhost = ghosts[i];
				break;
			case LEFT:
				if(ghosts[i].isSameCoordinates(super.getX() - 1, super.getY()))
					foundGhost = ghosts[i];
				break;
			}
		}
		//verifica se é fruta
		for(int i = 0; i < fruits.length && foundFruit == null; i++) {
			switch (direction) {
			case UP:
				if(fruits[i].isSameCoordinates(super.getX(), super.getY() + 1))
					foundFruit = fruits[i];
				break;
			case DOWN:
				if(fruits[i].isSameCoordinates(super.getX(), super.getY()-1))
					foundFruit = fruits[i];
				break;
			case RIGH:
				if(fruits[i].isSameCoordinates(super.getX() + 1, super.getY()))
					foundFruit = fruits[i];
				break;
			case LEFT:
				if(fruits[i].isSameCoordinates(super.getX() - 1, super.getY()))
					foundFruit = fruits[i];
				break;
			}
		}
		//verifica se é ponto
		for(int i = 0; i < pellets.length && foundPellet == null; i++) {
			switch (direction) {
			case UP:
				if(pellets[i].isSameCoordinates(super.getX(), super.getY() + 1))
					foundPellet = pellets[i];
				break;
			case DOWN:
				if(pellets[i].isSameCoordinates(super.getX(), super.getY()-1))
					foundPellet = pellets[i];
				break;
			case RIGH:
				if(pellets[i].isSameCoordinates(super.getX() + 1, super.getY()))
					foundPellet = pellets[i];
				break;
			case LEFT:
				if(pellets[i].isSameCoordinates(super.getX() - 1, super.getY()))
					foundPellet = pellets[i];
				break;
			}
		}
		if(foundWall) {
			canMove = false;
		} else if(foundGhost != null) {
			if(this.superPower) {
				//come fantasma
				foundGhost.killGhost();
				points += foundGhost.getPoints();
				canMove = true;
			}
			else {
				canMove = false;
				//game over
			}
		} else if(foundFruit != null) {
			if(!foundFruit.isConquered()) {
				points += foundFruit.getPoints();
				this.activateSuper();
				foundFruit.conquer();
				System.out.println("FRUIT CONQUERED");
			}
		} else if(foundPellet != null) {
			if(!foundPellet.isConquered()) {
				points += foundPellet.getPoints();
				foundPellet.conquer();
			}
		}
		if (canMove) {
			switch (direction) {
			case UP:
				super.getCoordinate().changeCoordinates(super.getX(), super.getY() + 1);
				break;
			case DOWN:
				super.getCoordinate().changeCoordinates(super.getX(), super.getY() - 1);
				break;
			case RIGH:
				super.getCoordinate().changeCoordinates(super.getX() + 1, super.getY());
				break;
			case LEFT:
				super.getCoordinate().changeCoordinates(super.getX() - 1, super.getY());
				break;
			}
		}
	}

	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);
	}
}
