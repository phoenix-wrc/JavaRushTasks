package com.javarush.games.SortVisio;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

public class NumberClass {
	public int num;
	public int x, y, width, height; // координаты и размер матрици
	public int[][] matrix; // Матрицы

	public void setChosen() {
		this.chosen = !chosen;
	}

	public boolean chosen;
	Color basicColor = Color.YELLOW;
	Color backColor = Color.DARKBLUE;
	Color chosenColor = Color.RED;

	public NumberClass (int x, int y, int num)   {
		this.x = x;
		this.y = y;
		this.num = num;
		width = 1;
		height = VisioMain.HEIGHT;
		chosen = false;
		matrix = getMatrix(num);
	}

	private int[][] getMatrix(int num) {
		int [][] matrix = new int[height][1];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (j < num)
					matrix[j][i] = basicColor.ordinal();
				else
					matrix[j][i] = backColor.ordinal();
			}
		}
		return matrix;
	}

	public void draw(Game game)   {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if(!chosen)
					game.setCellColor(x + i, y + j,
						Color.values()[matrix[j][i]]);
				else
					game.setCellColor(x + i, y + j,
							chosenColor);
			}
		}
	}
}

