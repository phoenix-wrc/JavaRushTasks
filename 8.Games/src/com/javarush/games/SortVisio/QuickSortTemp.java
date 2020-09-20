package com.javarush.games.SortVisio;

import java.util.ArrayList;
import java.util.Collections;

public class QuickSortTemp {
	static Integer score = 0;
	static int[] numbers = numbersCast();
	static int sizeNumbers = numbers.length;

	public static void main(String[] args) {

		System.out.println();
		System.out.println();

		quickSort(numbers);
		for (int i = 0; i < sizeNumbers; i++) {
			System.out.print(numbers[i] + " ");
		}

		//System.out.println("\n" + score + "\n");
	}

	private static void quickSort(int[] numbers) {
		if (numbers.length > 0 ) {
			QuickSortImpl(numbers, 0, numbers.length - 1);
		}
		System.out.println("\n" + "\n" + score + "\n");
	}

	static int Partition(int[] values, int l, int r) {
		int x = values[r];
		int less = l;

		for (int i = l; i < r; ++i) {
			if (values[i] <= x) {
				swap(i, less, numbers);
				less++;
			}
		}
		swap(less, r, numbers);
		return less;
	}

	static void QuickSortImpl(int [] values, int l, int r) {
		if (l < r) {
			int q = Partition(values, l, r);
			QuickSortImpl(values, l, q - 1);
			QuickSortImpl(values, q + 1, r);
		}
		System.out.println("\n" + l + ' ' +r);
	}


	static void swap(int i, int j, int[] numbers) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
		System.out.print('.');
		score++;
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
