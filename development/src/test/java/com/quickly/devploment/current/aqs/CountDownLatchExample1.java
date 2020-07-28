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

	/*
	 3 juc-locks AQS
	 	1 jdk 1.5 的时候引入进来的。 juc 包。 AQS 提供了一套通用的机制 来管理同步状态，堵塞/唤醒线程，管理等待队列。
	 	2 AQS 分离了构建同步器时的一系列关注点，它所有的操作 都围绕着一个资源-- 同步状态 ，替用户解决了如下问题，{
	 		1 资源是可以被同时访问？ 还是同一时间只能被一个线程访问-- 共享 /独占
	 		2 访问资源的线程 如何进行并发的管理 ---等待队列
	 		3 如果线程等不及资源了，如何从等待队列退出？--- 超时/中断
	 		4 什么是资源 ，如何定义资源？ ---留给用户自己实现。 {
	 			ReentrantLock ,资源表示独占锁 ，State 为0 表示锁可用，为1 表示被占用，为n 表示重入的次数
	 			CountDownLatch ，资源表示 倒数计数器，State 为0 表示计数器归0，所有线程都可以访问资源，为n 表示计数器未归零，所有线程都需要堵塞。
	 			Semaphore 资源表示 信号量或者令牌，State <=0  表示没有令牌可用，所有线程需要堵塞。大于0 表示有令牌可用
	 		}
	 	}
	 	3 cas 操作 ，等待队列的核心操作 : enq ,入队 操作，addWaiter 入队操作。setHead 设置头节点，unparkSuccessor 唤醒后继节点。 都是private 的.
	 		资源的获取操作，acquireQueue 。。。acquire  ,acquireShared
	 	4 AQS 衍生出三个基本问题 ，1 同步状态管理 2 堵塞/唤醒 线程的操作 3 线程等待队列的管理。
	 		1 同步状态，其实就是资源，用 32 位来保存同步状态。
	 		2 堵塞。唤醒 locksupport
	 		3 等待队列 ，AQS 核心，是一个 FIFO ，采用双向链表，也叫做 CLH 队列。
	 			{
	 				1 CLH 队列中的节点，是对线程的包装。节点有两种类型，独占 ，共享， 每个类型的节点 有自己的状态，0，-1，-2，1 ，-3。。
	 				2 AQS 使用CLH 实现线程的结构管理，而CLH 结构正是用 前一节点的 某一属性 表示当前节点的状态。之所以这样做，是因为 在双向链表的结构下，更容易实现取消和超时功能。
	 				3 next 指针，维护队列的顺序,当临界区的资源被释放的时候，头节点 通过 next 指针找到队首节点。
	 				4 prev ，用于节点被取消的时候，当前节点 的 前驱直接指向 当前节点的后驱 完成出队操作。
	 			}
	 */
}
