package com.javarush.games.SortVisio;

import java.util.ArrayList;
import java.util.Collections;

public class CombSortTemp {
	static final double factor = 1.247; // Фактор уменьшения

	public static void main(String[] args) {
		int[] numbers = numbersCast();
		int score = 0;

		System.out.println();
		System.out.println();

		combSort(numbers);
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}

		System.out.println();
	}

	static void combSort(int[] values) {
		double step = values.length - 1;
		int score = 0;

		while (step >= 1) {
			for (int i = 0; i + step < values.length; i++) {
				score++;
				if (values[i] > values[(int) (i + step)]) {
					int temp = values[i];
					values[i] = values[(int) (i + step)];
					values[(int) (i + step)] = temp;
				}
			}
			step /= factor;
		}
		// сортировка пузырьком
		for (int idx_i = 0; idx_i + 1 < values.length; idx_i++) {
			for (int idx_j = 0; idx_j + 1 < values.length
					- idx_i; idx_j++) {
				if (values[idx_j + 1] < values[idx_j]) {
					int temp = values[idx_j];
					values[idx_j] = values[idx_j + 1];
					values[idx_j + 1] = temp;
					score++;
				}
			}
		}
		System.out.println();
		System.out.println(score);
		System.out.println();
	}


	private static int[] numbersCast() {
		int[] temp = new int[100];
		ArrayList<Integer> tempArray= new ArrayList<>();
		for (int i = 0; i < temp.length; i++) {
			tempArray.add(i + 1);
		}

		Collections.shuffle(tempArray);

		for (int i = 0; i < temp.length; i++) {
			temp[i] = tempArray.get(i);
			System.out.print(temp[i] + " ");
		}
		return temp;
	}
}
