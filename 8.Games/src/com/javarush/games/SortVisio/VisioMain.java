package com.javarush.games.SortVisio;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VisioMain extends Game {
	public static final int WIDTH = 100, HEIGHT = 100;
	private List<NumberClass> numbers = new ArrayList<>();
	SortBubble2 bubble;
	private boolean isEnd;


	private void createGame() {
		initializeObject();
		drawField();
		setTurnTimer(100);//подбирается в ручную((
		drawScene();
	}

	public void initializeObject() {
		for (int i = 0; i <= WIDTH; i++) {
			numbers.add(new NumberClass(i, 0, i + 1));
		}
		Collections.shuffle(numbers);
		for (int i = 0; i <= WIDTH; i++) {
			numbers.get(i).x = i;
		}
		bubble = new SortBubble2(numbers);
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
		//step();
		try {
			bubble.step();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void step() {
		for(int i = 1 ; i < numbers.size(); i++)	{
			for (int j = numbers.size() - 1; j >= i; j--) {
				if (numbers.get(j - 1).num > numbers.get(j).num) {
					System.out.println(i);
					System.out.println(j);
					NumberClass temp = numbers.get(j - 1);
					numbers.set(j - 1, numbers.get(j));
					numbers.set(j, temp);
					for (int k = 0; k < numbers.size(); k++) {
						numbers.get(k).x = k;
					}
				}
			}
		}
//		if(!isEnd) {
//			try {
//				bubble.step();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			isEnd = true;
//		} else
//			return;
	}

	private void drawScene() {
		drawField();
		numbers.forEach(num -> num.draw(this));

//		bubble.draw(this);
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