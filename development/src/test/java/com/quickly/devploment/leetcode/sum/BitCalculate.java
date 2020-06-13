package com.quickly.devploment.leetcode.sum;

import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/13 2:20 下午
 * @Version 1.0
 */
public class BitCalculate {

	/**
	 * 统计1的位数出现次数
	 *
	 * @param n
	 * @return
	 */
	public static int hammingWeight1(int n) {
		int count = 0;
		while (n != 0) {
			count++;
			n = n & (n - 1);
		}
		return count;
	}

	@Test
	public void testBitCalculate(){
		System.out.println(hammingWeight1(103));
	}

}
