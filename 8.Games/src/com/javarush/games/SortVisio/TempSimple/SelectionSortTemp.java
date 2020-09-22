package com.javarush.games.SortVisio.TempSimple;

import java.util.ArrayList;
import java.util.Collections;

public class SelectionSortTemp {
	public static void main(String[] args) {
		int[] numbers = numbersCast();
		int score = 0;
		int sizeNumbers = numbers.length;
		boolean isEnd = false;

		System.out.println();
		System.out.println();

		selectionSort(numbers, score);
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}

		System.out.println();

	}

	private static void selectionSort(int[] numbers, int score) {

		for (int left = 0; left < numbers.length; left++) {
			int minInd = left;
			for (int i = left; i < numbers.length; i++) {
				if (numbers[i] < numbers[minInd]) {
					minInd = i;
				}
			}
			swap(numbers, left, minInd);
			score++;
		}
	System.out.println(score);
	System.out.println();
	}

	static void swap(int[] numbers,int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
		System.out.print('.');
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
