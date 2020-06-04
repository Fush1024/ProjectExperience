package com.quickly.devploment.threadpool;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lidengjin
 * @Date 2020/6/4 10:20 上午
 * @Version 1.0
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		ThreadPool tPool = ThreadPoolManager.getThreadPool(6);
		List<Runnable> taskList = new ArrayList<Runnable>();
		for (int i = 0; i < 100; i++) {
			taskList.add(new Task());
		}
		tPool.execute(taskList);
		System.out.println(tPool);
		tPool.destroy();// 所有线程都执行完成才destory
		System.out.println(tPool);

	}

	// 任务类
	static class Task implements Runnable {
		private static volatile int i = 1;

		@Override
		public void run() {// 执行任务
			System.out.println("当前处理的线程是： " + Thread.currentThread().getName() + " 执行任务 " + (i++) + " 完成");
		}
	}
}
