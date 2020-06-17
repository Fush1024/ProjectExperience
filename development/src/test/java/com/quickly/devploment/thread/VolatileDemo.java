package com.quickly.devploment.thread;

import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/16 9:24 上午
 * @Version 1.0
 */
public class VolatileDemo {

	public static Integer sumConut = 0;

	public static void main(String[] args) throws InterruptedException {
		Sync sync = new VolatileDemo().new Sync();
		sync.start();
		Thread.sleep(1000);
		sync.setFlag(true);
	}

	class Sync extends Thread {


		// MESI 协议 Modify exclusive Shard invalid
		// 涉及到 JMM 内存模型 ，缓存一致性协议
		public boolean flag = false;

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
			long sum = 0;
			System.out.println("start " + System.currentTimeMillis());
			int count = 0;
			while (!flag) {
				// MESI 协议 Modify exclusive Shard invalid
				// 此处涉及到 io 操作 ，故案例不行 ，IO操作会 刷新总线 bus
				//System.out.println(123);
				//				System.out.println(111);

				// 此处未涉及到 io 操作，只是 cpu 密集型，故案例行得通 ，volatile 案例
				//				System.out.println("sum " + sum);
				//				sum++;

				// 和 Java 编译器有关，如果编译的时候是永真的，如果是静态变量，是要去刷新主存的,如果不是永真，那么不去主存。
				if (sum++ == 1000000l) {
//					sumConut ++ ;
//					sum++;
//					sum++;
					System.out.println();
//					sumConut ++ ;
					while (!flag) {
						System.out.println("sum ---------------" + sum + "count " + count);
						count++;
						////					}
						////					sum = 0;
					}

				}
				//			System.out.println(sum);
			}
			System.out.println("end " + System.currentTimeMillis());
		}

		@Test
		public void testValue() {
			Integer i1 = new Integer(1000000);
			Integer i2 = i1;
			i1 = new Integer(2000000);
			System.out.println("i1 " + i1 + "-- i2 " + i2);
		}
	}
}
