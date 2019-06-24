package com.pacman.basic;

import java.util.Random;

import com.pacman.engine.LabyrinthObjectVisitor;

public class PrestigiousGhost extends Ghost{
	private boolean dead;

	PrestigiousGhost(int x, int y) {
		super(x, y);
		this.dead = false;
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

		boolean foundWall = false;
		boolean foundPlayer = false;
		boolean foundGhost = false;

		Player player = map.getPlayer();
		Ghost ghosts[] = map.getGhosts();
		Wall walls[] = map.getWall();
		//verifica se nao é parede
		for(int i = 0; i < map.getWall().length && !foundWall; i++) {
			if(walls[i].isSameCoordinates(x, y))
				foundWall = true;
		}
		//verifica se encontrou com jogador
		if(player.isSameCoordinates(super.getX(), super.getY() + 1))
			foundPlayer = true;
		
		//verifica se não é fantasma
		for(int i = 0; i < ghosts.length && !foundGhost; i++) {
			if(ghosts[i].isSameCoordinates(super.getX(), super.getY() + 1))
				foundGhost = true;
		}
		if(foundPlayer) {
			//jogador foi pego por um fantasma random
			if(player.isSuper()) {
				//fantasma morre
				this.killGhost();
			}
			else {
				// jogador perde vida
				player.kill();
			}
		} else if(!foundWall && !foundGhost) { //nao é parede e nem fantasma portanto pode teletransportar
			super.getCoordinate().changeCoordinates(x, y);
		} else {
			this.move(map);
		}
	}
	
	@Override
	public void killGhost() {
		this.dead = true;
	}
}
