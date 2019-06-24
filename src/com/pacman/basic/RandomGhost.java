package com.pacman.basic;

import java.util.Random;

import com.pacman.engine.LabyrinthObjectVisitor;

public class RandomGhost extends Ghost{
	private boolean dead;

	RandomGhost(int x, int y) {
		super(x, y);
		this.dead = false;
	}

	@Override
	public void accept(LabyrinthObjectVisitor visitor) {
		visitor.visit(this);		
	}

	@Override
	public void move(LabyrinthMap map) {
		if(!this.isDead()) {
			//direção aleatória
			Random r = new Random();
			int x;
			x = r.nextInt(4);
			
			boolean foundWall = false;
			boolean foundPlayer = false;
			boolean foundGhost = false;
	
			Wall walls[] = map.getWall();
			Player player = map.getPlayer();
			Ghost ghosts[] = map.getGhosts();
			
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
			switch (x) {
			case 0:
				if(player.isSameCoordinates(super.getX(), super.getY() + 1))
					foundPlayer = true;
				break;
			case 1:
				if(player.isSameCoordinates(super.getX(), super.getY()-1))
					foundPlayer = true;
				break;
			case 2:
				if(player.isSameCoordinates(super.getX() + 1, super.getY()))
					foundPlayer = true;
				break;
			case 3:
				if(player.isSameCoordinates(super.getX() - 1, super.getY()))
					foundPlayer = true;
				break;
			}
			//verifica se não é fantasma
			for(int i = 0; i < ghosts.length && !foundGhost; i++) {
				switch (x) {
				case 0:
					if(ghosts[i].isSameCoordinates(super.getX(), super.getY() + 1))
						foundGhost = true;
					break;
				case 1:
					if(ghosts[i].isSameCoordinates(super.getX(), super.getY()-1))
						foundGhost = true;
					break;
				case 2:
					if(ghosts[i].isSameCoordinates(super.getX() + 1, super.getY()))
						foundGhost = true;
					break;
				case 3:
					if(ghosts[i].isSameCoordinates(super.getX() - 1, super.getY()))
						foundGhost = true;
					break;
				}
			}
			if(foundPlayer) {
				//jogador foi pego por um fantasma random
				if(player.isSuper()) {
					//fantasma morre
					this.killGhost();
					player.updatePoints(this);
				}
				else {
					// jogador perde vida
					player.kill();
				}
			} else if(!foundWall && !foundGhost) {
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
			} else {
				this.move(map);
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
