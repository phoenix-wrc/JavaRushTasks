package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class RacerGame extends Game {
	public static final int WIDTH = 64, HEIGHT = 64, CENTER_X = WIDTH/2, ROADSIDE_WIDTH = 14;

	private void createGame() {
		drawScene();
	}

	private void drawScene() {
		drawField();
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
	public void setCellColor(int x, int y, Color color) {
		if(x < WIDTH && x >= 0 && y < HEIGHT && y >= 0) {
			super.setCellColor(x, y, color);
		}
	}
}
