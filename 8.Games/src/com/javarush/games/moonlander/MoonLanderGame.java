package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class MoonLanderGame extends Game {
	public static final int WIDTH = 64, HEIGHT = 64;
	private Rocket rocket;
	private GameObject landscape;
	private GameObject earth;
	private boolean isUpPressed, isLeftPressed, isRightPressed, isGameStopped;
	private GameObject platform;
	private int score;

	private void check()    {
		if(rocket.isCollision(landscape) &&
				!(rocket.isCollision(platform) &&
						rocket.isStopped())) {
			gameOver();
		}
		if(rocket.isCollision(platform) && rocket.isStopped())
				win();
	}

	private void win()  {
		rocket.land();
		isGameStopped = true;
		showMessageDialog(Color.GREENYELLOW,"Winer", Color.BLACK, 75);
		stopTurnTimer();
		System.out.println("win");
	}

	private void gameOver() {
		rocket.crash();
		isGameStopped = true;
		showMessageDialog(Color.RED, "Loose the cow", Color.WHITE,75);
		stopTurnTimer();
		score = 0;
		System.out.println("notWin");
	}

	@Override
	public void onKeyPress(Key key)    {
		if(key == Key.RIGHT) {
			isRightPressed = true;
			isLeftPressed = false;
		}
		if(key == Key.LEFT) {
			isLeftPressed = true;
			isRightPressed = false;
		}
		if(key == Key.UP)
			isUpPressed = true;

		if(key == Key.SPACE && isGameStopped)
			createGame();
	}

	@Override
	public void onKeyReleased(Key key) {
		if(key == Key.RIGHT)
			isRightPressed = false;
		if(key == Key.LEFT)
			isLeftPressed = false;
		if(key == Key.UP)
			isUpPressed = false;
	}

	@Override
	public void onTurn(int t)    {
		rocket.move(isUpPressed,isLeftPressed, isRightPressed);
		if(score > 0)
			score -=1;
		check();
		setScore(score);
		drawScene();
	}

	private void createGameObjects()    {
		landscape = new GameObject(0,25,ShapeMatrix.LANDSCAPE);
		rocket = new Rocket(WIDTH/2, 0);
		platform = new GameObject(23, MoonLanderGame.HEIGHT-1,ShapeMatrix.PLATFORM);
		earth = new GameObject(23, 2,ShapeMatrix.EARTH);
	}

	@Override
	public void initialize ()   {
		setScreenSize(WIDTH, HEIGHT);
		showGrid(false);
		createGame();
	}

	private void createGame() {
		isGameStopped = false;
		isLeftPressed = false;
		isRightPressed = false;
		isUpPressed = false;
		score = 1000;
		setTurnTimer(50);
		createGameObjects();
		drawScene();
	}
	
	private void drawScene() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				setCellColor(i,j,Color.values()[144]);
			}
		}
		earth.draw(this);
		rocket.draw(this);
		landscape.draw(this);
	}

	@Override
	public void setCellColor(int x, int y, Color color)  {
		if (x>=0 && x < HEIGHT && y >=0 && y < WIDTH)
			super.setCellColor(x,y,color);
	}
}
