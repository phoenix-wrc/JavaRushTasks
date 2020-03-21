package com.javarush.games.flappybird;

public class Colon extends GameObject {

	public Colon(double x, double y) {
		super(x, y, ShapeMatrix.COLON);
	}

	public void move() {
		x -= speed;
	}
}
