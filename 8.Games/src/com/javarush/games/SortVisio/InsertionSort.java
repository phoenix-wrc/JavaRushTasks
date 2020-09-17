package com.javarush.games.SortVisio;

import java.util.List;

public class InsertionSort {
	private boolean isChose;
	private boolean isFirstStep;
	private boolean isSecondStep;
	private boolean isChange;
	int swap1, swap2, i, j;
	public int score;
	NumberClass temp;
	List<NumberClass> numbers;
	int sizeNumbers;
	boolean isEnd;
	private boolean isOffStep;
	private boolean isLastStep;
	private int offIndex;
	private boolean isReturnChange;

	public InsertionSort(List<NumberClass> numbers) {
		this.numbers = numbers;
		isEnd = numbers.isEmpty();
		i = 1;
		score = 0;
		offIndex = 0;
		temp = null;
		sizeNumbers = numbers.size();
		isChange = false;
		isSecondStep = false;
		isFirstStep = false;
		isChose = false;
		isOffStep = false;
		isLastStep = false;
		isReturnChange = false;
	}

	public void step() {
		if (isEnd)
			return;
		else if (isChose)
			chose();
		else if (isOffStep)
			offStep();
		else if(isReturnChange)
			returnChange();
		else if(isChange)
			change();
		else if (isLastStep)
			lastStep();
		else if (isSecondStep)
			secondStep();
		else
			insertionSortFirst();
	}

	void insertionSortFirst() {
		numbers.get(swap2).setChosen(false);
		numbers.get(swap1).setChosen(false);

		if (i < sizeNumbers) {
			swap1 = i;
			temp = numbers.get(i);
			numbers.get(i).setChosen(true);
			isOffStep = true;
			j = i;
			isSecondStep = true;
			i++;
		}   else    {
			i = 1;
			setEnd();
		}
	}

	private void secondStep() {
		numbers.get(swap2).setChosen(false);

		if (j > 0 && numbers.get(j - 1).num >
				temp.num) {
			numbers.get(j - 1).setChosen(true);
			swap2 = j;
			isChange = true;
			j--;
		}   else    {
			isSecondStep = false;
			swap1 = j;
			isReturnChange = true;
		}
	}

	private void lastStep() {
	}

	private void change() {
		numbers.set(swap2, numbers.get(swap2 - 1));

		isChose = true;
		isChange = false;
	}

	private void returnChange() {
		numbers.set(swap1, temp);

		isChose = true;
		isReturnChange = false;
	}

	private void offStep() {
		isOffStep = false;
	}

	private void chose() {
		score++;
		isChose = false;
	}

	public void setEnd() {
		boolean answer = true;
		for (NumberClass num: numbers) {
			answer = answer && num.isWritePlace;
		}
		isEnd = answer;
	}
}
