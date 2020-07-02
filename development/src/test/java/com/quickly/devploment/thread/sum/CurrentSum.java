package com.quickly.devploment.thread.sum;

import com.quickly.devploment.pojo.UserPojo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author lidengjin
 * @Date 2020/6/29 2:06 下午
 * @Version 1.0
 */
public class CurrentSum {

	public static final int N = 100;

	static CountDownLatch countDownLatch = new CountDownLatch(N);

	static AtomicInteger ai = new AtomicInteger(0);

	/**
	 * 使用线程池 以及 AtomicInteger 实现加一操作
	 *
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
//		ExecutorService exec = Executors.newFixedThreadPool(10);
//		for (int i = 0; i <= N; i++) {
//			final int s = i;
//			exec.execute(() -> {
//				System.out.println(Thread.currentThread().getName() + ":" + ai.getAndAdd(s));
//				countDownLatch.countDown();
//			});
//		}
//		countDownLatch.await();
//		System.out.println(ai.get());
//		Thread.sleep(1000000);
//		exec.shutdown();
		testAddJVM();
	}

	public   static void testAddJVM() {
		try {
			testAddJVM_1();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
			for (Map.Entry<Thread, StackTraceElement[]> threadEntry : allStackTraces.entrySet()) {
				Thread key = threadEntry.getKey();
				System.out.println("thead "+ key.getName());
				StackTraceElement[] value = threadEntry.getValue();
				for (int i = 0; i < value.length; i++) {
					System.out.println("element" + value[i]);
				}
			}
		}

	}

	private static void testAddJVM_1() throws InterruptedException {
		testAddJVM_2();
	}

	private static void testAddJVM_2() throws InterruptedException {

		List<UserPojo> userPojos = new ArrayList<>();
		int sum = 0;
		boolean flag = false;

		while (!flag){
			userPojos.add(new UserPojo());
			sum++;
			if(sum == 100000){
				flag = true;
				Thread.sleep(1000);
			}
		}
		throw new RuntimeException("hello");

	}


	@Test
	public void testForkJoin() {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		CountTask task = new CountTask(1, N);
		// 执行一个任务
		Future<Integer> result = forkJoinPool.submit(task);
		try {
			System.out.println(result.get());
		} catch (ExecutionException | InterruptedException e) {
			System.out.println(e);
		}
	}

	static class CountTask extends RecursiveTask<Integer> {
		// 阈值
		private static final int THRESHOLD = 20;

		private final int start;

		private final int end;

		public CountTask(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		protected Integer compute() {
			int sum = 0;
			// 如果任务足够小就计算任务
			boolean canCompute = (end - start) <= THRESHOLD;
			if (canCompute) {
				for (int i = start; i <= end; i++) {
					sum += i;
				}
			} else {
				// 如果任务大于阈值，就分裂成两个子任务计算
				int middle = (start + end) / 2;
				CountTask leftTask = new CountTask(start, middle);
				CountTask rightTask = new CountTask(middle + 1, end);
				// 执行子任务
				leftTask.fork();
				rightTask.fork();
				// 等待子任务执行完，并得到其结果
				int leftResult = leftTask.join();
				int rightResult = rightTask.join();
				// 合并子任务
				sum = leftResult + rightResult;
			}
			return sum;
		}

	}

}
