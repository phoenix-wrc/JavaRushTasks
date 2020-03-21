package com.javarush.games.flappybird;

import com.javarush.engine.cell.*;
import org.jetbrains.annotations.NotNull;

public class GroundPart extends GameObject {

	public GroundPart(double x, double y) {
		super(x, y);
		matrix = createPart();
		height = matrix.length;
		width = matrix[0].length;
	}
	// создаем новую часть и красим по алгоритму - чем ниже пиксель
	// тем выше вероятность что он будет темный. Верхний всегда зеленый.
	@NotNull
	private int[][] createPart () {
		int[][] matrix = new int[6][1];
		for (int j = 0; j < 6; j++) {
			if(j == 0) {
				if(random.nextInt(5) < 1)
					matrix[j][0] = Color.DARKGREEN.ordinal();
				else
					matrix[j][0] = Color.GREEN.ordinal();
			} else {
				if(random.nextInt(4) > j*4)
					matrix[j][0] = Color.SANDYBROWN.ordinal();
				else if(random.nextInt(5) > 2*j)
					matrix[j][0] = 119;
				else if(random.nextInt(4) > 1)
					matrix[j][0] = 24;
				else
					matrix[j][0] = 126;
			}
			/*
			if(j == 0) {
				if(random.nextInt(5) < 1)
					matrix[j][0] = Color.DARKGREEN.ordinal();
				else
					matrix[j][0] = Color.GREEN.ordinal();
			} else if (j == 1) {
				if(random.nextInt(10) < 8)
					matrix[j][0] = Color.SANDYBROWN.ordinal();
				else if(random.nextInt(5) < 1)
					matrix[j][0] = 119;
				else if(random.nextInt(4) < 1)
					matrix[j][0] = 24;
				else
					matrix[j][0] = 126;
			} else if (j == 2) {
				if(random.nextInt(10) < 5)
					matrix[j][0] = Color.SANDYBROWN.ordinal();
				else if(random.nextInt(5) < 1)
					matrix[j][0] = 119;
				else if(random.nextInt(4) < 1)
					matrix[j][0] = 24;
				else
					matrix[j][0] = 126;
			} else if (j == 3) {
				if(random.nextInt(10) < 3)
					matrix[j][0] = Color.SANDYBROWN.ordinal();
				else if(random.nextInt(4) < 1)
					matrix[j][0] = 119;
				else if(random.nextInt(3) < 1)
					matrix[j][0] = 24;
				else
					matrix[j][0] = 126;
			} else {
				if(random.nextInt(10) < 1)
					matrix[j][0] = Color.SANDYBROWN.ordinal();
				else if(random.nextInt(4) < 1)
					matrix[j][0] = 119;
				else if(random.nextInt(3) < 1)
					matrix[j][0] = 24;
				else
					matrix[j][0] = 126;
			}*/
		}
		return matrix;
	}
}
