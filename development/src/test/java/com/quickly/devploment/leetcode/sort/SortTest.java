package com.quickly.devploment.leetcode.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/5 2:52 下午
 * @Version 1.0
 */
@Slf4j
public class SortTest {
	private int[] data = {10, 4, 2, 5, 7, 13, 56, 3, 5};

	/**
	 * 快排
	 */
	@Test
	public void testQuickSort() {
		quick(0, data.length - 1);
		for (int a = 0; a < data.length; a++) {
			System.out.print(data[a] + " ");
		}

	}

	public void quick(int left, int right) {
		int i, j;
		int Pivot;
		int temp;
		i = left;
		j = right;
		Pivot = data[(left + right) / 2];
		while (i < j) {
			while (data[i] < Pivot)
				i++;
			while (data[j] > Pivot)
				j--;
			if (i <= j) {
				temp = data[i];
				data[i] = data[j];
				data[j] = temp;
				i++;
				j--;
			}
		}
		if (left < j)
			quick(left, j);
		if (i < right)
			quick(i, right);
	}

}
