package com.javarush.games.SortVisio;

import java.util.ArrayList;
import java.util.List;

public class SortBubble {
	int idx_i = 0;
	int idx_j = 0;
	private List<NumberClass> numbers;
	int sizeNumbers;

	public SortBubble(List<NumberClass> numbers) {
		this.numbers = numbers;
		sizeNumbers = numbers.size();
	}

	public void step() throws InterruptedException {
		stepOut();
	}

	public void stepOut() throws InterruptedException {
		//Thread.sleep(100);
		if (idx_i + 1 < sizeNumbers) {
			stepInternal();
			++idx_i;
		} else idx_i = 0;
	}

	private void stepInternal() throws InterruptedException {
		if (idx_j + 1 < sizeNumbers - idx_i) {
			if (numbers.get(idx_j + 1).num > numbers.get(idx_j).num) {
				swap(numbers.get(idx_j).x, numbers.get(idx_j + 1).x);
			}
			++idx_j;
		} else idx_j = 0;
	}

	private void swap(int j1, int j2) throws InterruptedException {
		NumberClass temp = numbers.get(j2);
		numbers.set(j2, numbers.get(j1));
		numbers.set(j1, temp);
		Thread.sleep(50);
		for (int i = 0; i < sizeNumbers; i++) {
			numbers.get(i).x = i;
		}
	}
}
