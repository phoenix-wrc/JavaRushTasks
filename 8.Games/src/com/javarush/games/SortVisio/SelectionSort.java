package com.javarush.games.SortVisio;

import java.util.List;

public class SelectionSort extends Sort{
	int left;
	int minInd;
	private boolean isInternalStep;

	SelectionSort(List<NumberClass> numbers) {
		super(numbers);
		left = 0;
		minInd = 0;
		i = 0;
		isInternalStep = false;
	}

	@Override
	public void step() {
		if(isChose || isEnd || isSwap)
			super.step();
		else if(isPassStep)
			offStep();
		else if(isInternalStep)
			internalStep();
		else
			selectionSort();
	}

	private void selectionSort() {
		if (left < sizeNumbers) {
			numbers.get(minInd).setChosen(false);
			minInd = left;
			i = left;
			isInternalStep = true;
		}   else {
			numbers.get(minInd).setChosen(false);
			setEnd();
		}
	}
	void internalStep() {
		if(i < sizeNumbers) {
			if(i > 0)
				numbers.get(i - 1).setChosen(false);

			numbers.get(i).setChosen(true);
			numbers.get(minInd).setChosen(true);

			if (numbers.get(i).num < numbers.get(minInd).num){
				isPassStep = true;
			}
			i++;
		}   else    {
			numbers.get(sizeNumbers - 1).setChosen(false);
			numbers.get(left).setChosen(true);
			swap1 = left;
			swap2 = minInd;
			isSwap = true;
			left++;
			isInternalStep = false;
		}
	}

	@Override
	void offStep() {
		numbers.get(minInd).setChosen(false);
		minInd = i - 1;
		super.offStep();
	}
}
