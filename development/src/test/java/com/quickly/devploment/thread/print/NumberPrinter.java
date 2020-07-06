package com.quickly.devploment.thread.print;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author lidengjin
 * @Date 2020/7/6 4:13 下午
 * @Version 1.0
 * 数字打印，三个线程同时打印数字，第一个线程打印12345，第二个线程打印678910 .........
 */
public class NumberPrinter {

	//打印计数器
	private final AtomicInteger counter = new AtomicInteger(0);

	private class Printer implements Runnable {
		//总共需要打印TOTAL_PRINT_COUNT次
		private static final int TOTAL_PRINT_COUNT = 3;

		//每次打印PER_PRINT_COUNT次
		private static final int PER_PRINT_COUNT = 5;

		//打印锁
		private final ReentrantLock reentrantLock;
		//前一个线程的condition
		private final Condition afterCondition;
		//本线程的condition
		private final Condition thisCondition;

		public Printer(ReentrantLock reentrantLock, Condition thisCondition, Condition afterCondition) {
			super();
			this.reentrantLock = reentrantLock;
			this.afterCondition = afterCondition;
			this.thisCondition = thisCondition;
		}

		@Override
		public void run() {
			//进入临界区
			reentrantLock.lock();
			try {
				//循环打印TOTAL_PRINT_COUNT次
				for (int i = 0; i < TOTAL_PRINT_COUNT; i++) {
					//打印操作
					for (int j = 0; j < PER_PRINT_COUNT; j++) {
						//以原子方式将当前值加 1。
						//incrementAndGet返回的是新值（即加1后的值）
						System.out.println(counter.incrementAndGet() + "--i-->" +(i+1) + "===this" + thisCondition.hashCode());
					}
					//通过afterCondition通知后面线程
					afterCondition.signalAll();
					if (i < TOTAL_PRINT_COUNT - 1) {
						try {
							//本线程释放锁并等待唤醒
							thisCondition.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			} finally {
				reentrantLock.unlock();
			}
		}
	}

	public void test() throws InterruptedException {
		//打印锁
		ReentrantLock reentrantLock = new ReentrantLock();
		//打印A线程的Condition
		Condition conditionA = reentrantLock.newCondition();
		//打印B线程的Condition
		Condition conditionB = reentrantLock.newCondition();
		//打印C线程的Condition
		Condition conditionC = reentrantLock.newCondition();

		//打印线程A
		Thread threadA = new Thread(new Printer(reentrantLock, conditionA, conditionB));
		//打印线程B
		Thread threadB = new Thread(new Printer(reentrantLock, conditionB, conditionC));
		//打印线程C
		Thread threadC = new Thread(new Printer(reentrantLock, conditionC, conditionA));
		// 依次开启a b c线程
//		Thread.sleep(100);
		threadB.start();
		threadC.start();
//		Thread.sleep(10000);
		threadA.start();

	}

	public static void main(String[] args) throws InterruptedException {
		NumberPrinter print = new NumberPrinter();
		print.test();
	}
}
