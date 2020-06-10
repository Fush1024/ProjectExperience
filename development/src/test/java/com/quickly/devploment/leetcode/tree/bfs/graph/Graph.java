package com.quickly.devploment.leetcode.tree.bfs.graph;

import java.util.*;

/**
 * @Author lidengjin
 * @Date 2020/6/10 9:51 上午
 * @Version 1.0
 */
public class Graph {
	// 图的起点
	private String firstVertex;
	// 邻接表
	private Map<String, List<String>> adj = new HashMap<>();
	// 遍历算法
	private Algorithm algorithm;

	public Graph(Algorithm algorithm) { //策略模式，将Graph与算法分离
		this.algorithm = algorithm;
	}

	public void setFirstVertex(String firstVertex) {
		this.firstVertex = firstVertex;
	}

	/**
	 * 执行算法
	 */
	public void done() {
		algorithm.perform(this, firstVertex);
	}

	/**
	 * 得到从起点到{@code vertex}点的最短路径
	 *
	 * @param vertex
	 * @return
	 */
	public Stack<String> findPathTo(String vertex) {
		Stack<String> stack = new Stack<>();
		stack.add(vertex);
		Map<String, String> path = algorithm.getPath();
		for (String location = path.get(vertex); !location.equals(firstVertex); location = path.get(location)) {
			stack.push(location);
		}
		stack.push(firstVertex);
		return stack;
	}

	/**
	 * 添加一条边
	 */
	public void addEdge(String fromVertex, String toVertex) {
		if (firstVertex == null) {
			firstVertex = fromVertex;
		}
		adj.get(fromVertex).add(toVertex);
		adj.get(toVertex).add(fromVertex);
	}

	/**
	 * 添加一个顶点
	 */
	public void addVertex(String vertex) {
		adj.put(vertex, new ArrayList<String>());
	}

	public Map<String, List<String>> getAdj() {
		return adj;
	}
}
