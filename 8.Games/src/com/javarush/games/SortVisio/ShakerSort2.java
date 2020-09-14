package com.javarush.games.SortVisio;

import java.util.List;

public class ShakerSort2 {
	int TIME_TO_WAIT = 29;
	private List<NumberClass> numbers;
	int sizeNumbers;
	int i;
	int j;
	NumberClass temp;
	int swap1;
	int swap2;
	int left;
	int right;
	boolean isEnd;

	public ShakerSort2(List<NumberClass> numbers) {
		this.numbers = numbers;
		sizeNumbers = numbers.size();
		left = 0;
		right = sizeNumbers - 1;
		isEnd = numbers.isEmpty();
	}

	public void step() throws InterruptedException {
		if (numbers.isEmpty()) {
			return;
		}
		left = 0;
		right = sizeNumbers - 1;
		while (left <= right) {
			for (int i = right; i > left; i--) {
				if (numbers.get(i - 1).num > numbers.get(i).num) {
					swap(numbers.get(i - 1).x, numbers.get(i).x);
				}
			}
			++left;
			for (int j = left; j < right; j++) {
				if (numbers.get(j).num > numbers.get(j + 1).num) {
					swap(numbers.get(j).x, numbers.get(j + 1).x);
				}
			}
			--right;
		}
		setEnd();
	}

	private void swap(int j1, int j2) throws InterruptedException {
		swap1 = j1;
		swap2 = j2;
		temp = numbers.get(j2);
		chose();
	}

	private void chose() throws InterruptedException {
		numbers.set(swap2, numbers.get(swap1));
		numbers.set(swap1, temp);
		for (int i = 0; i < sizeNumbers; i++) {
			numbers.get(i).x = i;
		}
	}

	private void setAndWait(int i,boolean b) throws InterruptedException {
		Thread.sleep(TIME_TO_WAIT);
		numbers.get(i).setChosen(b);
		Thread.sleep(TIME_TO_WAIT);
	}

	public void setEnd() {
		boolean answer = true;
		for (NumberClass num: numbers) {
			answer = answer && num.isWritePlace;
		}
		isEnd = answer;
		System.out.println(isEnd);
	}
}
