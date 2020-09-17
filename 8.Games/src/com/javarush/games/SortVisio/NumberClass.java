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

	public void setWritePlace() {
		isWritePlace = x == num - 1;
	}

	public boolean isWritePlace;
	Color basicColor = Color.BLACK;
	Color backColor = Color.SKYBLUE;
	Color chosenColor = Color.ORANGERED;
	Color wrightColor = Color.DARKGREEN;

	public NumberClass (int x, int y, int num)   {
		this.x = x;
		this.y = y;
		this.num = num + 1;
		width = 1;
		height = VisioMain.HEIGHT;
		chosen = false;
		isWritePlace = false;
		matrix = getMatrix(num);
	}

	private int[][] getMatrix(int num) {
		int [][] matrix = new int[height][1];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (j > num)
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
				if(chosen) {
					if (j <= height - num)
						game.setCellColor(x + i, y - j , chosenColor);
					else
						game.setCellColor(x + i, y - j , backColor);
				} else if (isWritePlace){
					if (j <= height - num)
						game.setCellColor(x + i, y - j , wrightColor);
					else
						game.setCellColor(x + i, y - j , backColor);
				} else {
					if (j <= height - num)
						game.setCellColor(x + i, y - j , basicColor);
					else
						game.setCellColor(x + i, y - j , backColor);
				}
			}
		}
	}

	public void setChosen(boolean b) {
		chosen = b;
	}
}

