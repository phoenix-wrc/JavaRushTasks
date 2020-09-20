package com.javarush.games.SortVisio;

import java.util.List;

public class ShakerSort3 {
	public int score;
	private List<NumberClass> numbers;
	int sizeNumbers;
	NumberClass temp;
	int i;
	int j;
	int swap1;
	int swap2;
	boolean isEnd, isSwap, isChose, isPassStep;
	int left;
	int right;
	boolean isSecondStep, isFirstStep;
	private int offIndex;

	public ShakerSort3(List<NumberClass> numbers) {
		this.numbers = numbers;
		sizeNumbers = numbers.size();
		score = 0;
		left = 0;
		right = sizeNumbers - 1;
		isSwap = false;
		isSecondStep = false;
		isFirstStep = false;
		isChose = false;
		isEnd = numbers.isEmpty();
	}

	public void step() {
		if(isEnd)
			return;
		else if(isPassStep)
			offStep();
		else if(isChose)
			chose();
		else if(isSwap)
			swap();
		else if(isFirstStep)
			firstStep();
		else if(isSecondStep)
			secondStep();
		else
			stepOuter();
	}

	private void stepOuter() {
		if (left <= right) {
			isFirstStep = true;
			i = right;
			isSecondStep = true;
			j = left;
		} else {
			left = 0;
			right = sizeNumbers - 1;
			setEnd();
		}
	}

	private void firstStep() {
		if (i > left) {
			if(i < sizeNumbers - 1)
				numbers.get(i + 1).setChosen(false);
			numbers.get(i).setChosen(false);
			if(i + 1 < sizeNumbers)
				numbers.get(i - 1).setChosen(true);
				numbers.get(i).setChosen(true);
			if (numbers.get(i - 1).num > numbers.get(i).num) {
				swap1 = i - 1;
				swap2 = i;
				isSwap = true;
			}   else    {
				offIndex = i;
				isPassStep = true;
			}
			i--;
		}   else    {
			isFirstStep = false;
			left++;
		}
	}

	private void secondStep() {
		if(j > 0)
			numbers.get(j - 1).setChosen(false);
		numbers.get(j).setChosen(false);

		if ( j < right) {
			numbers.get(j).setChosen(true);
			numbers.get(j + 1).setChosen(true);
			if (numbers.get(j).num > numbers.get(j + 1).num) {
				swap1 = j + 1;
				swap2 = j;
				isSwap = true;
			}   else    {
				offIndex = j;
				isPassStep = true;
			}
			j++;
		}   else    {
			isSecondStep = false;
			right--;
		}
	}

	private void swap() {
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
