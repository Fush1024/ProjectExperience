package com.quickly.devploment.graph;

/**
 * 图的深度优先搜索底层---栈类
 *
 * @author Administrator
 */
public class StackX {

	/**
	 *  栈的大小
	 */
	private final int SIZE = 20;

	/**
	 *  栈的元素
	 */
	private int[] st;

	/**
	 *  栈的位置
	 */
	private int top;

	public StackX() {
		st = new int[SIZE];
		top = -1;
	}

	public void push(int j) {
		st[++top] = j;
	}

	public int pop() {
		return st[top--];
	}

	public int peek() {
		return st[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}
}
