package com.javarush.games.racer;

public class PlayerCar extends GameObject {
	private static int playerCarHeight = ShapeMatrix.PLAYER.length;
	public int speed = 1;

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	private Direction direction;

	public PlayerCar() {
		super(RacerGame.WIDTH/2+2,RacerGame.HEIGHT - playerCarHeight - 1,
				ShapeMatrix.PLAYER);
	}

	public void move()  {
		if(direction == Direction.LEFT) x--;
		if (direction == Direction.RIGHT) x++;
	}
}
