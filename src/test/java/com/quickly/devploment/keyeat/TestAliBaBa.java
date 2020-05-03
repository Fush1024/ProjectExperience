package com.quickly.devploment.keyeat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName TestAliBaBa
 * @Description
 * @Author LiDengJin
 * @Date 2019/11/23 11:21
 * @Version V-1.0
 **/
public class TestAliBaBa {
	public static final String YES = "1";

	class TestB {
		public static final String YES = "1";
	}

	public static void main(String[] args) {
		//		// TODO
		//		// FIXME 哈哈
		//		System.out.println("11");
		//		System.out.println(TestAliBaBa.YES.equals(TestB.YES));

		try {
			testLatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	static class InnerAliBa implements Runnable {
		private CountDownLatch countDownLatch;

		public InnerAliBa(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(1000); System.out.println("等了 1s");
			} catch (Exception e) {

			} finally {
				if (countDownLatch != null) {
					countDownLatch.countDown();
				}
			}
		}
	}

	static class InnerQueue implements Runnable {

		private CountDownLatch countDownLatch;

		public InnerQueue(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(3000);
				System.out.println("等了 3s");
			} catch (Exception e) {
			} finally {
				if (countDownLatch != null) {
					countDownLatch.countDown();
				}
			}

		}
	}


	private static void testLatch() throws Exception{
		long now = System.currentTimeMillis();
		System.out.println(now);
		CountDownLatch countDownLatch = new CountDownLatch(2);
		Executor executor = Executors.newFixedThreadPool(2);
		executor.execute(new InnerAliBa(countDownLatch));
		executor.execute(new InnerQueue(countDownLatch));
		countDownLatch.await();
		((ExecutorService) executor).shutdown();
		System.out.println("over，回家 cost:"+(System.currentTimeMillis()-now));
	}
}
