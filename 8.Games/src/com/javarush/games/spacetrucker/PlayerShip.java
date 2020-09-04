package com.javarush.games.spacetrucker;

import com.javarush.games.spacetrucker.space.RoadManager;

public class PlayerShip extends GameObject {
	private static int playerCarHeight = ShapeMatrix.PLAYER.length;
	public double speed = 1.0;
	public double sideSpeed = 0.1;
	private Direction direction;

	public void stop() {
		matrix = ShapeMatrix.PLAYER_DEAD;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public PlayerShip() {
		super(RacerGame.WIDTH/2+2,RacerGame.HEIGHT - playerCarHeight - 1,
				ShapeMatrix.PLAYER);
	}

	public void accelerate () {
		if(speed < 3) speed += 0.1;
	}

	public void sideAccelerate () {
		if(sideSpeed < 2 && sideSpeed > -2)
			if(direction == Direction.LEFT) sideSpeed -= 0.05;
			if(direction == Direction.RIGHT) sideSpeed += 0.05;
			if(direction == Direction.NONE && sideSpeed > 0) sideSpeed -= 0.01;
			if(direction == Direction.NONE && sideSpeed < 0) sideSpeed += 0.01;
	}

	public void brake() {
		if(speed > 0.5) speed -= 0.2;
	}

	public void move()  {
		sideAccelerate();
		x += sideSpeed;
		if (x < RoadManager.LEFT_BORDER) x = RoadManager.LEFT_BORDER;
		if (x > RoadManager.RIGHT_BORDER - width) x = RoadManager.RIGHT_BORDER - width;
	}
}
