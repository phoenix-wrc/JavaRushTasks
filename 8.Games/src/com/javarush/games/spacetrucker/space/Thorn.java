package com.javarush.games.spacetrucker.space;

public class Thorn extends RoadObject {
	public Thorn(int x, int y) {
		super(RoadObjectType.THORN, x, y);
		super.speed = 0;
	}
}
