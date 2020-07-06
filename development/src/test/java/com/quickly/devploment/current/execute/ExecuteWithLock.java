package com.quickly.devploment.current.execute;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author lidengjin
 * @Date 2020/7/6 1:52 下午
 * @Version 1.0
 */
@Slf4j
public class ExecuteWithLock {

	private ExecutorService executorService;

	private Semaphore semaphore;

	private CountDownLatch countDownLatch;

	private long clientTotalSize;

	// 重试次数
	private static final int retryCount = 3;

	public ExecuteWithLock(int semaphoreNum, int clientTotalSize) {
		this.executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
				Runtime.getRuntime().availableProcessors() * 4, 300, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));
		this.semaphore = new Semaphore(semaphoreNum);
		this.countDownLatch = new CountDownLatch(clientTotalSize);
		this.clientTotalSize = clientTotalSize;
	}

	public ExecuteWithLock() {
	}


	public <T> T executeWithinLock(Integer acquireNum, WithinLockExecutor<T> executor) {
		for (int i = 0; i < retryCount; i++) {
			try {
				tryLock(acquireNum);
				return executor.execute();
			} catch (Exception e) {
				log.info("exception is {0}", e);
			} finally {
				unLock(acquireNum);
			}
		}
		return null;
	}

	public <T> T execute(Integer acquireNum, WithinLockExecutor<T> executor) {
		for (int i = 0; i < this.clientTotalSize; i++) {
			this.executorService.execute(() -> {
				executeWithinLock(acquireNum, executor);
				countDownLatch.countDown();
			});
		}
		try {
			this.countDownLatch.await();
			this.executorService.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void unLock(Integer acquireNum) {
		if (acquireNum == null) {
			semaphore.release();
		} else {
			semaphore.release(acquireNum);
		}
	}

	private void tryLock(Integer acquireNum) {
		try {
			if (acquireNum != null) {
				semaphore.acquire(acquireNum);
			} else {
				semaphore.acquire();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public Semaphore getSemaphore() {
		return semaphore;
	}

	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	public void setCountDownLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	public long getClientTotalSize() {
		return clientTotalSize;
	}

	public void setClientTotalSize(long clientTotalSize) {
		this.clientTotalSize = clientTotalSize;
	}

}
