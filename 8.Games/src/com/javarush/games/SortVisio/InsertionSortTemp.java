package com.javarush.games.SortVisio;

import java.util.ArrayList;
import java.util.Collections;

public class InsertionSortTemp {

	public static void main(String[] args) {
		int[] numbers = numbersCast();
		int score = 0;
		int sizeNumbers = numbers.length;
		boolean isEnd = false;

		System.out.println();
		System.out.println();

		insertionSort(numbers, score);
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}

		System.out.println();

	}

	private static void insertionSort(int[] numbers, int score) {
		for (int i = 1; i < numbers.length; i++) {
			int temp = numbers[i];
			int j = i;
			while (j > 0 && numbers[j - 1] > temp) {
				numbers[j] = numbers[j - 1];
				score++;
				j--;
			}
			numbers[j] = temp;
		}
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
