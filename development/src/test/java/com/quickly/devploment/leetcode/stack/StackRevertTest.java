package com.quickly.devploment.leetcode.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/5 3:19 下午
 * @Version 1.0
 */
@Slf4j
public class StackRevertTest {
	/**
	 * 测试是否是回文数 通过栈的思想
	 */
	@Test
	public void testRevertWithMiddle() {
		String name = "hahahaha";
		char[] c = name.toCharArray();
		int middle = name.length() / 2;
		char[] a = new char[middle];
		int top = 0;
		for (int i = 0; i < middle; i++) {
			a[top++] = c[i];
		}
		int next;
		if (c.length % 2 == 0) {
			next = middle;
		} else
			next = middle + 1;
		for (int i = next; i < c.length; i++) {
			if (c[i] != a[--top]) {
				break;
			}
		}
		if (top == 0) {
			System.out.println("yes");
		} else
			System.out.println("no");
	}
}


