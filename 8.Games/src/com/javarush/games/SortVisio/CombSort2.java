package com.javarush.games.SortVisio;

import java.util.List;

public class CombSort2 {
	final double FACTOR = 1.247; // Фактор уменьшения
	private double step;
	int l;
	int tempIndex;
	int swap1, swap2, i, j;
	public int score;
	NumberClass temp;
	List<NumberClass> numbers;
	int sizeNumbers;
	int offIndex;
	boolean isEnd;
	boolean isChose;
	boolean isSwap;
	boolean isPassStep;
	boolean isInternalStepSecondSection;
	boolean isSecondSection;
	boolean isForLoopFirstSection;

	public CombSort2(List<NumberClass> numbers) {
		this.numbers = numbers;
		sizeNumbers = numbers.size();
		step = numbers.size() - 1;
		i = 1;
		j = sizeNumbers - 1;
		l = 0;
		score = 0;
		tempIndex = 0;
		offIndex = 0;

		isEnd = numbers.isEmpty();
		isSwap = false;
		isSecondSection = false;
		isInternalStepSecondSection = false;
		isChose = false;
		isPassStep = false;
		isForLoopFirstSection = false;
	}

	public void step() {
		if(isEnd)
			return;
		else if(isChose)
			chose();
		else if(isSwap)
			swap();
		else if(isPassStep)
			passStep();
		else if(isInternalStepSecondSection)
			internalStepSecondSection();
		else if(isSecondSection)
			outStepSecondSection();
		else if(isForLoopFirstSection)
			forLoopFirstSection();
		else
			whileLoopFirstSection();
	}
	
	private void whileLoopFirstSection() {
		if (step >= 1) {
			isPassStep = true;
			isForLoopFirstSection = true;
		} else {
			step = numbers.size() - 1;
			isSecondSection = true;
		}
	}

	void forLoopFirstSection() {
		if(l > 0)
			numbers.get(l - 1).setChosen(false);
		numbers.get(tempIndex).setChosen(false);

		if (l + step < numbers.size()) {
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
			isForLoopFirstSection = false;
			l = 0;
			step /= FACTOR;
		}
	}

	public void outStepSecondSection() {
		if (i < sizeNumbers) {
			isInternalStepSecondSection = true;
		} else {
			i = 1;
			j = sizeNumbers - 1;
			setEnd();
		}
	}

	void internalStepSecondSection() {
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
			isSecondSection = false;
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
		System.out.println("setEnd calls, isEnd - " + isEnd);
	}
}
