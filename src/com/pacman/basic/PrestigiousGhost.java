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
		if(!this.isDead()) {
			//dire��o aleat�ria
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
			//Esse fantasma nunca está fora do limite do mapa devido ao calculo de x e y random ser de 0 -> limites;
			//verifica se nao � parede
			for(int i = 0; i < map.getWall().length && !foundWall; i++) {
				if(walls[i].isSameCoordinates(x, y))
					foundWall = true;
			}
			//verifica se encontrou com jogador
			if(player.isSameCoordinates(super.getX() + x, super.getY() + y))
				foundPlayer = true;
			
			//verifica se n�o � fantasma
			for(int i = 0; i < ghosts.length && !foundGhost; i++) {
				if(ghosts[i].isSameCoordinates(super.getX() + x, super.getY() + y))
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
			} else if(!foundWall && !foundGhost) { //nao � parede e nem fantasma portanto pode teletransportar
				super.getCoordinate().changeCoordinates(x, y);
			} else {
				this.move(map);
			}
		}
	}
	
	@Override
	public void killGhost() {
		this.dead = true;
	}
}
