package com.quickly.devploment.leetcode.sum;

import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/8 10:16 上午
 * @Version 1.0
 */
public class BuyStockTest {

	@Test
	public void testBuyStock(){
		int[] a = {1,3,5,3,2,5,7,8,12,4,35,14};
		System.out.println(maxProfitTwo(a));
	}

	/**
	 * 动态规划
	 * 购买股票的最佳时机
	 *
	 * @param
	 * @return
	 */
	public static int maxProfitTwo(int nums[]) {
		int min = nums[0];
		int max = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < min)
				min = nums[i];
			else if (nums[i] - min > max)
				max = nums[i] - min;
		}
		return max;
	}
}
