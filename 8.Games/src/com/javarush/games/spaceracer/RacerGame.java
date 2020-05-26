package com.javarush.games.spaceracer;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceracer.space.RoadManager;

public class RacerGame extends Game {
	public static final int WIDTH = 100, HEIGHT = 100, CENTER_X = WIDTH / 2, ROADSIDE_WIDTH = 8;
	private static final int RACE_GOAL_CARS_COUNT = 40;
	private boolean isGameStopped;
	private int score;
	private PlayerShip player;
	private RoadManager roadManager;
	private FinishLine finishLine;
	private ProgressBar progressBar;

	private void createGame() {
		player = new PlayerShip();
		setTurnTimer(40);
		roadManager = new RoadManager();
		isGameStopped = false;
		finishLine = new FinishLine();
		progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
		score = 3500;
		drawScene();
	}

	private void drawScene() {
		drawField();
		finishLine.draw(this);
		roadManager.draw(this);
		player.draw(this);
		progressBar.draw(this);
	}

	private void moveAll()  {
		player.move();
		roadManager.move(player.speed);
		finishLine.move(player.speed);
		progressBar.move(roadManager.getPassedCarsCount());
	}

	private void gameOver() {
		isGameStopped = true;
		showMessageDialog(Color.AQUA, "Bay-bay", Color.BLACK, 40);
		stopTurnTimer();
		player.stop();
	}

	private void drawField() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if(i < ROADSIDE_WIDTH || i > HEIGHT - ROADSIDE_WIDTH) setCellColor(i,j,Color.DARKSLATEGRAY);
				else setCellColor(i,j,Color.BLACK);
			}
		}
	}

	@Override
	public void initialize() {
		showGrid(false);
		setScreenSize(WIDTH,HEIGHT);

		createGame();
	}

	private void win() {
		isGameStopped = true;
		showMessageDialog(Color.GREEN, "МОЛОДЕЧИК", Color.WHEAT, 50);
		stopTurnTimer();
	}

	@Override
	public void onTurn(int t) {
		if(RACE_GOAL_CARS_COUNT <= roadManager.getPassedCarsCount()) finishLine.show();
		if (roadManager.checkCrush(player)) {
			gameOver();
		} else if(finishLine.isCrossed(player)) {
			win();
		} else {
			moveAll();
			roadManager.generateNewRoadObjects(this);
			score -= 5;
		}
		setScore(score);
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
		if(key == Key.SPACE && isGameStopped) createGame();
		if(key == Key.UP) player.accelerate();
		if(key == Key.DOWN) player.brake();
	}

	@Override
	public void onKeyReleased(Key key) {
		if(key == Key.RIGHT && player.getDirection() == Direction.RIGHT)
			player.setDirection(Direction.NONE);
		if(key == Key.LEFT && player.getDirection() == Direction.LEFT)
			player.setDirection(Direction.NONE);
	}
}
