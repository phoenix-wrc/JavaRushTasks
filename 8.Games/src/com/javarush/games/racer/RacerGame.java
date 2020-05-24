package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class RacerGame extends Game {
	public static final int WIDTH = 64, HEIGHT = 64, CENTER_X = WIDTH/2, ROADSIDE_WIDTH = 14;
	private RoadMarking roadMarking;
	private PlayerCar player;

	private void createGame() {
		roadMarking = new RoadMarking();
		player = new PlayerCar();
		setTurnTimer(40);

		drawScene();
	}

	private void drawScene() {
		drawField();
		roadMarking.draw(this);
		player.draw(this);
	}

	private void moveAll()  {
		roadMarking.move(player.speed);
		player.move();
	}

	private void drawField() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if(i == CENTER_X) {
					setCellColor(i,j,Color.WHITE);
				} else if(i < ROADSIDE_WIDTH) {
					setCellColor(i,j,Color.GREEN);
				} else if(i >= WIDTH - ROADSIDE_WIDTH) {
					setCellColor(i,j,Color.GREEN);
				} else {
					setCellColor(i,j,Color.DIMGRAY);
				}
			}
		}
	}

	@Override
	public void initialize() {
		showGrid(false);
		setScreenSize(WIDTH,HEIGHT);

		createGame();
	}

	@Override
	public void onTurn(int t) {
		moveAll();
		drawScene();
	}

	@Override
	public void setCellColor(int x, int y, Color color) {
		if(x < WIDTH && x >= 0 && y < HEIGHT && y >= 0) {
			super.setCellColor(x, y, color);
		}
	}

	@Override
	public void onKeyPress(Key key) {
		if(key == Key.RIGHT) player.setDirection(Direction.RIGHT);
		if(key == Key.LEFT) player.setDirection(Direction.LEFT);
	}
}
