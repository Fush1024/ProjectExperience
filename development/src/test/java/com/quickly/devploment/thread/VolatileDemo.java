package com.quickly.devploment.thread;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.HashMap;

/**
 * @Author lidengjin
 * @Date 2020/6/16 9:24 上午
 * @Version 1.0
 */
public class VolatileDemo {


	public static void main(String[] args) throws InterruptedException {
		Sync sync = new Sync();
		sync.start();
		Thread.sleep(1000);
		sync.setFlag(true);
	}

	static class Sync extends Thread {

		// MESI 协议 Modify exclusive Shard invalid
		// 涉及到 JMM 内存模型 ，缓存一致性协议
		public boolean flag = false;

		public static Integer sumConut = 0;

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
			double value = 0.0d;
			System.out.println("start " + System.currentTimeMillis());
			int count = 1000;
			float floatValue = 0f;
			byte byteValue = 0;
			while (!flag) {

//				testStaticMethod();
				// MESI 协议 Modify exclusive Shard invalid
				// 此处涉及到 io 操作 ，故案例不行 ，IO操作会 刷新总线 bus
				//System.out.println(123);
				//				System.out.println(111);

				// 此处未涉及到 io 操作，只是 cpu 密集型，故案例行得通 ，volatile 案例
				//				System.out.println("sum " + sum);
				//				sum++;

				// 和 Java 编译器有关，如果编译的时候是永真的，如果是静态变量，是要去刷新主存的,如果不是永真，那么不去主存。
				if (sum++ == 100l){
					//					sumConut ++ ;
					System.out.println(sum);

				}
//				count++;
				//				if (sum++ == 1000000l) {
				////					sum++;
				////					sum++;
				//					System.out.println();
				////					sumConut ++ ;
				//					while (!flag) {
				//						System.out.println("sum ---------------" + sum + "count " + count);
				//						count++;
				//						////					}
				//						////					sum = 0;
				//					}
				//
				//				}
				//			System.out.println(sum);
			}
			System.out.println("end " + System.currentTimeMillis());
		}

		 void testStaticMethod() {
			new HashMap<>();
		}

		@Test
		public void testValue() {
			Integer i1 = new Integer(1000000);
			Integer i2 = i1;
			i1 = new Integer(2000000);
			System.out.println("i1 " + i1 + "-- i2 " + i2);
		}
	}
	/*
		1 Java 内存模型
			java线程 工作内存 主内存
			内存间的交互
				1 lock 锁定 作用于主内存的变量， 把变量标识为一条线程独占的状态
				2 unlock 解锁 ，作用于主内存的变量，把处于锁定的状态的变量 释放出来。释放出来之后才能被其他的线程 锁定
				3 read 读取 作用于主内存的变量 把变量的值 从主内存 传输到线程的工作内存中，以便随后的load 动作使用
				4 load 载入 作用于工作内存的变量，把read 操作的变量值 放入到工作内存的变量副本中
				5 use 使用 作用于工作内存的变量，把工作内存中的变量值 传递给执行引擎，虚拟机每次使用变量的值的字节码指令时 执行这个操作
				6 assign 作用于工作内存 把从执行引擎接受的值 复制给工作变量 ，虚拟机每次需要给变量赋值的字节码指令时 执行这个操作
				7 store 存储 作用于工作内存的变量 把变量的值从工作内存 传送到 主内存 以便随后的write 使用
				8 write 写入 ，作用于主内存的变量 把 store 操作的从工作内存中得到的变量的值 写入到主内存中。
		2 Volatile 保证了可见性  但是不能保证原子性 ，在比如 race++ , volatile 只能保证 race 是最新的，但是自增的时候 不是原子性的 ，所以容易造成数据的并发安全，所以还需要加锁。
			禁止了指令重排序。
		3 缓存一致性 协议，MESI 协议。
	 */

	/*
	 1 java 线程调度 1 协同式线程调度 实现简单，等线程执行完毕 主动通知系统进行切换下一个线程，但是存在问题 就是线程的执行时间 不可控，所以很危险，严重可能导致系统崩溃。
	 		2 抢占式线程调度 由系统进行分配执行时间，线程的切换不由线程本身决定 ，由系统决定，所以不会出现一个线程 占用很长的时间问题。当然 不能够线程主动获取资源，需要系统分配。
	 2 java 线程调度 {
	 	1 new  只是创建了 线程，还未启动
	 	2 runnable 运行 ，此时也许正在运行，也许正在等待操作系统为它分配执行时间 就是所谓的时间片
	 	3 waiting 处于此时的状态的线程 不会被分配处理器执行时间，此时是无限期等待，等待被其他线程显示唤醒。 没有设置timeout 的object::wait（） 方法，或者没有设置时间的 Thread::join（） 方法，LockSupport::par（）方法，将会让线程进入无限期的等待中。
	 	4 time waiting 有限期的等待 ，不会被分配处理器执行时间 ，不过无须等待其他的线程显示唤醒，可以一定时间之后 由系统自动唤醒。Thread::sleep(), 设置时间的 Object：：wait(),Thread.Join(),LockSupport::parkNanos(),LockSupport：：parkUntil（）会进入有限期的等待
		5 block 堵塞，堵塞与等待的区别是，堵塞状态的在等待着获取到一个排他锁，这个事件将在另一个线程放弃这个锁的时候发生，而等待是等一段时间之后，在程序进入到同步等待区域时，程序将进入这种状态
		6 terminated 结束
	 }
	 */
}
