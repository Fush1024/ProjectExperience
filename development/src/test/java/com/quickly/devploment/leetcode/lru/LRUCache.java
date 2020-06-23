package com.quickly.devploment.leetcode.lru;

import java.util.HashMap;

/**
 * @Author lidengjin
 * @Date 2020/6/23 2:31 下午
 * @Version 1.0
 */

public class LRUCache {

	class Node {
		int key;
		int value;
		Node prev;
		Node next;
	}

	/**
	 * 向双向链表加入新节点
	 * 加入到表头
	 */
	private void addNode(Node node) {
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}

	/**
	 * 删除节点
	 */
	private void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	/**
	 * 删除表尾节点
	 */
	private Node popTail() {
		Node node = tail.prev;
		removeNode(node);
		return node;
	}

	/**
	 * 移动到表头
	 * 1.删除原节点
	 * 2.在表头插入原节点
	 */
	private void moveToHead(Node node) {
		removeNode(node);
		addNode(node);
	}

	private int capacity;
	private int size;
	private HashMap<Integer, Node> cache;
	private Node head;
	private Node tail;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		cache = new HashMap<>();
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * get()操作
	 * 如果key对应的value不存在，返回-1
	 * 如果存在，则获取值，并将节点移动到表头
	 */
	public int get(int key) {
		Node node = cache.get(key);
		if (node == null)
			return -1;
		moveToHead(node);
		return node.value;
	}

	/**
	 * put()操作
	 * 如果key不存在，则新建节点，将节点加入cache map，放置到表头，size+1
	 * 如果size>capacity，要去除表尾节点，并从cache中删除，size-1
	 * 如果key存在，将该节点的值更新，并移动到表头
	 */
	public void put(int key, int value) {
		Node node = cache.get(key);
		if (node == null) {
			node = new Node();
			node.key = key;
			node.value = value;
			cache.put(key, node);
			addNode(node);
			size++;
			if (size > capacity) {
				Node del = popTail();
				cache.remove(del.key);
				size--;
			}
		} else {
			node.value = value;
			moveToHead(node);
		}
	}

	public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(2);
		lruCache.put(1, 1);
		lruCache.put(2, 2);
		System.out.println(lruCache.get(1));
		lruCache.put(3, 3);
		System.out.println(lruCache.get(2));
		lruCache.put(4, 4);
		lruCache.get(1);
		lruCache.get(3);
		lruCache.get(4);
	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
