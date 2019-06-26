package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChaseGhost extends Ghost{
	private boolean dead = false;
	private boolean overPlayer = false; 

	ChaseGhost(int x, int y) {
		super(x, y);
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
		if (!this.isDead()) {
			Player p = map.getPlayer();
			double distance;
			Coordinate neighbour;
			Map<Double, Coordinate> distanceMap = new HashMap<Double, Coordinate>();
			
			//se esta em cima do jogador nao se mexe, espera o jogador andar
			if (overPlayer){
				super.getCoordinate().changeCoordinates(super.getX(), super.getY());
				overPlayer = false;
				return;
			}
			
			//Cima
			neighbour = new Coordinate(super.getX(), super.getY()+1);
			distance = getDistance(neighbour, p);
			distanceMap.put(distance,neighbour);
			//Baixo
			neighbour = new Coordinate(super.getX(), super.getY()-1);
			distance = getDistance(neighbour, p);
			distanceMap.put(distance,neighbour);
			//Esquerda
			neighbour = new Coordinate(super.getX()-1, super.getY());
			distance = getDistance(neighbour, p);
			distanceMap.put(distance,neighbour);
			//Direita
			neighbour= new Coordinate(super.getX()+1, super.getY());
			distance = getDistance(neighbour, p);
			distanceMap.put(distance,neighbour);
			
			List<Double> distanceArray = new ArrayList<>(distanceMap.keySet());
			Collections.sort(distanceArray);
			
			
			
			System.out.println(distanceArray);
			
			
	
			Wall walls[] = map.getWall();
			Player player = map.getPlayer();
			Ghost ghosts[] = map.getGhosts();
			
			for (Double distances:distanceArray) {
				boolean foundWall = false;
				boolean foundPlayer = false;
				boolean foundGhost = false;
				Coordinate coordinateToMove = distanceMap.get(distances);
				//verifica se nao é parede
						
				for(int i = 0; i < map.getWall().length && !foundWall; i++) {
					if (walls[i].isSameCoordinates(coordinateToMove.getX(), coordinateToMove.getY()))
						foundWall = true;
				}
				//fazer verifica jogador e outro fantasma
				if(p.isSameCoordinates(coordinateToMove.getX(), coordinateToMove.getY()))
					foundPlayer = true;
				
				//verifica se n�o � fantasma
				for(int i = 0; i < ghosts.length && !foundGhost; i++) {
					if (ghosts[i].isSameCoordinates(coordinateToMove.getX(), coordinateToMove.getY()))
						foundGhost = true;
				}
				if(foundPlayer) {
					overPlayer = true;
					//jogador foi pego por um fantasma random
					if(player.isSuper()) {
						System.out.println("Super player");
						//fantasma morre
						this.killGhost();
						player.updatePoints(this);
					}
					else {
						System.out.println("Player Morre");
						// jogador perde vida
						player.kill();
					}
				} else if(!foundWall && !foundGhost) {
					super.getCoordinate().changeCoordinates(coordinateToMove.getX(), coordinateToMove.getY());
					return;
				}
			}
		}
	}

	@Override
	public void killGhost() {
		this.dead = true;
	}
	
	

}
