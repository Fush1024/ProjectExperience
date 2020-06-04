package com.quickly.devploment.threadpool;

/**
 * @Author lidengjin
 * @Date 2020/6/4 10:17 上午
 * @Version 1.0
 */

import java.util.List;

/**
 * 面向接口编程
 */
public interface ThreadPool {
	// 执行一个Runnable类型的任务
	void execute(Runnable task);

	void execute(Runnable[] tasks);

	void execute(List<Runnable> tasks);

	// 返回以执行任务的个数
	int getExecuteTaskNumber();

	// 返回任务队列的长度，即还没处理的任务个数
	int getWaitTaskNumber();

	// 返回工作线程的个数
	int getWorkThreadNumber();

	// 关闭线程池
	void destroy();
}
