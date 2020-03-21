package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
	public static final int WIDTH = 64, HEIGHT = 64, CENTER_X = WIDTH/2, ROADSIDE_WIDTH =14;

	private RoadMarking roadMarking;
	private PlayerCar player;
	private RoadManager roadManager;

	private void moveAll()  {
		roadMarking.move(player.speed);
		player.move();
		roadManager.move(player.speed);
	}

	private void createGame()   {
		roadMarking = new RoadMarking();
		player = new PlayerCar();
		setTurnTimer(40);
		roadManager = new RoadManager();

		drawScene();
	}

	private void drawScene()    {
		drawField();
		roadMarking.draw(this);
		player.draw(this);
		roadManager.draw(this);
	}

	private void drawField()    {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if(i == CENTER_X)
					setCellColor(CENTER_X, j, Color.WHITE);
				else if(i >= ROADSIDE_WIDTH && i < WIDTH - ROADSIDE_WIDTH )
					setCellColor(i, j, Color.DIMGRAY);
				else //if (i < ROADSIDE_WIDTH || i >= WIDTH - ROADSIDE_WIDTH)
					setCellColor(i, j, Color.DARKGREEN);
			}
		}
	}

	@Override
	public void onKeyPress  (Key key)  {
		if (key == Key.LEFT)
			player.setDirection(Direction.LEFT);
		if (key == Key.RIGHT)
			player.setDirection(Direction.RIGHT);
	}

	@Override
	public void onKeyReleased (Key key) {
		if (key == Key.RIGHT && player.getDirection() == Direction.RIGHT)
			player.setDirection(Direction.NONE);
		if (key == Key.LEFT && player.getDirection() == Direction.LEFT)
			player.setDirection(Direction.NONE);

	}
	@Override
	public void initialize() {
		setScreenSize(WIDTH,HEIGHT);
		showGrid(false);

		createGame();
	}

	@Override
	public void onTurn(int t)    {
		moveAll();
		roadManager.generateNewRoadObjects(this);

		drawScene();
	}

	@Override
	public void setCellColor(int x, int y, Color color) {
		if (x < 0)
			return;
		if (x >= WIDTH)
			return;
		if (y < 0 || y >= HEIGHT)
			return;
		super.setCellColor(x, y, color);
	}
}
