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
		if(isInternalStep)
			secondStep();
		else
			firstLoop();
		System.out.println(isSwap || isChose || isSecondStep || isEnd ||
				isPassStep || isBubbleSortOn);
	}

	private void firstLoop() {
		if (step >= 2) {
			isPassStep = true;
			isInternalStep = true;
		} else {
			step = numbers.size() - 1;
			//setEnd();
			isBubbleSortOn = true;
			System.out.println(isBubbleSortOn);
		}
	}

	void secondStep() {
		if (l + step < numbers.size()) {

			tempIndex = (int) (l + step);

			if (l > 0) numbers.get(l - 1).setChosen(false);
			numbers.get(l).setChosen(true);

			System.out.println(l);

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

			if (tempIndex < sizeNumbers)
				numbers.get(tempIndex).setChosen(false);
			numbers.get(l).setChosen(false);

			l = 0;
			step /= FACTOR;
			isInternalStep = false;
		}
	}
}
