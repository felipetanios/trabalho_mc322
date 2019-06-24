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
	protected Player getPlayer() {
		return player;
	}
	protected Wall[] getWall() {
		return this.wall;
	}
	protected Ghost[] getGhosts() {
		return ghosts;
	}
	protected Fruits[] getFruits() {
		return fruits;
	}
	protected Pellet[] getPellet() {
		return pellet;
	}
	public int getWidht() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public void updateMap(Direction direction) {
		player.move(direction, this);
		for(Ghost g:ghosts) {
			g.move(this);
		}
		updatePlayer();
		System.out.println("Pontuação: " + player.getPoints());
		System.out.println("Vidas: "+ player.getLifes());
		//fazer contagem de tempo do poder da fruta especial pra comer os outros fantasmas
	}
	public boolean isGameOver() {
		if(player.getLifes() <= 0) {
			return true;
		}
		return false;
	}
	public boolean isFinished() {
		boolean foundPellet = false;
		for(Pellet p:pellet) {
			if(!p.isConquered())
				foundPellet = true;
		}
		if(foundPellet)
			return false;
		else
			return true;
	}
	private void updatePlayer() {
		if(player.getPoints() >= 10000)
			player.updateLifes();
	}
	public void accept(LabyrinthObjectVisitor visitor) {
		for(Wall walls: wall) {
			walls.accept(visitor);
			//System.out.println("checkpoint is fine");
		}

		//System.out.println("wall is fine");
		for(Fruits fruit: fruits) {
			fruit.accept(visitor);
		}
		//System.out.println("fruit is fine");

		for(Ghost g: ghosts) {
			g.accept(visitor);
		}
		//System.out.println("ghost is fine");

		for(Pellet p: pellet) {
			p.accept(visitor);
		}
		//System.out.println("pellet is fine");

		player.accept(visitor);
	}
}
