package com.quickly.devploment.leetcode.dp;

import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/13 2:41 下午
 * @Version 1.0
 * 非波拉契算法
 */
public class NonPolac {

	/**
	 * 递推+缓存
	 *
	 * @param n
	 * @return
	 */
	public static int f(int n) {
		if (n == 1 | n == 2) {
			return 1;
		} else {
			int[] array = new int[n + 1];
			array[0] = 1;
			array[1] = 1;
			for (int i = 2; i < n + 1; i++) {
				array[i] = array[i - 1] + array[i - 2];
			}
			return array[n - 1];
		}
	}

	@Test
	public void testNonPolac() {
		int f = f(6);
		System.out.println(f);

	}

	/**
	 * 爬楼梯 DP
	 *
	 * @param n
	 * @return
	 */
	public static int climbStairs(int n) {

		int result = 0;

		// 只有一阶
		if (n == 1 || n == 2) {
			result = n;
		} else if (n > 2) {
			// 保存所有的解法
			int[] ways = new int[n];
			ways[0] = 1;
			ways[1] = 2;
			for (int i = 2; i < ways.length; i++) {
				ways[i] = ways[i - 1] + ways[i - 2];
			}
			result = ways[ways.length - 1];
		}
		return result;
	}

	@Test
	public void testClimbStairs(){
		System.out.println(climbStairs(6));
	}


}
