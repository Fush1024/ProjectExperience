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


	/*
	 2 juc-lock
	 	1 Lock 接口 ，是Synchronized 的增强版，提供了 限时锁等待，锁中断，锁尝试 等功能。
	 		lock （） 和 lockInterruptibly() 区别，lock （） 类似于synchronized 加锁，如果锁不可用，那么处于线程调度的目的，将禁用当前线程，并且在获得锁之前，该线程一直处于休眠状态。
	 		lockInterruptibly() 表示，如果锁不可用，那么当前正在等待锁的线程是可以被中断的，比synchronized 更灵活。
	 	2 Condition 可以被看作是 Object() 中的wait(),notify(),notifyAll() 的替代品，与Lock 配合使用，当线程执行condition 对象的wait 时，当前线程会释放锁，并进入对象的等待区，等待其他线程唤醒或者中断， 、
	 		JUC 实现condition对象的时候，其实是通过AQS 框架，实现了一个Condition 等待队列。
	 	3 ReentrantLock 是Lock 的实现 ， 重入锁的独占锁，内部类实现了AQS 框架，公平策略与非公平策略， 默认是非公平，公平的话，可能会性能不高，因为涉及到了线程的上下文切换，所以吞吐量不会很大，一般为了性能会选择非公平策略。
	 		所以 当线程持有锁的时间相对较长，或者线程请求锁的平均时间间隔较长的时候，可以使用公平锁，因为线程调度产生的耗时间隔影响较小。
	 	4 ReentrantReadWriteLock 读写锁。是ReadWriteLock 的实现。内部也是通过 AQS 实现的 具体独占锁的写锁，以及具有共享锁的读锁。支持可重入。 同一写线程获取了写锁之后 既可以获取写锁，也可以获取读锁。
	 		并且支持锁降级，获取写锁，然后获取读锁，最后释放写锁，这样 写锁就降级了读锁。写锁可以降级读锁，但是读锁不能升级写锁。
	 		Condition ，读锁调用newCondition 的时候 抛异常，因为调用condition的await 方法时，需要拿到和condition 对象关联的锁，由于线程对读锁的访问是不受限制的，所以拿读锁关联的condition对象是没有意义的。
	 	5 LockSupport ,用来创建锁和其他同步类的基本线程 堵塞原语。park 和 unpark(), park 堵塞当前线程，unpark 唤醒指定线程池。内部其实使用的是一种名为 Permit (许可) 的概念，来做到堵塞和唤醒的功能
	 		可以把许可堪称是一个 0，1 的信号量，但与信号量不同的是，permit 上线是 1 ，初始是0，permit 是0，调用了 unpark 时，permit +1 ，调用park 时，如果permit 为0 那么进入堵塞状态。
	 */
}
