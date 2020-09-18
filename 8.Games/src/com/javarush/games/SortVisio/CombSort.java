package com.javarush.games.SortVisio;

import java.util.List;

public class CombSort extends SortBubble3{
	final double FACTOR = 1.247; // Фактор уменьшения
	private double step;
	int l;
	int tempIndex;
	boolean isInternalStep;
	boolean isBubbleSortOn;

	public CombSort(List<NumberClass> numbers) {
		super(numbers);
		l = 0;
		isEnd = numbers.isEmpty();
		isInternalStep = false;
		isBubbleSortOn = false;
		step = numbers.size() - 1;
		tempIndex = 0;
		offIndex = 0;
	}

	public void step() {
		if(isSwap || isChose || isSecondStep || isEnd ||
				isPassStep || isBubbleSortOn)
			super.step();
		else if(isInternalStep)
			secondStep();
		else
			firstLoop();
	}

	private void firstLoop() {
		if (step >= 1) {
			isPassStep = true;
			isInternalStep = true;
		} else {
			step = numbers.size() - 1;
			isBubbleSortOn = true;
			setEnd();
		}
	}

	void secondStep() {
		if(l > 0)
			numbers.get(l - 1).setChosen(false);
		numbers.get(tempIndex).setChosen(false);

		if (l + step < sizeNumbers) {
			tempIndex = (int) (l + step);

			numbers.get(l).setChosen(true);
			numbers.get(tempIndex).setChosen(true);

			if (numbers.get(l).num >
					numbers.get(tempIndex).num) {

				swap2 = l;
				swap1 = tempIndex;
				isSwap = true;

			} else {
				isPassStep = true;
				offIndex = tempIndex;
			}
			l++;
		}   else    {
			l = 0;
			step /= FACTOR;
			isInternalStep = false;
		}
	}
}
