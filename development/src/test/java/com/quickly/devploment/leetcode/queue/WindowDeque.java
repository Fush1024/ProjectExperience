package com.quickly.devploment.leetcode.queue;

/**
 * @Author lidengjin
 * @Date 2020/6/6 2:07 下午
 * @Version 1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 滑动窗口中的最大值
 */
@Slf4j
public class WindowDeque {

	@Test
	public void testMaxInWindows() {
		int[] windows = {1, 3, 2,1,1,1};
		int size = 3;
		// 3 5 6 7 8 9 13 13 13
		ArrayList<Integer> integers = maxInWindows(windows, size);
		log.info("max array {}", integers);
	}

	/**
	 * 滑动窗口的最大值
	 * 大顶堆 + 双端队列
	 *
	 * @param num
	 * @param size
	 * @return
	 */
	public ArrayList<Integer> maxInWindows(int[] num, int size) {
		ArrayList<Integer> res = new ArrayList<>();
		if (size < 1 || num.length == 0)
			return res;
		//定义一个双端队列
		// 3 5 6 7 8 9 13 13 13
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < num.length; i++) {
			//如果当前下标的值大于队列中最右边的值就循环出队
			while (!list.isEmpty() && num[list.peekLast()] < num[i]) {
				list.pollLast();
			}
			list.add(i);
//			//判断左边元素是否失效 1,3,3,1, 1, 1, 1 ---> 此时会有用。
			if (list.peekFirst() <= i - size) {
				list.pollFirst();
			}
			if (i >= size - i) {
				res.add(num[list.peekFirst()]);
			}
		}
		return res;
	}

}
