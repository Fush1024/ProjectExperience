package com.quickly.devploment.leetcode.tree.bfs.graph;

import java.util.Map;

/**
 * @Author lidengjin
 * @Date 2020/6/10 9:51 上午
 * @Version 1.0
 */
public interface Algorithm {
	/**
	 * 执行算法
	 */
	void perform(Graph g, String sourceVertex);
	/**
	 * 得到路径
	 */
	Map<String, String> getPath();
}

