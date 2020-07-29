package com.quickly.devploment.safe.collections;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author lidengjin
 * @Date 2020/7/29 11:39 上午
 * @Version 1.0
 */
public class SafeHashMap {
	public static void main(String[] args) {
		ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
	}
	/*

		1 ConcurrentHashMap 继承自 AbstractMap
				1 Node结点的定义非常简单，也是其它四种类型结点的父类，默认链接到table[i]——桶上的结点就是Node结点 ，当出现hash冲突时，Node结点会首先以链表的形式链接到table上，当结点数量超过一定数目时，链表会转化为红黑树。因为链表查找的平均时间复杂度为O(n)，而红黑树是一种平衡二叉树，其平均时间复杂度为O(logn)
				2 TreeNode TreeNode就是红黑树的结点，TreeNode不会直接链接到table[i]——桶上面，而是由TreeBin链接，TreeBin会指向红黑树的根结点
				3 TreeBin TreeBin相当于TreeNode的代理结点。TreeBin会直接链接到table[i]——桶上面，该结点提供了一系列红黑树相关的操作，以及加锁、解锁操作
				4 ForwardingNode 扩容节点。 相当于占位节点，表示table 正在进行扩容，当前线程可以协助数据迁移。
				5 ReservationNode 保留节点。
		2 ConcurrentHashMap 使用的是懒加载的方式，只有首次插入键值对的时候，才会去真正的初始化 数组。
		3 为什么 table 容量为2 的幂次方，因为 table.length-1 的二进制特点，全是1 ，配合这种索引计算方式 key 的hash & table.length -1 可以实现key 的均匀分布，减少hash 冲突。
		4 ConcurrentHashMap 计数 sum ，用的类似于 LongAddr 思想。
		5 最复杂的数据扩容，数据迁移。
			1 扩容思路
				1 table 数组的扩容
					一般是新建一个2倍大小的槽数组，这个过程一般由单线程完成，不允许出现并发。
				2 数据迁移
					把 旧的槽中的数据 重新分配到新的table 中，这一过程涉及到key 的 rehash , 因为key 的映射到桶的位置 与 table 大小有关。
			2 ConcurrentHashMap 在处理rehash 的时候并没有重新计算 key 的hash 值，而是利用了很巧妙的方法，concurrenthashmap table 是2 的幂次，让key 均匀分布，减少冲突。
				再一个原因就是 当table 数组的大小变为2 的幂次时，通过 key .hash & table.length -1 这种方式计算的索引 i ,当 table 扩容后，新的索引要不在原来的位置，要不是 i + n。
			3 数据扩容的时候，可能会多线程，共同扩容，这样的话，在调用 tranfer 的时候，如果后面的入参是 null,表示首次扩容，单线程，如果不是null,表示多线程，然后多线程的话，会通过计算得到一个
				步长，stride ,表示 每个线程处理的桶区间。表示数据迁移的时候，每个线程负责旧的table 中的多少桶。
			4 ForwardingNode，主要做占用位，多线程进行数据迁移时，其它线程看到这个桶中是ForwardingNode结点，就知道有线程已经在数据迁移了。另外，当最后一个线程完成迁移任务后，会遍历所有桶，看看是否都是ForwardingNode，如果是，那么说明整个扩容/数据迁移的过程就完成了。
				另外，可能在扩容过程中，出现扩容冲突的情况，比如多个线程领用了同一区段的桶，这时任何一个线程都不能进行数据迁移，相当于作废，最后的时候，还要处理作废而没有迁移的桶，把它们正确的迁移到新的table 中。


	*/
}
