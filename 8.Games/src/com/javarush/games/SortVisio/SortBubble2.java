package com.javarush.games.SortVisio;

import java.util.List;

public class SortBubble2 {
	int i;
	int j;
	private List<NumberClass> numbers;
	int sizeNumbers;

	public SortBubble2(List<NumberClass> numbers) {
		this.numbers = numbers;
		sizeNumbers = numbers.size();
		i = 1;
		j = sizeNumbers - 1;
	}

	public void step() throws InterruptedException {
		stepOut();
	}

	public void stepOut() throws InterruptedException {
		if (i < sizeNumbers) {
			stepInternal();
		} else {
			i = 1;
			j = sizeNumbers - 1;
		}
	}

	private void stepInternal() throws InterruptedException {
		if (j >= i) {
			if (numbers.get(j - 1).num > numbers.get(j).num) {
				numbers.get(j).setChosen();
				numbers.get(j - 1).setChosen();
				Thread.sleep(50);
				swap(j, j - 1);
				numbers.get(j).setChosen();
				numbers.get(j - 1).setChosen();
			} else {

			}
			j--;
		} else {
			j = sizeNumbers - 1;
			i++;
		}
	}

	private void swap(int j1, int j2) throws InterruptedException {
		NumberClass temp = numbers.get(j2);
		numbers.set(j2, numbers.get(j1));
		numbers.set(j1, temp);
		Thread.sleep(5);
		for (int i = 0; i < sizeNumbers; i++) {
			numbers.get(i).x = i;
		}
	}
}
