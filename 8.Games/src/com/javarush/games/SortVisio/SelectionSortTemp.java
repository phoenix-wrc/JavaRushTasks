package com.javarush.games.SortVisio;

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
		for (int i = numbers[0];
		            i != numbers[numbers.length]; i++) {
			int j;
			if(i < numbers[numbers.length])
				j = i;
			else j = numbers[numbers.length];
			int temp = numbers[i];
			numbers[i] = numbers[j];
			numbers[j] = temp;
		}
	System.out.println(score);
	System.out.println();
	}
//	void SelectionSort(vector<int>& values) {
//		for (auto i = values.begin(); i != values.end(); ++i) {
//			auto j = std::min_element(i, values.end());
//			swap(*i, *j);
//		}
//	}

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
