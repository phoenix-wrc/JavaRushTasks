package com.javarush.games.SortVisio;

import java.util.List;

public class ShakerSort {
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
	Thread thread;

	public ShakerSort(List<NumberClass> numbers) {
		this.numbers = numbers;
		sizeNumbers = numbers.size();
		left = 0;
		right = sizeNumbers - 1;
		isEnd = numbers.isEmpty();
		thread = new Thread(() -> {
			try {
				while(!isEnd)
					step();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}

	public void step() throws InterruptedException {
		if (left <= right) {
			stepOne();
			Thread.sleep(TIME_TO_WAIT);
			left++;
			Thread.sleep(TIME_TO_WAIT);
			stepTwo();
			Thread.sleep(TIME_TO_WAIT);
			right--;
			Thread.sleep(TIME_TO_WAIT);
		} else {
			left = 0;
			right = sizeNumbers - 1;
			setEnd();
		}

	}

	private void stepOne() throws InterruptedException {
		for (i = right; i > left; i--) {
			Thread.sleep(TIME_TO_WAIT);
			numbers.get(i).setChosen(true);
			Thread.sleep(TIME_TO_WAIT);
			if (numbers.get(i - 1).num > numbers.get(i).num) {
				numbers.get(i - 1).setChosen(true);
				numbers.get(i).setChosen(true);
				Thread.sleep(TIME_TO_WAIT);
				swap(numbers.get(i - 1).x, numbers.get(i).x);
				Thread.sleep(TIME_TO_WAIT);
				numbers.get(i - 1).setChosen(false);
				numbers.get(i).setChosen(false);
			}
			numbers.get(i - 1).setChosen(false);
			numbers.get(i).setChosen(false);
			Thread.sleep(TIME_TO_WAIT);
		}
	}

	private void stepTwo() throws InterruptedException {
		for (j = left; j < right; j++) {
			Thread.sleep(TIME_TO_WAIT);
			numbers.get(j).setChosen(true);
			if (numbers.get(j).num > numbers.get(j + 1).num) {
				numbers.get(j + 1).setChosen(true);
				numbers.get(j).setChosen(true);
				Thread.sleep(TIME_TO_WAIT);
				swap(numbers.get(j).x, numbers.get(j + 1).x);
				Thread.sleep(TIME_TO_WAIT);
				numbers.get(j + 1).setChosen(false);
				numbers.get(j).setChosen(false);
			}
			Thread.sleep(TIME_TO_WAIT);
			numbers.get(j + 1).setChosen(false);
			numbers.get(j).setChosen(false);
		}
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

	public void setEnd() {
		boolean answer = true;
		for (NumberClass num: numbers) {
			answer = answer && num.isWritePlace;
		}
		isEnd = answer;
		System.out.println(isEnd);
	}
}
