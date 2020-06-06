package com.quickly.devploment.leetcode.queue;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @Author lidengjin
 * @Date 2020/6/6 1:27 下午
 * @Version 1.0
 */
public class PriorityQueueTest {

	class KthLargest {
		final PriorityQueue<Integer> priorityQueue;
		final int k;

		KthLargest(int[] a, int k) {
			this.priorityQueue = new PriorityQueue<>();
			this.k = k;
			for (int i : a) {
				add(i);
			}
		}

		/**
		 * 使用最小 heap 以及 条件优先队列 实现第 K 个大的元素
		 *
		 * @param n
		 * @return
		 */
		public int add(int n) {
			if (this.priorityQueue.size() < k) {
				this.priorityQueue.offer(n);
			} else if (this.priorityQueue.peek() < n) {
				this.priorityQueue.poll();
				this.priorityQueue.offer(n);
			}
			return this.priorityQueue.peek();
		}
	}

	/**
	 * 两种实现 1 对数据 sort 实现，然后得到第k个元素 n*klogk
	 * 2 通过最小堆 实现 优先队列
	 */
	@Test
	public void testPriorityQueueAndKth() {
		int[] a = {1, 3, 4, 3, 6, 8, 7, 6};
		int k = 5;
		KthLargest kthLargest = new KthLargest(a, k);
		int add = kthLargest.add(10);
		System.out.println(add);

	}
}
