package com.pacman.basic;

import java.util.Random;

import com.pacman.engine.LabyrinthObjectVisitor;

public class Player extends LabyrinthObject {
	private Direction currentDirection;
	private boolean superPower;
	private int points;
	private int lifes;
	Player(int x, int y) {
		super(x, y);
		this.points = 0;
		this.lifes = 3;
		this.superPower = false;
	}
	
	public Direction getCurrentDirection() {
		return this.currentDirection;
	}
	public int getPoints() {
		return points;
	}
	void updateLifes() {
		this.lifes++;
		points -= 10000;
	}
	int getLifes() {
		return lifes;
	}
	boolean isSuper() {
		return superPower;
	}
	void activateSuper() {
		this.superPower = true;
	}
	void deactivateSuper() {
		this.superPower = false;
	}
	void kill() {
		this.lifes--;
	}
	void updatePoints(LabyrinthObject obj) {
		if(obj instanceof Ghost) {
			points += ((Ghost) obj).getPoints();
		} else if(obj instanceof Pellet) {
			points += ((Pellet) obj).getPoints();
		} else if(obj instanceof Fruits) {
			points += ((Fruits) obj).getPoints();
		}
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
		
		switch (direction) {
		case UP:
			if(super.getX() >= map.getWidht() || (super.getY() - 1) < 0)
				canMove = false;
			break;
		case DOWN:
			if(super.getX() >= map.getWidht() || (super.getY() + 1) >= map.getHeight())
				canMove = false;
			break;
		case RIGH:
			if((super.getX() + 1) >= map.getWidht() || super.getY() >= map.getHeight())
				canMove = false;
			break;
		case LEFT:
			if((super.getX() - 1) < 0 || super.getY() >= map.getHeight())
				canMove = false;
			break;
		}

		
		//verifica se nao � parede
		for(int i = 0; i < map.getWall().length && !foundWall; i++) {
			switch (direction) {
			case UP:
				if(walls[i].isSameCoordinates(super.getX(), super.getY() - 1))
					foundWall = true;
				break;
			case DOWN:
				if(walls[i].isSameCoordinates(super.getX(), super.getY() + 1))
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
		//verifica se n�o � fantasma
		for(int i = 0; i < ghosts.length && foundGhost == null; i++) {
			switch (direction) {
			case UP:
				if(ghosts[i].isSameCoordinates(super.getX(), super.getY() - 1))
					foundGhost = ghosts[i];
				break;
			case DOWN:
				if(ghosts[i].isSameCoordinates(super.getX(), super.getY() + 1))
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
		//verifica se � fruta
		for(int i = 0; i < fruits.length && foundFruit == null; i++) {
			switch (direction) {
			case UP:
				if(fruits[i].isSameCoordinates(super.getX(), super.getY() - 1))
					foundFruit = fruits[i];
				break;
			case DOWN:
				if(fruits[i].isSameCoordinates(super.getX(), super.getY() + 1))
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
		//verifica se � ponto
		for(int i = 0; i < pellets.length && foundPellet == null; i++) {
			switch (direction) {
			case UP:
				if(pellets[i].isSameCoordinates(super.getX(), super.getY() - 1))
					foundPellet = pellets[i];
				break;
			case DOWN:
				if(pellets[i].isSameCoordinates(super.getX(), super.getY() + 1))
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
			if(!foundGhost.isDead()) {
				if(this.superPower) {
					//come fantasma
					foundGhost.killGhost();
					this.updatePoints(foundGhost);
					canMove = true;
				}
				else {
					canMove = false;
					//perde vida
					this.kill();
				}
			}
		} else if(foundFruit != null) {
			if(!foundFruit.isConquered()) {
				this.updatePoints(foundFruit);
				this.activateSuper();
				foundFruit.conquer();
				System.out.println("FRUIT CONQUERED");
			}
		} else if(foundPellet != null) {
			if(!foundPellet.isConquered()) {
				this.updatePoints(foundPellet);
				foundPellet.conquer();
			}
		}
		if (canMove) {
			switch (direction) {
			case UP:
				super.getCoordinate().changeCoordinates(super.getX(), super.getY() - 1);
				break;
			case DOWN:
				super.getCoordinate().changeCoordinates(super.getX(), super.getY() + 1);
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
