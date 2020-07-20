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
			标记-清除 缺点： 1 效率不稳定，当有大量的对象 需要回收时，进行大量的标记和清除工作，导致执行效率下降，2 内存空间的碎片化 过多。导致，当有大对象需要分配的时候没有足够的连续的内存 进行分配，不得已而进行一次垃圾回收动作。
			标记-复制 空间浪费过多，内存使用率减少。 Serial、ParNew 新生代垃圾回收器 使用的这种思想 eden survivor
			标记-整理 标记出来对象后，让活动的对象向内存空间一端移动，然后清理掉边界以外的内存。
			如果是老年代，用标记-整理的话，需要移动对象，那么老年代每次回收都会有大量的对象存活区域，移动并且标记的话 会有极大的负重，所以 老年代也是区分场景了，关注吞吐量的 parallel scavenge 标记-整理 ，关注延迟的cms 是标记清除算法
			和稀泥的解决方案 ，平时使用标记清除，暂时容忍内存碎片的存在，直到影响对象的内存分配的时候，在进行标记整理 以获得规整的空间，cms --就是这样的。
		4 分配担保 当新生代gc 时，survivor 放不下时 可以去老年代 放点儿对象。
		5 用户线程以及垃圾收集线程， 存在安全点，当用户线程达到安全点儿的时候，就可以进行垃圾回收了。中断两种 1 抢先式中断，-- 垃圾回收进行处理，当有的用户线程没有达到安全点的时候，恢复用户线程进行执行，然后再进行中断。2 主动式中断
			安全区域，安全点有缺点，当用户线程 sleep 的时候，没办法响应，所以引入了安全区域概念。
		6 卡表，字节数组，记忆集的具体实现。卡表的每一个元素都对应着 内存区域的某一块，俗称卡页。一般是2的n 次幂。只有卡页中有个一对象存在跨代引用，那么就标志为1 ，俗称元素变脏。
			通过写屏障 维护卡表的状态。类似于 AOP 环绕通知。一般都用写后屏障。还可能会遇到伪屏障，通过标志位 去解决。
		7 可达性分析算法理论上要求全过程都基于一个能保障一致性的快照中才能够进行分析，这意味着必须全程冻结用户线程的运行，那么并发下的可达性分析？三色域。白色，未被垃圾收集器访问过，黑色，被访问过，都已经扫过了，灰色，被垃圾回收器扫过，但是还有未扫过的对象。

	 */



}
