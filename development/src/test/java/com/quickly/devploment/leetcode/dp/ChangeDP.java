package com.quickly.devploment.leetcode.dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author lidengjin
 * @Date 2020/6/19 9:29 上午
 * @Version 1.0
 * @Description 零钱兑换问题 动态规划
 */
public class ChangeDP {


	@Test
	public void testChangeMoney() {
		int[] coins = {1, 2, 5, 10};
		int money = 31;
		System.out.println(change(money, coins));
	}

	public static int change(int amount, int[] coins) {
		if (coins == null || coins.length == 0 || amount <= 0)
			return 0;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, amount + 1);
		dp[0] = 0;
		// 先遍历每个币种 --
		for (int j = 0; j < coins.length; j++)
			// 遍历每个金额对应的币种 最少有几种组合方式
			for (int i = coins[j]; i <= amount; i++)
				dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
		return dp[amount] > amount ? -1 : dp[amount];
	}

}
