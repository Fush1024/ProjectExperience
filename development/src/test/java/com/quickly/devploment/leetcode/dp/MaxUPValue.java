package com.quickly.devploment.leetcode.dp;

import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/15 9:36 上午
 * @Version 1.0
 * @Description 最大上升子序列的乘积
 */
public class MaxUPValue {
	/**
	 * @param nums: an array of integers
	 * @return: an integer
	 */
	public static int maxProduct(int[] nums) {
		// 当前最大值
		int posmax = nums[0];
		// 当前最小值
		int posmin = nums[0];
		int resmax = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int a = posmax * nums[i];
			int b = posmin * nums[i];
			posmax = Math.max(Math.max(a, b), nums[i]);
			posmin = Math.min(Math.min(a, b), nums[i]);
			resmax = Math.max(resmax, posmax);
		}
		return resmax;
	}

	@Test
	public void testMaxUPValue() {
		int[] arrays = {2, 4, -4, 5, 8};
		System.out.println(maxProduct(arrays));
	}
}
