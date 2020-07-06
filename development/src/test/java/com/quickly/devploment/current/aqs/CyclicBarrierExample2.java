package com.quickly.devploment.current.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CyclicBarrierExample2 {

	private static CyclicBarrier barrier = new CyclicBarrier(5);

	public static void main(String[] args) throws Exception {

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
		try {
			// 500 等待时间，超时抛异常， 如果不是 5 的整数倍，那么也会报超时异常，因为会等待，有时间限制
			barrier.await(5000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			log.warn("BarrierException", e);
		}
		log.info("{} continue", threadNum);
	}
}
