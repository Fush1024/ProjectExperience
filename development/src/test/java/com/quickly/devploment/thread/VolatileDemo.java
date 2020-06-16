package com.quickly.devploment.thread;

import lombok.SneakyThrows;

/**
 * @Author lidengjin
 * @Date 2020/6/16 9:24 上午
 * @Version 1.0
 */
public class VolatileDemo {
	public static void main(String[] args) throws InterruptedException {
		Sync sync = new VolatileDemo().new Sync();
		sync.start();
		Thread.sleep(1000);
		sync.setFlag(true);
	}

	class Sync extends Thread {

		// MESI 协议 Modify exclusive Shard invalid
		// 涉及到 JMM 内存模型 ，缓存一致性协议
		public volatile boolean flag = false;

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		@SneakyThrows
		@Override
		public void run() {
			System.out.println("start ");
			System.out.println("-----" + flag);
			int sum = 0;
			while (!flag) {
				// MESI 协议 Modify exclusive Shard invalid
				// 此处涉及到 io 操作 ，故案例不行
				//System.out.println(123);

				// 此处未涉及到 io 操作，只是 cpu 密集型，故案例行得通 ，volatile 案例
				if(++sum /100 == 0 ){
					sum = 0;
				}

			}

			System.out.println(sum);
		}
	}
}
