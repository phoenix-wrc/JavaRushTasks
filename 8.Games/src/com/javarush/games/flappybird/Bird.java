package com.javarush.games.flappybird;

public class Bird extends GameObject {
	private double ySpeed = 0;
	private int flaps = 0;
	private double boost = 0.25;
	public boolean isDead = false;

	public Bird(double x, double y) {
		super(x, y, ShapeMatrix.BIRD_1);
	}

	public void dead() {
		matrix = ShapeMatrix.BIRD_DEAD;
	}

	public void move() {
		if(flaps > 0) {
			ySpeed = 0;
			y -= 2;
			matrix = ShapeMatrix.BIRD_FLAPP;
			flaps --;
			checkBorders();
		} else {
			ySpeed += boost;
			y += ySpeed;
			matrix = ShapeMatrix.BIRD_1;
		}
	}

	public void flap () {
		flaps = 4;
		y -= 2;
	}

	private void checkBorders() {
		if (y <= 0) {
			y = 0;
			isDead = true;
		}
	}
}
