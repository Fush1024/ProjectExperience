package com.quickly.devploment.leetcode.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author lidengjin
 * @Date 2020/6/5 4:40 下午
 * @Version 1.0
 */
public class BinarySort {



	@Test
	public void testBinSearch() {
		int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		long l = System.nanoTime();
		System.out.println(binarySortRecursion(array, 5, 0, array.length - 1));
		System.out.println(System.nanoTime() - l);
		l = System.nanoTime();
		System.out.println(binarySort(array, 5));
		System.out.println(System.nanoTime() - l);
	}

	@Test
	public void testHashMapAndList() {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		int n = 1000000;
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			arrayList.add(i);
			hashMap.put(i,i);
			array[i] = i;
		}
		long l = System.nanoTime();
		System.out.println(arrayList.contains(66666));
		System.out.println(System.nanoTime() - l);

		l = System.nanoTime();
		System.out.println(hashMap.containsKey(66766));
		System.out.println(System.nanoTime() - l);

		l = System.nanoTime();
		System.out.println(binarySort(array, 68666));
		System.out.println(System.nanoTime() - l);
	}

	/**
	 * 循环实现二分查找
	 *
	 * @param array
	 * @param key
	 * @return
	 */
	public static int binarySort(int[] array, int key) {
		int low = 0;
		int high = array.length - 1;
		while (low <= high) {
			int mid = (low + high) >>> 1;
			if (key < array[mid]) {
				high = mid - 1;
			} else if (key > array[mid]) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	/**
	 * 递归实现二分查找
	 *
	 * @param array
	 * @param key
	 * @param low
	 * @param high
	 * @return
	 */
	public static int binarySortRecursion(int[] array, int key, int low, int high) {
		if (low <= high) {
			int mid = (low + high) >>> 1;
			if (key < array[mid]) {
				return binarySortRecursion(array, key, low, mid - 1);
			} else if (key > array[mid]) {
				return binarySortRecursion(array, key, mid + 1, high);
			} else {
				return mid;
			}
		}
		return -1;
	}


}
