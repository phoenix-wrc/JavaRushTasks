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
			numbers.get(i).setChosen(false);
			swap1 = i;
			temp = numbers.get(i);
			j = i;
			isSecondStep = true;
			i++;
		}   else    {
			i = 1;
			setEnd();
		}
	}

//	void InsertionSort(vector<int>& values) {
//		for (size_t i = 1; i < values.size(); ++i) {
//			int x = values[i];
//			size_t j = i;
//			while (j > 0 && values[j - 1] > x) {
//				values[j] = values[j - 1];
//				--j;
//			}
//			values[j] = x;
//		}
//	}

	private void secondStep() {
		numbers.get(swap2).setChosen(false);
		numbers.get(swap1).setChosen(false);

		if (j > 0 && numbers.get(j - 1).num >
				temp.num) {
			numbers.get(j).setChosen(true);
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
		//numbers.get(swap2).setChosen(false);
		//numbers.get(swap1).setChosen(true);

		//temp = numbers.get(swap2);
		numbers.set(swap2, numbers.get(swap2 - 1));
		//numbers.set(swap1, temp);
		offIndex = swap2;

		isChose = true;
		isChange = false;
	}

	private void returnChange() {
		//numbers.get(swap2).setChosen(true);
		//numbers.get(swap1).setChosen(true);

		//temp = numbers.get(swap2);
		//numbers.set(swap2, numbers.get(swap1));
		numbers.set(swap1, temp);

		offIndex = swap1;

		isChose = true;
		isReturnChange = false;
	}

	private void offStep() {
		//numbers.get(offIndex).setChosen(false);
		isOffStep = false;
	}

	private void chose() {
		numbers.get(offIndex).setChosen(false);
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
