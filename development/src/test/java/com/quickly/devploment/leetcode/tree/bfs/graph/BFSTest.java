package com.quickly.devploment.leetcode.tree.bfs.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

/**
 * @Author lidengjin
 * @Date 2020/6/10 9:54 上午
 * @Version 1.0
 */
public class BFSTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: test()
	 */
	@Test
	public void BFSTest() throws Exception {
		//TODO: Test goes here...
		Graph g = new Graph(new BroadFirstSearchAlgorithm());
		//添加顶点
		g.addVertex("North Gate");
		g.addVertex("South Gate");
		g.addVertex("Classroom");
		g.addVertex("Square");
		g.addVertex("Toilet");
		g.addVertex("Canteen");

		//添加边
		g.addEdge("North Gate", "Classroom");
		g.addEdge("North Gate", "Square");
		g.addEdge("Classroom", "Toilet");
		g.addEdge("Square", "Toilet");
		g.addEdge("Square", "Canteen");
		g.addEdge("Toilet", "South Gate");
		g.addEdge("Toilet", "South Gate");

		g.done();
		g.setFirstVertex("North Gate");
		Stack<String> result = g.findPathTo("Canteen");
		System.out.println("BFS: From [North Gate] to [Canteen]:");
		while (!result.isEmpty()) {
			System.out.println(result.pop());
		}
	}
}

