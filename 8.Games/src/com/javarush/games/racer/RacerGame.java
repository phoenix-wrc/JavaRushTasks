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

	}

	@Override
	public void initialize() {
		showGrid(false);
		setScreenSize(WIDTH,HEIGHT);

		createGame();
	}
}
