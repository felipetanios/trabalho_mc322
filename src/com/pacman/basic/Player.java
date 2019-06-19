package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public class Player extends LabyrinthObject {
	private Direction currentDirection;
	
	Player(int x, int y) {
		super(x, y);
	}
	
	public Direction getCurrentDirection() {
		return this.currentDirection;
	}
	void move(Direction direction, Wall walls[]) {
		boolean found = false;
		for(int i = 0; i < walls.length && !found; i++) {
			switch (direction) {
			case UP:
				if(walls[i].isSameCoordinates(super.getX(), super.getY() + 1))
					found = true;
				break;
			case DOWN:
				if(walls[i].isSameCoordinates(super.getX(), super.getY()-1))
					found = true;
				break;
			case RIGH:
				if(walls[i].isSameCoordinates(super.getX() + 1, super.getY()))
					found = true;
				break;
			case LEFT:
				if(walls[i].isSameCoordinates(super.getX() - 1, super.getY()))
					found = true;
				break;
			}
		}
		if(!found) {
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
		// TODO Auto-generated method stub
		visitor.visit(this);
	}
}
