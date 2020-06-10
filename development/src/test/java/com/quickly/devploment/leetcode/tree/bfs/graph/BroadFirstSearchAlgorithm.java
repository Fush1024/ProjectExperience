package com.quickly.devploment.leetcode.tree.bfs.graph;

import java.util.*;

/**
 * @Author lidengjin
 * @Date 2020/6/10 9:52 上午
 * @Version 1.0
 */
public class BroadFirstSearchAlgorithm implements Algorithm {
	// 保存已经访问过的地点
	private List<String> visitedVertex;
	// 保存最短路径
	private Map<String, String> path;

	@Override
	public void perform(Graph g, String sourceVertex) {
		if (null == visitedVertex) {
			visitedVertex = new ArrayList<>();
		}
		if (null == path) {
			path = new HashMap<>();
		}
		BFS(g, sourceVertex);
	}

	@Override
	public Map<String, String> getPath() {
		return path;
	}

	private void BFS(Graph g, String sourceVertex) {
		Queue<String> queue = new LinkedList<>();
		//标记起点
		visitedVertex.add(sourceVertex);
		// 起点入列
		queue.add(sourceVertex);
		while (!queue.isEmpty()) {
			String ver = queue.poll();  //返回队列头部，或null，如果队列为空
			List<String> toBeVisitedVertex = g.getAdj().get(ver);
			for (String v : toBeVisitedVertex) {
				if (!visitedVertex.contains(v)) {
					visitedVertex.add(v);
					path.put(v, ver);
					queue.add(v);   //后添加的要等前面的所有距离为currDist的顶点都被处理之后才被处理
				}
			}
		}
	}
}
