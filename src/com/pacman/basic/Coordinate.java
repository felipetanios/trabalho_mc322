package com.pacman.basic;

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void changeCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public boolean isSameCoordinates(int x, int y) {
		if(this.x == x && this.y == y)
			return true;
		else
			return false;
	}
	public boolean equals(Object obj) {
		try {
			if(this.clone().equals(obj))
				return true;
			else
				return false;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
