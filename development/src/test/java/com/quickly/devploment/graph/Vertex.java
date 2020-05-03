package com.quickly.devploment.graph;

/**
 * 图的顶点类
 *
 * @author Administrator
 */
public class Vertex {
	/**
	 *  标签
	 */
	public char lable;
	/**
	 *  是否是可见的
	 */
	public boolean wasvisited;

	public Vertex(char lab) {
		lable = lab;
		wasvisited = false;
	}
}