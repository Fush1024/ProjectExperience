package com.quickly.devploment.thread.print;

/**
 * @author lixiaoxi
 * @Author lidengjin
 * @Date 2020/7/6 4:10 下午
 * @Version 1.0
 * 基于一个锁和一个状态变量实现连续打印abcabc...
 * <p>
 * 多线程 轮询打印ABC
 */
public class StateLockPrinter {
	//状态变量
	private volatile int state = 0;

	// 打印线程
	private class Printer implements Runnable {
		//打印次数
		private static final int PRINT_COUNT = 10;

		//打印锁
		private final Object printLock;

		//打印标志位 和state变量相关
		private final int printFlag;

		//后继线程的线程的打印标志位，state变量相关
		private final int nextPrintFlag;

		//该线程的打印字符
		private final char printChar;

		public Printer(Object printLock, int printFlag, int nextPrintFlag, char printChar) {
			super();
			this.printLock = printLock;
			this.printFlag = printFlag;
			this.nextPrintFlag = nextPrintFlag;
			this.printChar = printChar;
		}

		@Override
		public void run() {
			//获取打印锁 进入临界区
			synchronized (printLock) {
				//连续打印PRINT_COUNT次
				for (int i = 0; i < PRINT_COUNT; i++) {
					//循环检验标志位 每次都阻塞然后等待唤醒
					while (state != printFlag) {
						try {
							printLock.wait();
						} catch (InterruptedException e) {
							return;
						}
					}
					//打印字符
					System.out.print(printChar);
					//设置状态变量为下一个线程的标志位
					state = nextPrintFlag;
					//注意要notifyall，不然会死锁，因为notify只通知一个，
					//但是同时等待的是两个,如果唤醒的不是正确那个就会没人唤醒，死锁了
					printLock.notifyAll();
				}
			}
		}

	}

	public void test() throws InterruptedException {
		//锁
		Object lock = new Object();
		//打印A的线程
		Thread threadA = new Thread(new Printer(lock, 0, 1, 'A'));
		//打印B的线程
		Thread threadB = new Thread(new Printer(lock, 1, 2, 'B'));
		//打印C的线程
		Thread threadC = new Thread(new Printer(lock, 2, 0, 'C'));
		//一次启动A B C线程
		threadA.start();
		//		Thread.sleep(100);
		threadB.start();
		//		Thread.sleep(100);
		threadC.start();
	}

	public static void main(String[] args) throws InterruptedException {
		StateLockPrinter print = new StateLockPrinter();
		print.test();
	}

}
