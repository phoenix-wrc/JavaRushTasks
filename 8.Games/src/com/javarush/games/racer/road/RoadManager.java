package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.RacerGame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoadManager {
	public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH,
			RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
	private static final int FIRST_LANE_POSITION = 16 , FOURTH_LANE_POSITION = 44;

	private List<RoadObject> items = new ArrayList<>();

	private void deletePassedItems()    {
		Iterator<RoadObject> iterator = items.iterator();
		while(iterator.hasNext())   {
			RoadObject item = iterator.next();
			if(item.y >= RacerGame.HEIGHT)
				iterator.remove();
		}
		//items.removeIf(item -> item.y >= RacerGame.HEIGHT);
	}

	public void generateNewRoadObjects(Game game)    {
		generateThorn(game);
	}

	private void generateThorn(Game game)   {
		if(game.getRandomNumber(100) < 10 && !isThornExists())
			addRoadObject(RoadObjectType.THORN, game);
	}

	private boolean isThornExists() {
		boolean answer = false;
		for (RoadObject item : items) {
			if (item.type == RoadObjectType.THORN)  {
				answer = true;
				break;
			}
		}
		return answer;
	}

	public void draw (Game game) {
		for (RoadObject item : items) {
			item.draw(game);
		}
	}

	public void move(int boost)  {
		for (RoadObject item : items) {
			item.move(boost + item.speed);
		}
		deletePassedItems();
	}

	private void addRoadObject(RoadObjectType type, Game game) {
		int x = game.getRandomNumber(FIRST_LANE_POSITION,FOURTH_LANE_POSITION);
		int y = -1*RoadObject.getHeight((type));
		RoadObject ro = createRoadObject(type, x, y);
		if(ro != null)
			items.add(ro);
	}

	private RoadObject createRoadObject(RoadObjectType type, int x, int y)  {
		if (type == RoadObjectType.THORN)
			return new Thorn(x,y);
		else
			return null;
	}
}
