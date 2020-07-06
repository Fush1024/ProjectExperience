package com.quickly.devploment.current.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {

	private final static int threadCount = 200;

	public static void main(String[] args) throws Exception {

		ExecutorService exec = Executors.newCachedThreadPool();

		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			exec.execute(() -> {
				try {
					test(threadNum);
				} catch (Exception e) {
					log.error("exception", e);
				} finally {
					countDownLatch.countDown();
				}
			});
		}
		// 带有超时时间的，如果超时没有获取到，那么就返回 false, 就会往下执行 10< 100 ，会向下执行
		// countDownLatch.await() ，若子任务堵塞，那么主任务会无限期的堵塞等待
		countDownLatch.await(10, TimeUnit.MILLISECONDS);
		log.info("finish");
		exec.shutdown();
	}

	private static void test(int threadNum) throws Exception {
		Thread.sleep(100);
		log.info("{}", threadNum);

	}
}
