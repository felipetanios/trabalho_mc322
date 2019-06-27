package com.pacman.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pacman.engine.LabyrinthObjectVisitor;

public class EvadeGhost extends Ghost{
	private boolean dead;

	EvadeGhost(int x, int y) {
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
		if (!this.isDead()) {
			Player p = map.getPlayer();
			double distance;
			Coordinate neighbour;
			Map<Double, Coordinate> distanceMap = new HashMap<Double, Coordinate>();
			
			//Cima
			neighbour = new Coordinate(super.getX(), super.getY()+1);
			distance = getGhostDistances(neighbour, map);
			distanceMap.put(distance,neighbour);
			//Baixo
			neighbour = new Coordinate(super.getX(), super.getY()-1);
			distance = getGhostDistances(neighbour, map);
			distanceMap.put(distance,neighbour);
			//Esquerda
			neighbour = new Coordinate(super.getX()-1, super.getY());
			distance = getGhostDistances(neighbour, map);
			distanceMap.put(distance,neighbour);
			//Direita
			neighbour= new Coordinate(super.getX()+1, super.getY());
			distance = getGhostDistances(neighbour, map);
			distanceMap.put(distance,neighbour);
			
			List<Double> distanceArray = new ArrayList<>(distanceMap.keySet());
			Collections.sort(distanceArray, Collections.reverseOrder());
	
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
				//verifica jogador
				if(p.isSameCoordinates(coordinateToMove.getX(), coordinateToMove.getY()))
					foundPlayer = true;
				
				//verifica se n�o � fantasma
				for(int i = 0; i < ghosts.length && !foundGhost; i++) {
					if (ghosts[i].isSameCoordinates(coordinateToMove.getX(), coordinateToMove.getY()))
						foundGhost = true;
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
	
	private double getGhostDistances(Coordinate coordinate, LabyrinthMap map) {
		double ghostdistance = 0;
		for (Ghost ghost: map.getGhosts()) {
			ghostdistance += getDistance(coordinate, ghost);
		}
		
		return ghostdistance;
	}
}
