package com.javarush.games.SortVisio;

import java.util.List;

public class SortBubble3 {
	private int swap1, swap2, i, j;
	public int score;
	NumberClass temp;
	private List<NumberClass> numbers;
	int sizeNumbers;
	boolean isEnd;
	private boolean isChose, isSwap, isSecondStep, isPassStep;

	public SortBubble3(List<NumberClass> numbers) {
		this.numbers = numbers;
		sizeNumbers = numbers.size();
		i = 1;
		j = sizeNumbers - 1;
		score = 0;
		isEnd = numbers.isEmpty();
		isSwap = false;
		isSecondStep = false;
		isChose = false;
		isPassStep = false;
	}

	public void step() {
		if(isEnd) {
			numbers.get(sizeNumbers - 1).setChosen(false);
			return;
		}
		else if(isChose)
			chose();
		else if(isSwap)
			swap();
		else if(isPassStep)
			passStep();
		else if(isSecondStep)
			stepInternal();
		else
			stepOut();

		for (int i = 0; i < sizeNumbers; i++) {
			numbers.get(i).x = i;
		}
	}

	public void stepOut() {
		if (i < sizeNumbers) {
			isSecondStep = true;
		} else {
			i = 1;
			j = sizeNumbers - 1;
			setEnd();
		}
	}

	private void stepInternal() {
		if (j >= i) {
			numbers.get(i-1).setChosen(false);
			if(j + 1 < sizeNumbers)
				numbers.get(j+1).setChosen(false);
			numbers.get(j).setChosen(true);
			if (numbers.get(j - 1).num > numbers.get(j).num) {
				swap1 = j;
				swap2 = j - 1;
				isSwap = true;
			} else {
				isPassStep = true;
			}
			j--;
		}   else    {
			numbers.get(j).setChosen(false);
			j = sizeNumbers - 1;
			i++;
			isSecondStep = false;
		}
	}

	private void passStep() {
		isPassStep = false;
	}

	private void swap() {
		numbers.get(swap2).setChosen(true);
		numbers.get(swap1).setChosen(true);

		temp = numbers.get(swap2);
		numbers.set(swap2, numbers.get(swap1));
		numbers.set(swap1, temp);

		isChose = true;
		isSwap = false;
	}

	private void chose() {
		numbers.get(swap1).setChosen(false);
		isChose = false;
		score++;
	}

	public void setEnd() {
		boolean answer = true;
		for (NumberClass num: numbers) {
			answer = answer && num.isWritePlace;
		}
		isEnd = answer;
	}
}
