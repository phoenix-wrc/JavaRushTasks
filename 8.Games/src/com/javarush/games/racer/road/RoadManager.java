package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.GameObject;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.List;

public class RoadManager {
	public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
	public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
	private static final int FIRST_LANE_POSITION = 16;
	private static final int FOURTH_LANE_POSITION = 44;
	private List<RoadObject> items = new ArrayList<>();

	private boolean isThornExists() {
		for (int i = 0; i < items.size(); i++) {
			if(items.get(i).type == RoadObjectType.THORN) {
				return true;
			}
		}
		return false;
	}

	private void generateThorn(Game game) {
		if(!isThornExists() && game.getRandomNumber(100) < 10) {
			addRoadObject(RoadObjectType.THORN, game);
		}
	}

	public void generateNewRoadObjects(Game game) {
		generateThorn(game);
	}

	public void move(int boost) {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).move(items.get(i).speed + boost);
		}
	}

	public void draw(Game game) {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).draw(game);
		}
	}

	private void addRoadObject(RoadObjectType type, Game game) {
		int x = game.getRandomNumber(FIRST_LANE_POSITION,FOURTH_LANE_POSITION);
		int y = -1 * RoadObject.getHeight(type);
		RoadObject ro = createRoadObject(type, x, y);
		if(ro != null) items.add(ro);
	}

	private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
		if(type != RoadObjectType.THORN) return null;
		if(type == RoadObjectType.THORN) return new Thorn(x,y);
		return null;
	}
}
