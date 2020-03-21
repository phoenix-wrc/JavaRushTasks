package com.javarush.games.flappybird;

import com.javarush.games.moonlander.MoonLanderGame;

public class Bird extends GameObject {
	private double ySpeed = 0;
	private int flaps = 0;


	public Bird(double x, double y) {
		super(x, y, ShapeMatrix.BIRD_1);
	}

	public void dead() {
		matrix = ShapeMatrix.BIRD_DEAD;
	}

	public void move() {
		if(flaps > 0) {
			ySpeed = 0;
			y -= 3;
			matrix = ShapeMatrix.BIRD_FLAPP;
			flaps --;
			checkBorders();
		} else {
			double boost = 0.55;
			ySpeed += boost;
			y += ySpeed;
			matrix = ShapeMatrix.BIRD_1;
		}
	}

	public void flap () {
		flaps = 3;
		// ySpeed = 0;
		//y -= 15;
		//matrix = ShapeMatrix.BIRD_FLAPP;
	}

	private void checkBorders() {
		if (y <= 0) {
			y = 0;
		}
	}
}
