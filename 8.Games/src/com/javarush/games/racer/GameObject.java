package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class GameObject {
	public int x;
	public int y;
	public int width;
	public int height;
	public int[][] matrix;

	public GameObject(int x, int y, int[][] matrix) {
		this.x = x;
		this.y = y;
		this.matrix = matrix;
		this.height = matrix.length;
		this.width = matrix[0].length;
	}

	public void draw(Game game) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				game.setCellColor(x + i,y + j,Color.values()[matrix[i][j]]);
			}
		}
	}
}
