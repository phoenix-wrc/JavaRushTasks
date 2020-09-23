package com.javarush.games.SortVisio;

import java.util.List;

public class SortBubble3 extends Sort{
	int offIndex;
	boolean isSecondStep;

	public SortBubble3(List<NumberClass> numbers) {
		super(numbers);
		i = 1;
		j = sizeNumbers - 1;
		isSecondStep = false;
	}

	public void step() {
		if(isEnd) {
			numbers.get(sizeNumbers - 1).setChosen(false);
			return;
		}
		else if(isChose)
			super.step();
		else if(isSwap)
			swap();
		else if(isPassStep)
			offStep();
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

	void offStep() {
		sounds.playChose();
		super.offStep();
	}
}
