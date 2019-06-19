package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public class LabyrinthMap {
	private Player player;
	private Checkpoint checkpoint[];
	private Wall wall[];
	private int width;
	private int height;
	
	protected LabyrinthMap(int width, int height, Player player, Checkpoint checkpoint[], Wall walls[] ) {
		this.player = player;
		this.width = width;
		this.height = height;
		this.checkpoint = checkpoint;
		this.wall = walls;
	}
	
	public int getWidht() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public void updateMap(Direction direction) {
		
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
		for(Checkpoint check: checkpoint) {

			check.accept(visitor);
		}
		player.accept(visitor);
	}
}
