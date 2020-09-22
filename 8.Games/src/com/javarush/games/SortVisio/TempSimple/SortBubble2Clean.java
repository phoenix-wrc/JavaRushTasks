package com.javarush.games.SortVisio.TempSimple;

import com.javarush.engine.cell.Game;
import com.javarush.games.SortVisio.NumberClass;

import java.util.List;

public class SortBubble2Clean {
	private List<NumberClass> numbers;
	int i;
	int j;
	int sizeNumbers;

	public SortBubble2Clean(List<NumberClass> numbers) {
		this.numbers = numbers;
		sizeNumbers = numbers.size();
		i = 1;
		j = sizeNumbers - 1;
	}

	public void step() throws InterruptedException {
		for(i = 1; i < sizeNumbers; i++)	{
			System.out.println(i);
			for (j = sizeNumbers - 1; j >= i; j--) {
				System.out.print(i + " - ");
				System.out.print(j + ", ");
				if (numbers.get(j - 1).num > numbers.get(j).num) {
					swap(j, j-1);
//					NumberClass temp = numbers.get(j - 1);
//					numbers.set(j - 1, numbers.get(j));
//					numbers.set(j, temp);
				}
			}
		}
		//stepOut();
	}

	public void stepOut() throws InterruptedException {
		//Thread.sleep(100);
		if (i < sizeNumbers) {
			stepInternal();
			i++;
		} else i = 1;
	}

	private void stepInternal() throws InterruptedException {
		if (j >= i) {
			if (numbers.get(j - 1).num > numbers.get(j).num) {
				swap(numbers.get(j).x, numbers.get(j - 1).x);
			}
			j--;
		} else j = sizeNumbers - 1;
	}

	private void swap(int j1, int j2) throws InterruptedException {
		NumberClass temp = numbers.get(j2);
		numbers.set(j2, numbers.get(j1));
		numbers.set(j1, temp);
		Thread.sleep(50);
		for (int i = 0; i < sizeNumbers; i++) {
			numbers.get(i).x = i;
		}
	}

	public void draw(Game game) {
		//numbers.forEach(num -> num.draw(game));
	}
}
