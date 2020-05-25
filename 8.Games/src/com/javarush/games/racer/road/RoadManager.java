package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoadManager {
	public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
	public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
	private static final int FIRST_LANE_POSITION = 16;
	private static final int FOURTH_LANE_POSITION = 44;
	private static final int PLAYER_CAR_DISTANCE = 12;
	private List<RoadObject> items = new ArrayList<>();

	private void generateMovingCar(Game game) {
		if(!isMovingCarExists() && game.getRandomNumber(100) < 10) {
			addRoadObject(RoadObjectType.DRUNK_CAR, game);
		}
	}

	private boolean isMovingCarExists() {
		for (RoadObject item : items) {
			if (item.type == RoadObjectType.DRUNK_CAR) {
				return true;
			}
		}
		return false;
	}

	private boolean isRoadSpaceFree(RoadObject object) {
		for (RoadObject item: items) {
			if(item.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)) {
				return false;
			}
		}
		return true;
	}

	private void generateRegularCar(Game game) {
		int carTypeNumber = game.getRandomNumber(4);
		if(game.getRandomNumber(100) < 30) {
			addRoadObject(RoadObjectType.values()[carTypeNumber], game);
		}
	}

	public boolean checkCrush(PlayerCar car) {
		for (RoadObject item: items) {
			if(item.isCollision(car)) return true;
		}
		return false;
	}

	private void deletePassedItems() {
		Iterator<RoadObject> iterator = items.iterator();
		while (iterator.hasNext()) {
			RoadObject item = iterator.next();
			if (item.y >= RacerGame.HEIGHT) iterator.remove();
		}
	}

	private boolean isThornExists() {
		for (RoadObject item : items) {
			if (item.type == RoadObjectType.THORN) {
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
		generateRegularCar(game);
		generateMovingCar(game);
	}

	public void move(int boost) {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).move(items.get(i).speed + boost, items);
		}

		deletePassedItems();
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
		if(ro != null && isRoadSpaceFree(ro)) items.add(ro);
	}

	private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
		if(type == RoadObjectType.THORN) return new Thorn(x,y);
		else if(type == RoadObjectType.DRUNK_CAR) return new MovingCar(x,y);
		else return new Car(type, x, y);
	}
}
