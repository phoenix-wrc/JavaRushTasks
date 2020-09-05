package com.javarush.games.flappybird;

import com.javarush.engine.cell.*;

public class FlappyBirdGame extends Game {
	public static final int WIDTH = 100, HEIGHT = 100;
	public static final double SPEED = 1.0;
	private Bird bird;
	private GameObjectManager gameObjects;
	private boolean isGameStopped;

	private void createGame() {
		bird = new Bird(3 , (double)(HEIGHT - ShapeMatrix.BIRD_1.length)/2);
		//ставим птицу в середину
		gameObjects = new GameObjectManager();
		//управление всеми объектами кроме птици
		gameObjects.initializeObject();

		setTurnTimer(40);//подбирается в ручную((
		drawScene();
	}

	private void drawField() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT-6; j++) {
				setCellColor(i, j, Color.SKYBLUE);
			}
		}
	}

	private void drawScene() {
		drawField();

		gameObjects.draw(this);
		bird.draw(this);
	}

	private void moveAll() {
		gameObjects.move();
		bird.move();
	}

	@Override
	public void onMouseLeftClick(int x, int y)  {
		//if (isGameStopped) {
		//	restart();
		//} else {
			bird.flap();
		//}
	}

	private void restart() {
	}

	@Override
	public void onTurn(int t)    {
		moveAll();
		//gameObjects.generateNewObjects(this);
		//if(bird.y > HEIGHT - 6 - bird.height) {
		//	bird.dead();
		//	bird.y = HEIGHT - 6 - bird.height;
		//}
		isGameStopped = gameObjects.checkCollision(bird);
		drawScene();
	}

	@Override
	public void initialize() {
		setScreenSize(WIDTH, HEIGHT);
		showGrid(false);
		createGame();
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
