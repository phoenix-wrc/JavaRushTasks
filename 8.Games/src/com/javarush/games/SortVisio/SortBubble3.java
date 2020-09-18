package com.javarush.games.SortVisio;

import java.util.List;

public class SortBubble3 {
	int swap1, swap2, i, j;
	public int score;
	NumberClass temp;
	List<NumberClass> numbers;
	int sizeNumbers;
	int offIndex;
	boolean isEnd;
	boolean isChose;
	boolean isSwap;
	boolean isSecondStep;
	boolean isPassStep;

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

	void stepInternal() {
		if(j < sizeNumbers - 1)
			numbers.get(j + 1).setChosen(false);
		numbers.get(j).setChosen(false);
		if (j >= i) {

			numbers.get(j).setChosen(true);
			numbers.get(j - 1).setChosen(true);
			if (numbers.get(j - 1).num > numbers.get(j).num) {
				swap1 = j;
				swap2 = j - 1;
				isSwap = true;
			} else {
				isPassStep = true;
				offIndex =  j;
			}
			j--;
		}   else    {
			j = sizeNumbers - 1;
			i++;
			isSecondStep = false;
		}
	}

	void passStep() {
		isPassStep = false;

		for (int i = 0; i < sizeNumbers; i++) {
			numbers.get(i).x = i;
		}
	}

	public void swap() {

		temp = numbers.get(swap2);
		numbers.set(swap2, numbers.get(swap1));
		numbers.set(swap1, temp);

		isChose = true;
		isSwap = false;
	}

	public void chose() {
		isChose = false;
		score++;

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
