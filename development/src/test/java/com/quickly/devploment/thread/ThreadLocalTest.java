package com.quickly.devploment.thread;

import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/5/13 10:19 上午
 * @Version 1.0
 */
public class ThreadLocalTest {
	@Test
	public void testThreadLocalSet(){
		ThreadLocal<Integer> threadLocalInteger = new ThreadLocal<>();
		threadLocalInteger.set(10);
		ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
		stringThreadLocal.set("the Object is  StringThreadLocal");
	}

	private static int q = 10;

	@Test
	public void test(){
		q1();

	}

	private void q1() {
		q = 20;
		System.out.println(q);
		q2();
	}

	private void q2() {
		q = 30;
		System.out.println(q);
	}
}
