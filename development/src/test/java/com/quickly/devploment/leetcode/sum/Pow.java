package com.quickly.devploment.leetcode.sum;

import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/7 5:24 下午
 * @Version 1.0
 */
public class Pow {

	@Test
	public void testPowXN() {
		int x = 2;
		int n = 4;
		double v = myPow(x, n);
		System.out.println(v);
		double v1 = myPow_1(x, n);
		System.out.println(v1);
	}

	private double fastPow(double x, long n) {
		if (n == 0) {
			return 1.0;
		}
		double half = fastPow(x, n / 2);
		if (n % 2 == 0) {
			return half * half;
		} else {
			return half * half * x;
		}
	}

	/**
	 * 方法1  通过递归实现
	 *
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}

		return fastPow(x, N);
	}

	/**
	 * 方法2 ： 通过快速迭代方法
	 *
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow_1(double x, int n) {
		long N = n;
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}
		double ans = 1;
		double current_product = x;
		for (long i = N; i > 0; i /= 2) {
			if ((i % 2) == 1) {
				ans = ans * current_product;
			}
			current_product = current_product * current_product;
		}
		return ans;
	}
}
