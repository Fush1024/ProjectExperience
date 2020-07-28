package com.quickly.devploment.current.lock.locksupport;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author lidengjin
 * @Date 2020/7/28 3:45 下午
 * @Version 1.0
 */
public class FIFOMutex {
	private final AtomicBoolean locked = new AtomicBoolean(false);
	private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();

	public void lock() {
		Thread current = Thread.currentThread();
		waiters.add(current);

		// 如果当前线程不在队首，或锁已被占用，则当前线程阻塞
		// NOTE：这个判断的意图其实就是：锁必须由队首元素拿到
		while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
			LockSupport.park(this);
		}
		waiters.remove(); // 删除队首元素
	}

	public void unlock() {
		locked.set(false);
		LockSupport.unpark(waiters.peek());
	}
}
