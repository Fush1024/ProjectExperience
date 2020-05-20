package com.quickly.devploment.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Author lidengjin
 * @Date 2020/5/18 2:18 下午
 * @Version 1.0
 */
public class Driver {

	private static final int N = 10;

	public static void main(String[] args) {

		CountDownLatch switcher = new CountDownLatch(1);

		for (int i = 0; i < N; ++i) {
			new Thread(new Worker(switcher)).start();
		}


		switcher.countDown();  // 主线程开启开关
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		doSomething();

	}

	public static void doSomething() {
		System.out.println("something is  start....");
	}
}

class Worker implements Runnable {
	private final CountDownLatch startSignal;

	Worker(CountDownLatch startSignal) {
		this.startSignal = startSignal;
	}

	public void run() {
		try {
			startSignal.await();    //所有执行线程在此处等待开关开启
			doWork();
		} catch (InterruptedException ex) {
		}
	}
	public void doWork() {
		System.out.println(" the worker is working ..");
	}
}
