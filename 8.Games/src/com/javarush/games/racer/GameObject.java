package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class GameObject  {
	public int x, y, width, height;
	public int[][] matrix;


	public GameObject (int x, int y, int [][] matrix)   {
		this.x = x;
		this.y = y;
		this.matrix = matrix;
		height = matrix.length;
		width = matrix[0].length;
	}

	public GameObject (int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Game game)   {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				game.setCellColor(x + i, y + j, Color.values()[matrix[j][i]]);
			}
		}
	}
}
