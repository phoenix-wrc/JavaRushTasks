package com.javarush.games.SortVisio;

import java.util.List;

public class Sort {
	public int score;
	List<NumberClass> numbers;
	int sizeNumbers;
	NumberClass temp;
	int iPatition;
	int j;
	int swap1;
	int swap2;
	boolean isEnd, isSwap, isChose, isPassStep;

	Sort(List<NumberClass> numbers) {
		this.numbers = numbers;
		sizeNumbers = numbers.size();
		isEnd = numbers.isEmpty();
		temp = null;
		score = 0;
		iPatition = 0;
		j = 0;
		swap1 = 0;
		swap2 = 0;
		isChose = false;
		isSwap = false;
		isPassStep = false;
	}

	public void step() {
		if (isEnd)
			return;
		else if (isPassStep)
			offStep();
		else if (isChose)
			chose();
		else if (isSwap)
			swap();
	}


	void swap() {
		temp = numbers.get(swap2);
		numbers.set(swap2, numbers.get(swap1));
		numbers.set(swap1, temp);

		isChose = true;
		isSwap = false;
	}

	private void chose() {
		score++;
		isChose = false;

		for (int i = 0; i < sizeNumbers; i++) {
			numbers.get(i).x = i;
		}
	}

	private void offStep() {
		isPassStep = false;

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
	}
}
