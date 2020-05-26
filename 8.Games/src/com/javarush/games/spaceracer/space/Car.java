package com.javarush.games.spaceracer.space;

public class Car extends RoadObject {
	public Car(RoadObjectType type, int x, int y) {
		super(type, x, y);
		super.speed = 1;
	}
}
