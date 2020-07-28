package com.quickly.devploment.current.lock.locksupport;

/**
 * @Author lidengjin
 * @Date 2020/7/28 3:46 下午
 * @Version 1.0
 */
public class LockSupport {
	public static void main(String[] args) throws InterruptedException {
		FIFOMutex mutex = new FIFOMutex();
		MyThread a1 = new MyThread("a1", mutex);
		MyThread a2 = new MyThread("a2", mutex);
		MyThread a3 = new MyThread("a3", mutex);
		MyThread a4 = new MyThread("a4", mutex);

		a1.start();
		a2.start();
		a3.start();
		a4.start();

		a1.join();
		a2.join();
		a3.join();
		a4.join();

		assert MyThread.count == 400;
		System.out.print("Finished");
	}
}

class MyThread extends Thread {
	private String name;
	private FIFOMutex mutex;
	public static int count;

	public MyThread(String name, FIFOMutex mutex) {
		this.name = name;
		this.mutex = mutex;
	}


	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			mutex.lock();
			count++;
			System.out.println("name:" + name + "  count:" + count);
			mutex.unlock();
		}
	}
}
