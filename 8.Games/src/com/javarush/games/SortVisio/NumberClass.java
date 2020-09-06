package com.javarush.games.SortVisio;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

public class NumberClass {
	public int num;
	public int x, y, width, height; // координаты и размер матрици
	public int[][] matrix; // Матрицы

	public NumberClass (int x, int y, int num)   {
		this.x = x;
		this.y = y;
		this.num = num;
		width = 1;
		height = VisioMain.HEIGHT;
		matrix = getMatrix(num);
	}

	private int[][] getMatrix(int num) {
		int [][] matrix = new int[height][1];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (j < num)
					matrix[j][i] = 29;
				else
					matrix[j][i] = 3;
			}
		}
		return matrix;
	}

	public void draw(Game game)   {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				//System.out.println(x + " " + i + " - " + y + " " + j);
				game.setCellColor(x + i, y + j,
						Color.values()[matrix[j][i]]);
			}
		}
	}
}

