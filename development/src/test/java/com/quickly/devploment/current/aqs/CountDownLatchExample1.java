package com.quickly.devploment.current.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CountDownLatchExample1 {

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
		countDownLatch.await();
		log.info("finish");
		exec.shutdown();
	}

	private static void test(int threadNum) throws Exception {
		Thread.sleep(100);
		log.info("{}", threadNum);
		Thread.sleep(100);
	}

	/*
	 1 juc  分类
	 	juc-locks  锁框架 {
	 		是 synchronized ，wait notify 的增强版
	 		ReentrantLock
	 		ReentrantReadWriteLock
	 		LockSupport
	 		AbstractQueuedSynchronizer AQS
	 		Condition
	 		StampedLock
	 	}
	 	juc-atomic 原子类框架 {
	 		Unsafe
	 		AtomicInteger
	 		AtomicReference
	 		Atomic 数组
	 		AtomicXXXFieldUpdater
	 		LongAdder 更强的原子类
	 	}
	 	juc-sync 同步器框架 {
	 		CountDownLatch ，内部实现 AQS
	 		CyclicBarrier 内部通过ReentrantLock 和 Condition
	 		Semaphore 内部通过 AQS
	 		Exchanger
	 		Phaser
	 	}
	 	juc-collections 集合框架 {
	 		ConcurrentHashMap
	 		ConcurrentSkipListMap
	 		CopyOnWriteArrayList
	 		CopyOnWriteArraySet
	 		ConcurrentLinkedQueue
	 		DelayQueue 延时堵塞队列
	 	}
	 	juc-executors 执行器框架 {
	 		线程池 ThreadPool
	 		计划线程池 ScheduledThreadPoolExecutor
	 		Future 模式
	 		ForkJoin 框架
	 	}
	 */
}
