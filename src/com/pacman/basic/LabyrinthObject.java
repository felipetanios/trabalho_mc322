package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public abstract class LabyrinthObject {
	private Coordinate coordinate;
	private boolean canPass;
	
	LabyrinthObject(int x, int y) {
		coordinate = new Coordinate(0,0);
		coordinate.changeCoordinates(x, y);
	}
	public abstract void accept(LabyrinthObjectVisitor visitor);
	public boolean canPassBy() {
		return canPass;
	}
	public int getX() {
		return coordinate.getX();
	}
	public int getY() {
		return coordinate.getY();
	}
	protected Coordinate getCoordinate(){
		return coordinate;
	}
	public boolean isSameCoordinates(int x, int y) {
		if (coordinate.isSameCoordinates(x, y))
			return true;
		else 
			return false;
	}
	public boolean isSameCoordinates(LabyrinthObject labyrinthObject) {
		if (labyrinthObject.getCoordinate() == coordinate)
			return true;
		else
			return false;
	}
	protected double getDistance (LabyrinthObject q, LabyrinthObject p) {
		//System.out.println(ghost.getX()+"  " +ghost.getY() + '\n');
		double distance = Math.sqrt(Math.pow((q.getX()-p.getX()), 2) + Math.pow((q.getY()-p.getY()), 2));
		return distance;
	}
	protected double getDistance (Coordinate q, LabyrinthObject p) {
		//System.out.println(ghost.getX()+"  " +ghost.getY() + '\n');
		double distance = Math.sqrt(Math.pow((q.getX()-p.getX()), 2) + Math.pow((q.getY()-p.getY()), 2));
		return distance;
	}
}
