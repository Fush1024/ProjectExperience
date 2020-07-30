package com.quickly.devploment.face.one;

import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/7/29 6:05 下午
 * @Version 1.0
 */
public class Calculate {

	private static double EPSILON = 0.0000000001;

	// 已知 sqrt (2)约等于 1.414，要求不用数学库，求 sqrt (2)精确到小数点后 10 位。
	private double sqrt2() {
		double low = 1.4, height = 1.5;
		double mid = (low + height) / 2;
		while (height - low > EPSILON) {
			if (mid * mid > 2) {
				height = mid;
			} else {
				low = mid;
			}
			mid = (height + low) / 2;

		}
		return mid;
	}

	@Test
	public void testSqrt() {
		double v = new Calculate().sqrt2();
		System.out.println(v);
	}


}
