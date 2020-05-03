package com.quickly.devploment.graph;

/**
 * @ClassName DeepGraphTest
 * @Description 深度优先搜索测试类
 * @Author LiDengJin
 * @Date 2019/11/29 10:17
 * @Version V-1.0
 **/
public class DeepGraphTest {
	public static void main(String[] args) {
		Graph g = new Graph();
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addEdage(0, 1);
		g.addEdage(2, 1);
		g.addEdage(2, 3);
		//    g.displayVertex(0);
		g.dfs();
	}
}
