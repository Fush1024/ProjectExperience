package com.quickly.devploment.graph;

/**
 * 图的广度优先搜索---队列类
 *
 * @author Administrator
 */
public class Queue {
	private final int SIZE = 20;
	private int queueArray[];
	private int front;
	private int rear;

	public Queue() {
		queueArray = new int[SIZE];
		front = 0;
		rear = -1;
	}

	public void insert(int j) {
		if (rear == SIZE - 1)
			rear = -1;
		queueArray[++rear] = j;
	}

	public int remove() {
		int temp = queueArray[front++];
		if (front == SIZE)
			front = 0;
		return temp;
	}

	public boolean isEmpty() {
		return (rear + 1 == front || front + SIZE - 1 == rear);
	}
}