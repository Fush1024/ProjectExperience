package com.quickly.devploment.leetcode.dp;

import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/22 9:19 上午
 * @Version 1.0
 * @Desccription 字符串编辑距离
 */
public class StringEditDistance {

	@Test
	public void testEditDistance() {
		int i = minEditDistance("cas", "sacaqaes");
		System.out.println(i);
	}

	/**
	 *  通过DP DP[i][j] 实现
	 *  见 blog https://www.cnblogs.com/boris1221/p/9375047.html
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int minEditDistance(String s1, String s2) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		//对dp数组初始化
		for (int i = 0; i < dp.length; i++)
			dp[i][0] = i;
		for (int j = 0; j < dp[0].length; j++)
			dp[0][j] = j;

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1];
				else
					dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
			}
		}
		return dp[dp.length - 1][dp[0].length - 1];
	}

	public static int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
}
