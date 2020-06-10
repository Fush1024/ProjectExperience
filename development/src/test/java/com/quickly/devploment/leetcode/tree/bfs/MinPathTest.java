package com.quickly.devploment.leetcode.tree.bfs;

import java.util.LinkedList;

/**
 * @Author lidengjin
 * @Date 2020/6/10 9:47 上午
 * @Version 1.0
 */

public class MinPathTest {

	/**
	 * 广度搜索，查找最小距离
	 */
	static int map[][] = new int[4][4];

	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = 0;
			}
		}
		new MinPathTest().bfs(3, 3);
	}

	public void bfs(int end_r, int end_c) {
		int next[][] = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};// 4个方向
		LinkedList<Node> q = new LinkedList<Node>();// 队列存储
		Node start = new Node(0, 0, 0);
		q.offer(start);
		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (temp.row == end_r && temp.cloumm == end_c) {
				System.out.println(temp.round);
				break;
			}
			for (int i = 0; i < 4; i++) {
				int r = temp.row + next[i][0];
				int c = temp.cloumm + next[i][1];
				if (r > 3 || c > 3 || r < 0 || c < 0 || map[r][c] == 1) {
					continue;
				}
				map[r][c] = 1;
				q.offer(new Node(r, c, temp.round + 1));
			}
		}
	}

	class Node {
		int row;
		int cloumm;
		int round;

		public Node(int row, int cloumm, int round) {
			super();
			this.row = row;
			this.cloumm = cloumm;
			this.round = round;
		}

	}

}
