package com.javarush.games.spacetrucker;


public class FinishLine extends GameObject {
	public FinishLine() {
		super(RacerGame.ROADSIDE_WIDTH,-1 * ShapeMatrix.FINISH_LINE.length,
				ShapeMatrix.FINISH_LINE);
	}

	public boolean isCrossed(PlayerShip player) {
		return player.y < y;
	}

	private boolean isVisible = false;

	public void show() {
		isVisible = true;
	}

	public void move(double boost) {
		if(isVisible) y += boost;
	}
}
