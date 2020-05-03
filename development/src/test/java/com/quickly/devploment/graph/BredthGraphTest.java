package com.quickly.devploment.graph;

/**
 * @ClassName BredthGraphTest
 * @Description
 * @Author LiDengJin
 * @Date 2019/11/29 10:50
 * @Version V-1.0
 **/
public class BredthGraphTest {
	public static void main(String[] args) {
		QGraph g = new QGraph();
		g.addVertex('A');
		g.addVertex('B');
		g.addVertex('C');
		g.addVertex('D');
		g.addEdage(0, 1);
		g.addEdage(2, 1);
		g.addEdage(0, 3);
		//    g.displayVertex(0);
		g.bfs();
	}
}
