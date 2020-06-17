package com.quickly.devploment.leetcode.dp;

import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/16 3:06 下午
 * @Version 1.0
 */
public class StockK {

	@Test
	public void testKStock() {
		int[] arrays = {1, 4, 2, 7, 6, 3, 10};
		int k = 2;
		int i = maxProfit(k, arrays);
		System.out.println(i);
	}

	public int maxProfit(int k, int[] prices) {
		if (k >= prices.length) //如果大于长度要求的天数 那么我们直接比大小就行了
			return resovle(prices);
		int[] local = new int[k + 1];
		int[] global = new int[k + 1];
		for (int i = 0; i < prices.length - 1; i++) {
			int diff = prices[i + 1] - prices[i];
			for (int j = k; j > 0; j--) {
				local[j] = Math.max(global[j - 1] + (diff > 0 ? diff : 0), local[j] + diff);
				global[j] = Math.max(global[j], local[j]);

			}
		}
		return global[k];
	}

	private int resovle(int[] prices) {
		int res = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] - prices[i - 1] > 0)
				res += prices[i] - prices[i - 1];
		}
		return res;
	}
}
