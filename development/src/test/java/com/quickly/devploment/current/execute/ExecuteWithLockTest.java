package com.quickly.devploment.current.execute;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author lidengjin
 * @Date 2020/7/6 2:43 下午
 * @Version 1.0
 */
public class ExecuteWithLockTest {

	private static Map<Integer, Integer> map = new HashMap<>();
	private static Map<Integer, Integer> concurrentMap = new ConcurrentHashMap<>();

	private static AtomicInteger i = new AtomicInteger(0);

	@Test
	public void testExecute() {
		ExecuteWithLock executeWithLock = new ExecuteWithLock(100, 1000000);
		executeWithLock.execute(10, () -> {
			return update();
		});
		System.out.print(concurrentMap.size());
	}

	private Map<Integer, Integer> update() {
		concurrentMap.put(i.incrementAndGet(), i.get());
		return null;
	}
}
