package com.quickly.devploment.current.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierExample1 {

	// 当 5 个数 满了之后 就会执行， 如果不是 5 的整数 ，那么将会无限期的等待，导致线程的堵塞。
	private static CyclicBarrier barrier = new CyclicBarrier(5);

	public static void main(String[] args) throws Exception {

		// 注意产线不能用这个， coreSize maxPoolSize BlockingQueue
		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 11; i++) {
			final int threadNum = i;
			Thread.sleep(1000);
			executor.execute(() -> {
				try {
					race(threadNum);
				} catch (Exception e) {
					log.error("exception", e);
				}
			});
		}
		executor.shutdown();
	}

	private static void race(int threadNum) throws Exception {
		Thread.sleep(1000);
		log.info("{} is ready", threadNum);
		barrier.await();
		log.info("{} continue", threadNum);
	}
}
