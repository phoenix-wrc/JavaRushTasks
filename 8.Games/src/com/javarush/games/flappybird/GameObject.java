package com.javarush.games.flappybird;
import com.javarush.engine.cell.*;

import java.util.Random;

public class GameObject  {
	public double x, y, width, height; // координаты и размер матрици
	public int[][] matrix; // Матрицы
	Random random = new Random();   // делаем поток рандомных чисел, что бы каждый раз не имортить Game
	public double speed = FlappyBirdGame.SPEED;  // скорость движения объектов,
								// при корректировки придется подбирать
								// скорость появления земли((

	public GameObject (double x, double y, int [][] matrix)   {
		this.x = x;
		this.y = y;
		this.matrix = matrix;
		height = matrix.length;
		width = matrix[0].length;
	}

	public GameObject(double x, double y) { // Этот констукртор только для земли
		this.x = x;
		this.y = y;
	}
	// Рисовашка
	public void draw(Game game)   {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				game.setCellColor((int) x + i, (int) y + j, Color.values()[matrix[j][i]]);
			}
		}
	}
	// Двигаем объекты
	public void move() {
		x -= speed;
	}
}
