package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public class LabyrinthMap {
	private Player player;
	private Wall wall[];
	private Ghost ghosts[];
	private Fruits fruits[];
	private Pellet pellet[];
	private int width;
	private int height;
	
	protected LabyrinthMap(int width, int height, Player player, Wall walls[], Ghost ghosts[], Fruits fruits[], Pellet pellet[] ) {
		this.player = player;
		this.width = width;
		this.height = height;
		this.wall = walls;
		this.ghosts = ghosts;
		this.fruits = fruits;
		this.pellet = pellet;		
	}
	
	public int getWidht() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public void updateMap(Direction direction) {
		player.move(direction, wall);
	}
	public boolean isDone() {
		return false;
	}
	public void accept(LabyrinthObjectVisitor visitor) {
		for(Wall walls: wall) {
			walls.accept(visitor);
			System.out.println("checkpoint is fine");
		}

		System.out.println("wall is fine");
		for(Fruits fruit: fruits) {
			fruit.accept(visitor);
		}
		System.out.println("fruit is fine");

		for(Ghost g: ghosts) {
			g.accept(visitor);
		}
		System.out.println("ghost is fine");

		for(Pellet p: pellet) {
			p.accept(visitor);
		}
		System.out.println("pellet is fine");

		player.accept(visitor);
	}
}
