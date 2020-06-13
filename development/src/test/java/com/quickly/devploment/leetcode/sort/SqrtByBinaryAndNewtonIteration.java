package com.quickly.devploment.leetcode.sort;

import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/13 10:07 上午
 * @Version 1.0
 * 牛顿迭代法 以及二分查找 实现 sqrt
 */
public class SqrtByBinaryAndNewtonIteration {

	/**
	 * 牛顿迭代法
	 * 公式 y = x * x
	 * Xn-1 = f(Xn)/f'(Xn)
	 *
	 * @param x
	 * @return
	 */
	public static int mySqrt(int x) {
		int r;
		if (x <= 1)
			return x;
		r = x;
		while (r > x / r)
			r = (r + x / r) / 2;
		return (r);
	}


	/**
	 * 二分
	 *
	 * @param x
	 * @return
	 */

	public int mySqrtWithMid(int x) {
		// 注意：针对特殊测试用例，例如 2147395599
		// 要把搜索的范围设置成长整型
		// 为了照顾到 0 把左边界设置为 0
		long left = 0;
		// # 为了照顾到 1 把右边界设置为 x // 2 + 1
		long right = x / 2 + 1;  //对于一个非负数n，它的平方根不会大于（n/2+1）
		while (left < right) {              //在left与right中寻找
			// 注意：这里一定取右中位数，如果取左中位数，代码会进入死循环
			// long mid = left + (right - left + 1) / 2;
			long mid = (left + right + 1) >>> 1;    //左移一位与直接除二相比有巨大的时间差
			long square = mid * mid;
			if (square > x) {
				right = mid - 1;     //如果大于了则上界减一
			} else {
				left = mid;          //如果小于了则下界补上来
			}
		}
		// 因为一定存在，因此无需后处理
		return (int) left;
	}

	@Test
	public void testBinary(){
		int x = 640009;
		int i = mySqrt(x);
		System.out.println(i);
		System.out.println(mySqrtWithMid(x));
	}


}
