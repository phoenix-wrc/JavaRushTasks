package com.javarush.games.SortVisio;

import java.util.List;

public class SortBubble2 {
	int i;
	int j;
	NumberClass temp;
	int swap1;
	int swap2;
	private List<NumberClass> numbers;
	int sizeNumbers;

	public void setEnd() {
		boolean answer = true;
		for (NumberClass num: numbers) {
		answer = answer && num.isWritePlace;
		}
		isEnd = answer;
	}

	boolean isEnd;

	public SortBubble2(List<NumberClass> numbers) {
		this.numbers = numbers;
		sizeNumbers = numbers.size();
		i = 1;
		j = sizeNumbers - 1;
		isEnd = false;
		Thread thread = new Thread(() -> {
			try {
				while(!isEnd)
					stepOut();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}

	public void step() throws InterruptedException {
	}

	public void stepOut() throws InterruptedException {
		if (i < sizeNumbers) {
			stepInternal();
		} else {
			i = 1;
			j = sizeNumbers - 1;
			setEnd();
		}
	}

	private void stepInternal() throws InterruptedException {
		if (j >= i) {
			if (numbers.get(j - 1).num > numbers.get(j).num) {
				numbers.get(j).setChosen();
				numbers.get(j - 1).setChosen();
				Thread.sleep(15);
				swap(j, j - 1);
				Thread.sleep(5);
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
}
