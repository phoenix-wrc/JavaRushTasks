package com.javarush.games.SortVisio;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VisioMain extends Game {
	public static final int WIDTH = 100, HEIGHT = 100;
	private List<NumberClass> numbers = new ArrayList<>();
	SortBubble bubble;
	private boolean isEnd;


	private void createGame() {
		initializeObject();
		drawField();
		setTurnTimer(5);//подбирается в ручную((
		drawScene();
	}

	public void initializeObject() {
		for (int i = 0; i <= WIDTH; i++) {
			numbers.add(new NumberClass(i, 0, i));
		}
		Collections.shuffle(numbers);
		for (int i = 0; i <= WIDTH; i++) {
			numbers.get(i).x = i;
		}
		bubble = new SortBubble(numbers);
		isEnd = false;
	}

	private void drawField() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT-6; j++) {
				setCellColor(i, j, Color.SKYBLUE);
			}
		}
	}

	@Override
	public void onTurn(int t)    {
		drawScene();
		try {
			bubble.step();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void step() {
		if(!isEnd) {
			try {
				bubble.step();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			isEnd = true;
		} else
			return;
	}

	private void drawScene() {
		numbers.forEach(num -> num.draw(this));
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