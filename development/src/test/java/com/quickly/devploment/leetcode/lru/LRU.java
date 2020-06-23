package com.quickly.devploment.leetcode.lru;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lidengjin
 * @Date 2020/6/23 9:44 上午
 * @Version 1.0
 */
//基于 hash （拉链法） + 双向链表，LRUcache
//若改为开放寻址，线性探测法能更好使用cpuCache
public class LRU {
	private class Node {
		Node p;         //访问序 priv
		Node n;         //访问序 next

		Node hn;        //hash 拉链 next
		Object key;
		Object val;

		public Node() {
		}

		public Node(Object key, Object val) {
			this.key = key;
			this.val = val;
		}

		@Override
		public String toString() {
			return "Node{" + "key=" + key + ", val=" + val + ", hn=" + (hn == null ? "n" : hn.key) + ", p=" + (
					p == null ? "n" : p.key) + ", n=" + (n == null ? "n" : n.key) + '}';
		}
	}

	Node head;
	Node tail;
	Node[] tables;
	int capa = 4;
	int tabSize;                                    //2的整数倍
	int count;
	int countLimit = 8;
	float loadFactor = 0.75f;                       //装载因子

	public LRU(int countLimit) {
		this.countLimit = countLimit;
		tabSize = capa;
		tables = new Node[tabSize];
		count = 0;
	}

	public LRU() {
		tabSize = capa;
		tables = new Node[tabSize];
		count = 0;
	}

	public int getTabSize() {
		return tabSize;
	}

	public int getCountLimit() {
		return countLimit;
	}

	public int getCount() {
		return count;
	}

	public void put(Object key, Object val) {
		int indexh = hash(key);
		Node newNode = null;

		resize();

		//插入hash表
		if (tables[indexh] == null) {
			newNode = new Node(key, val);
			tables[indexh] = newNode;
			count++;
		} else {
			Node sentry = new Node();
			sentry.hn = tables[indexh];
			while (sentry.hn != null) {             //hash相同，在同一个桶里,需要额外判断equals
				if (sentry.hn.key.equals(key)) {
					sentry.hn.val = val;            //相同的key，替换val
					newNode = sentry.hn;
					break;
				} else
					sentry = sentry.hn;             //key不相同继续找拉链的下一个
			}
			if (newNode == null) {                  //没有存在有相同key的节点,创建一个新的插入
				newNode = new Node(key, val);
				sentry.hn = newNode;                //拉链尾接上新节点
				count++;
			}
		}

		//修改访问序链表
		if (head == null)
			head = newNode;
		if (tail == null) {
			tail = newNode;
		} else {
			if (newNode.p != null)                  //已存在的中间节点,从链表中取出
				newNode.p.n = newNode.n;
			if (newNode.n != null)
				newNode.n.p = newNode.p;
			newNode.n = null;
			newNode.p = tail;                       //放到链表尾部
			tail.n = newNode;
			tail = tail.n;
		}

		if (count > countLimit) {
			System.out.println("count > countLimit , del :" + del(head.key));
		}
	}

	public Node get(Object key) {
		int indexh = hash(key);

		//从hash表中查找
		Node chainHead = tables[indexh];
		while (chainHead != null) {                 //hash相同，在同一个桶里,需要额外判断equals
			if (!chainHead.key.equals(key))         //key不相同继续找拉链的下一个
				chainHead = chainHead.hn;
			break;                                  //找到了
		}

		//处理访问序链表，将访问的节点放到最后
		if (chainHead != null && tail != chainHead) {
			if (chainHead.p != null)
				chainHead.p.n = chainHead.n;
			if (chainHead.n != null)
				chainHead.n.p = chainHead.p;
			if (head == chainHead) {
				head = head.n;
			}
			tail.n = chainHead;
			chainHead.p = tail;
			chainHead.n = null;
			tail = tail.n;
		}

		return chainHead;
	}

	public static class Pair {
		public Object key;
		public Object val;

		@Override
		public String toString() {
			return "{" + "key=" + key + ", val=" + val + '}';
		}
	}

	public List<Pair> getAll() {
		List<Pair> list = new ArrayList<>();
		for (Node cur = head; cur != null; cur = cur.n) {
			Pair p = new Pair();
			p.key = cur.key;
			p.val = cur.val;
			list.add(p);
		}
		return list;
	}

	public Node del(Object key) {
		int indexh = hash(key);
		Node chainHead = tables[indexh];
		Node delNode = null;
		Node delHnodeP = null;

		//从hash表中移除
		while (chainHead != null) {                 //hash相同，在同一个桶里,需要额外判断equals
			if (!chainHead.key.equals(key))         //key不相同继续找拉链的下一个
				chainHead = chainHead.hn;
			else {
				delNode = chainHead;                //找到目标节点

				if (delHnodeP != null) {            //中间节点
					delHnodeP.hn = delNode.hn;
				} else {                            //tables 头节点
					tables[indexh] = delNode.hn;
				}
				break;
			}
			delHnodeP = chainHead;
		}

		//从访问序链表中移除
		if (delNode != null) {
			if (delNode.p != null)                   //从链表中取出
				delNode.p.n = delNode.n;
			if (delNode.n != null) {
				delNode.n.p = delNode.p;
			}
			if (tail == delNode)                     //链表头尾处理
				tail = delNode.p;
			if (head == delNode)
				head = delNode.n;
			count--;
		}

		return delNode;
	}

	public int hash(Object key) {
		int hashc = key.hashCode();
		hashc = hashc ^ (hashc >> 16);
		int indexh = hashc & (tabSize - 1);
		return indexh;
	}

	//扩容
	public void resize() {
		if (loadFactor * capa > count)
			return;

		System.out.println("resize " + capa + " to " + (capa << 1));
		List<Pair> list = getAll();
		capa = capa << 1;
		tabSize = capa;
		Node[] newTables = new Node[tabSize];

		head = null;
		tail = null;
		count = 0;
		tables = newTables;
		for (Pair p : list) {
			put(p.key, p.val);
		}
	}

	public static void main(String[] args) {
		//测试
		//add
		LRU lru = new LRU();
		lru.put(1, 1);
		lru.put(2, 2);
		lru.put(5, 5);
		lru.put(7, 7);
//
		System.out.println("count--"+lru.getCount());
		for (Pair p : lru.getAll()) {
			System.out.println("node---"+p);
		}
//		System.out.println();
//
//		//get
//		System.out.println("2 --"+lru.get(2));
//		System.out.println("count --"+lru.getCount());
//		for (Pair p : lru.getAll()) {
//			System.out.println(p);
//		}
//		System.out.println();
//
//		//del
//		System.out.println("del"+lru.del(5));
//		System.out.println("del-count"+lru.getCount());
//		for (Pair p : lru.getAll()) {
//			System.out.println(p);
//		}
//		System.out.println();
//
//		//same key
//		lru.put(7, 72);
//		for (Pair p : lru.getAll()) {
//			System.out.println(p);
//		}
//		System.out.println();
//
//		//hash collision
//		lru.put(7 + lru.getTabSize(), 73);
//		System.out.println(lru.getCount());
//		for (Pair p : lru.getAll()) {
//			System.out.println(p);
//		}
//		System.out.println();
//
//		//get bucket chain head
//		lru.get(7);
//		for (Pair p : lru.getAll()) {
//			System.out.println(p);
//		}
//		System.out.println();
//
//		//del
//		lru.del(23);
//		System.out.println(lru.getCount());
//		for (Pair p : lru.getAll()) {
//			System.out.println(p);
//		}
//		System.out.println();
//
//		lru.put(8, 8);
//		lru.put(9, 9);
//		lru.put(10, 10);
//		lru.put(11, 11);
//		lru.put(12, 12);
//		lru.put(13, 13); //del 1
//		lru.put(14, 14); //del 2
//		System.out.println(lru.getCount());
//		for (Pair p : lru.getAll()) { //7~14
//			System.out.println(p);
//		}
//		System.out.println();
	}
}
