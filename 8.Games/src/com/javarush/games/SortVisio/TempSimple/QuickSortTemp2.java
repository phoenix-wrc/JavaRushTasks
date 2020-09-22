package com.javarush.games.SortVisio.TempSimple;

import java.util.ArrayList;
import java.util.Collections;

public class QuickSortTemp2 {
	static Integer score = 0;
	static int[] numbers = numbersCast();
	static int sizeNumbers = numbers.length;

	public static void main(String[] args) {

		System.out.println();
		System.out.println();

		quickSort();
		for (int i = 0; i < sizeNumbers; i++) {
			System.out.print(numbers[i] + " ");
		}
	}

	private static void quickSort() {
		if (numbers.length > 0 ) {
			QuickSortImpl(0, numbers.length - 1);
		}
		System.out.println("\n" + "\n" + score + "\n");
	}

	static void QuickSortImpl(int l, int r) {
		System.out.println("\n" + l + ' ' +r);

		if (l < r) {
			int q = Partition(l, r);
			System.out.print(' ' + "q - " + q);
			QuickSortImpl( l, q - 1);
			QuickSortImpl( q + 1, r);
		}
		System.out.println(" - - - - - - - - - - - - - - - - - - ");
	}

	static int Partition(int l, int r) {
		int x = numbers[r];
		int less = l;

		for (int i = l; i < r; i++) {
			if (numbers[i] <= x) {
				swap(i, less);
				less++;
			}
		}
		swap(less, r);
		System.out.println(" - less - " + less);
		return less;
	}

	static void swap(int i, int j) {
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
