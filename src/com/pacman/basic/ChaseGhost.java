package com.pacman.basic;

import com.pacman.engine.LabyrinthObjectVisitor;

public class ChaseGhost extends Ghost{
	private boolean dead = false;

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
//		Player p = map.getPlayer();
//		double distance = 0;
//		int closest[] = new int[4];
//		int points[][] = new int[4][2];
//		//Cima
//		points[0][0] = p.getX() - this.getX();
//		points[0][1] = p.getY() - (this.getY() + 1);
//		//Baixo
//		points[1][0] = p.getX() - this.getX();
//		points[1][1] = p.getY() - (this.getY() - 1);
//		//Esquerda
//		points[2][0] = p.getX() - (this.getX() - 1);
//		points[2][1] = p.getY() - this.getY();
//		//Direita
//		points[3][0] = p.getX() - (this.getX() + 1);
//		points[3][1] = p.getY() - this.getY();
//		
//		for(int i = 0; i < 4; i ++) {
//			double distance2 = Math.sqrt(points[i][0]*points[i][0] + points[i][1]*points[i][1]);
//			closest[i] = i;
//			if (distance2 < distance) {
//				closest[] = i;
//				distance = distance2;
//			}
//		}
//		
//		
//		
	}

	@Override
	public void killGhost() {
		this.dead = true;
	}

}
