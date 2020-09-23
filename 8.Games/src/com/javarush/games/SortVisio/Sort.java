package com.javarush.games.SortVisio;

import java.util.List;

public class Sort {
	public int score;
	List<NumberClass> numbers;
	int sizeNumbers;
	NumberClass temp;
	Sounds sounds;
	String name, subName;
	int i, j, swap1, swap2;
	boolean isEnd, isSwap, isChose, isPassStep;

	Sort(List<NumberClass> numbers) {
		this.numbers = numbers;
		sizeNumbers = numbers.size();
		sounds = new Sounds();
		isEnd = numbers.isEmpty();
		temp = null;
		name = "";
		subName = "";
		score = 0;
		i = 0;
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

		sounds.playSwap();

		for (int i = 0; i < sizeNumbers; i++) {
			numbers.get(i).x = i;
		}
	}

	void offStep() {
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
