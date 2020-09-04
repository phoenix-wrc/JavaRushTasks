package com.javarush.games.flappybird;

import com.javarush.engine.cell.*;
import org.jetbrains.annotations.NotNull;

public class GroundPart extends GameObject {

	public GroundPart(double x, double y) {
		super(x, y);
		matrix = createMatrix();
		height = matrix.length;
		width = matrix[0].length;
	}
	// создаем новую часть и красим по алгоритму - чем ниже пиксель
	// тем выше вероятность что он будет темный. Верхний всегда зеленый.
	@NotNull
	private int[][] createMatrix() {
		int[][] matrix = new int[6][1];
		for (int j = 0; j < 6; j++) {
			if (j == 0) {
				if (random.nextInt(5) < 1)
					matrix[j][0] = Color.DARKGREEN.ordinal();
				else
					matrix[j][0] = Color.GREEN.ordinal();
			} else {
				int rnd = random.nextInt(5);
				if (rnd > j)
					matrix[j][0] = Color.SANDYBROWN.ordinal();
				else if (rnd > 2 * j)
					matrix[j][0] = 119;
				else if (rnd > 3 * j)
					matrix[j][0] = 24;
				else
					matrix[j][0] = 126;
			}
		}
		return matrix;
	}

	public void reCreateMatrix() {
		matrix = createMatrix();
	}
}
