package com.quickly.devploment.thread;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @Author lidengjin
 * @Date 2020/7/2 2:26 下午
 * @Version 1.0
 */
public class TheadJoin {


	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " 主线程 start");
		System.out.println("------------------------------------------------");

		// 定义一个线程
		Thread t = new Thread(() -> {
			// 打印 1 到 10 的数字
			IntStream.range(0, 10).forEach(i -> System.out.println(Thread.currentThread().getName() + ">>>>>>" + i));
			Optional.of(Thread.currentThread().getName() + " 打印完毕").ifPresent(System.out::println);

		}, "artisan-thread");
		// 启动线程
		t.start();
		// 在主线程中 ，开启了一个新的线程t ,调用 t.join方法，确保该线程执行结束后，才会继续执行主线程中剩下的逻辑
		t.join();

		Optional.of("------------------------------------------------").ifPresent(System.out::println);
		System.out.println(Thread.currentThread().getName() + " 主线程 over");
	}


}
