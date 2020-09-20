package com.javarush.games.SortVisio;

import java.util.List;

public class QuickSort extends Sort {
	private boolean isQuickSortFirsStep;
	private int[] r;
	private int[] l;
	int[] q;
	int xPartition;
	int less;
	int lPartition;
	int rPartition;
	private boolean isPartitionFirst;
	private boolean isPartitionSecond;
	private boolean isQuickSortSecondStep;
	private boolean isQuickSortThreesStep;
	private boolean isStackAdd;
	private int i;

	QuickSort(List<NumberClass> numbers) {
		super(numbers);
		r = new int[sizeNumbers * 2];
		l = new int[sizeNumbers * 2];
		q = new int[sizeNumbers * 2];
		iPatition = 0;
		i = 0;
		j = 0;
		xPartition = 0;
		less = 0;
		isQuickSortFirsStep = false;
		isQuickSortSecondStep = false;
		isQuickSortThreesStep = false;
		isPartitionFirst = false;
		isPartitionSecond = false;
		isStackAdd = false;
	}

	@Override
	public void step() {
		if (isEnd || isPassStep || isChose || isSwap)
			super.step();
		else if(isPartitionSecond)
			partitionSecond();
		else if(isPartitionFirst)
			partitionFirst();
		else if(isStackAdd)
			stackAdd();
		else if(isQuickSortFirsStep)
			QuickSortImplFirstStep();
		else if(isQuickSortSecondStep)
			QuickSortImplSecondStep();
		else if(isQuickSortThreesStep)
			QuickSortImplThreesStep();
		else
			quickSort();
		setEnd();
	}

	void quickSort() {
		if (sizeNumbers > 0) {
			l[j] = 0;
			r[j] = sizeNumbers - 1;
			isQuickSortFirsStep = true;
		}
	}

	void stackAdd() {
		isQuickSortThreesStep = true;

		isQuickSortFirsStep = true;
		isStackAdd = false;
	}

	void QuickSortImplFirstStep() {
		if (l[j] < r[j]) {
			isPartitionFirst = true;
			lPartition = l[j];
			rPartition = r[j];
			j++;

			isQuickSortSecondStep = true;

			isQuickSortFirsStep = false;

			isStackAdd = true;
		}   else {
			isQuickSortFirsStep = false;
		}
	}

	void QuickSortImplSecondStep() {
		if (l[j] < r[j]) {
			r[j] = q[j - 1] - 1;
			l[j] = l[j - 1];
		} else {
			j = 0;
			isQuickSortSecondStep = false;
		}
	}


	void QuickSortImplThreesStep() {
		r[j] = r[j - 2];
		l[j] = q[j - 2] + 1;
		isQuickSortThreesStep = false;
	}

	void partitionFirst() {
		xPartition = numbers.get(rPartition).num;
		less = lPartition;
		iPatition = lPartition;
		if (iPatition < rPartition) {
			isPartitionSecond = true;
		}   else    {
		swap2 = less;
		swap1 = rPartition;
		isSwap = true;
		q[j] = less;
		}
	}
	void partitionSecond() {
		if (numbers.get(iPatition).num <= xPartition) {
			swap1 = iPatition;
			swap2 = less;
			isSwap = true;
			less++;
			isPartitionSecond = false;
		}
		iPatition++;
	}
}