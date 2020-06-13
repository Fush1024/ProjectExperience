package com.quickly.devploment.leetcode.dp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author lidengjin
 * @Date 2020/6/13 3:46 下午
 * @Version 1.0
 */
public class Triangle {

	/**
	 * 动态规划  三角形的最短路径
	 *
	 * @param triangle
	 * @return
	 */
	public static int minimumTotal(List<List<Integer>> triangle) {
		int[] dp = new int[triangle.get(triangle.size() - 1).size() + 1];
		for (int i = triangle.size() - 1; i >= 0; --i) {
			for (int j = 0; j < triangle.get(i).size(); ++j) {
				dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
			}
		}
		return dp[0];
	}

	@Test
	public void testTriangle() {
		List<Integer> integers1 = Arrays.asList(10);
		List<Integer> integers2 = Arrays.asList(3, 5);
		List<Integer> integers3 = Arrays.asList(5, 6, 7, 2, 8);
		List<Integer> integers4 = Arrays.asList(11, 2, 1, 6, 8, 4, 13, 16);
		List<List<Integer>> lists = Arrays
				.asList(integers1, integers2, integers3, integers4);
		int i = minimumTotal(lists);
		System.out.println(i);
	}
}
