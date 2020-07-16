package com.quickly.devploment.thread.exception;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author lidengjin
 * @Date 2020/7/14 3:25 下午
 * @Version 1.0
 */
public class ThreadRunnableException {

	@Test
	public void testRunnableException() {
		Runnable runnable = () -> {
			int i = 1 / 0;
		};
		new Thread(runnable).start();
	}

	@Test
	public void testCallableException() {
		Callable callable = () -> {
			int i = 1 / 0;
			return null;
		};
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		Future submit = executorService.submit(callable);
		try {
			submit.get();
		} catch (InterruptedException e) {
			System.out.println(" Interrupted Exception ");
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("ExecutionException ");
			e.printStackTrace();
		}

	}
}
