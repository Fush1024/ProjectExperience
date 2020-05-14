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
}
