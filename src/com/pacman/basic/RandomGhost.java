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
			//dire��o aleat�ria
			int x = 0;		
			boolean foundWall = false;
			boolean foundPlayer = false;
			boolean foundGhost = false;
			boolean outOfBounds = false;
	
			Wall walls[] = map.getWall();
			Player player = map.getPlayer();
			Ghost ghosts[] = map.getGhosts();
			do {
				Random r = new Random();
				x = r.nextInt(4);
				switch (x) {
				case 0:
					if((super.getY() - 1) < 0)
						outOfBounds = true;
					break;
				case 1:
					if((super.getY() + 1) >= map.getHeight())
						outOfBounds = true;
					break;
				case 2:
					if((super.getX() + 1) >= map.getWidht())
						outOfBounds = true;
					break;
				case 3:
					if((super.getX() - 1) < 0)
						outOfBounds = true;
					break;
				}
			} while (outOfBounds);
			
			//verifica se nao é parede ou fora do limite do mapa
			for(int i = 0; i < map.getWall().length && !foundWall; i++) {
				switch (x) {
				case 0:
					if(walls[i].isSameCoordinates(super.getX(), super.getY() - 1))
						foundWall = true;
					break;
				case 1:
					if(walls[i].isSameCoordinates(super.getX(), super.getY() + 1))
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
				if(player.isSameCoordinates(super.getX(), super.getY() - 1))
					foundPlayer = true;
				break;
			case 1:
				if(player.isSameCoordinates(super.getX(), super.getY() + 1))
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
			//verifica se n�o � fantasma
			for(int i = 0; i < ghosts.length && !foundGhost; i++) {
				switch (x) {
				case 0:
					if(ghosts[i].isSameCoordinates(super.getX(), super.getY() - 1))
						foundGhost = true;
					break;
				case 1:
					if(ghosts[i].isSameCoordinates(super.getX(), super.getY() + 1))
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
					super.getCoordinate().changeCoordinates(super.getX(), super.getY() - 1);
					break;
				case 1:
					super.getCoordinate().changeCoordinates(super.getX(), super.getY() + 1);
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
