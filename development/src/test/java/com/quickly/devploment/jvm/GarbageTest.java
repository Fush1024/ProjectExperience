package com.quickly.devploment.jvm;

/**
 * @Author lidengjin
 * @Date 2020/7/1 5:37 下午
 * @Version 1.0
 */
public class GarbageTest {

	private static final int _1MB = 1024 * 1024;

	/**
	 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 */
	public static void testAllocation() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
	}

	public static void main(String[] args) {
		//		testAllocation();
		//		testPretenureSizeThreshold();
		testTenuringThreshold();
	}

	/**
	 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * -XX:PretenureSizeThreshold=3145728 大于这个内存的 直接放到老年代
	 */
	public static void testPretenureSizeThreshold() {
		byte[] allocation;
		allocation = new byte[4 * _1MB]; //直接分配在老年代中
	}

	/**
	 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:Survivor-
	 * Ratio=8 -XX:MaxTenuringThreshold=1
	 * -XX:+PrintTenuringDistribution
	 */
	@SuppressWarnings("unused")
	public static void testTenuringThreshold() {
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4]; // 什么时候进入老年代决定于XX:MaxTenuring-
		// Threshold设置
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
	}

	/*
		1 什么样的内存进行垃圾回收？
		2 什么时候回收？
		3 如何回收？

		1 如何判断Java对象 已死？
			1 引用计数器法  -- 循环引用 不可避免 ，如果是循环引用，那么就可能会导致内存泄漏，进而导致内存溢出。
			2 可达性分析法  已 GC root 为根结点，向下搜索，所走的过程称为引用链。
				-- 可以作为 GC root 的 ，栈中引用的对象，或者说 各个线程被调用的方法堆栈中使用到的 参数，局部变量，等。
					方法区中 类静态属性 引用的对象 方法区中常量引用的对象，同步锁持有的对象，或者内部调用的对象等等。
		2 引用 --强引用 软引用，弱引用 虚引用。
		3 finalize() F-queue ,不一定会等待其执行结束，担心里面执行缓慢，造成队列堵塞，使得其他的队列中的对象 称为永久等待过程中。对象在垃圾回收之前的最后一次拯救，只会运行一次。
		4 方法区中垃圾回收 效果不大，性价比低。回收的目标 ： 废弃的常量和不再使用的类。

		2 分代收集 理论，分代存放不同的对象。根据对象年龄 进行收集
			Minor GC, major jc（cms 只有单独对老年代的收集，）, full gc，mix gc 整个新生代以及部分老年代 （只有 g1）
			对象之间的跨代引用 -怎么解决？ 跨代引用假说， 在新生代 建立数据结构 记忆集，标记到 老年代划分小块儿，然后标记出某一个老年代内存 存在跨代引用，当发生 minor gc 时，把包含了 跨代引用的对象 加入到
			gcroot 进行扫描，然后进行可达性分析。
		3 标记-复制 标记-清除 标记-整理



	 */



}
