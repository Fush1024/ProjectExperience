package com.quickly.devploment.leetcode.tree.tree;

/**
 * @Author lidengjin
 * @Date 2020/6/7 11:45 上午
 * @Version 1.0
 */

public class LowestCommonAscestor {
	private TreeNode node1 = null;
	private TreeNode node2 = null;

	public LowestCommonAscestor(TreeNode n1, TreeNode n2) {
		this.node1 = n1;
		this.node2 = n2;
	}

	private int findNodeHeight(TreeNode n) {
		int h = 0;
		while (n.parent != null) {
			h++;
			n = n.parent;
		}

		return h;
	}

	private TreeNode retrackByHeight(TreeNode n, int h) {
		while (n.parent != null && h > 0) {
			h--;
			n = n.parent;
		}

		return n;
	}

	private TreeNode traceBack(TreeNode n1, TreeNode n2) {
		while (n1 != n2) {
			if (n1 != null) {
				n1 = n1.parent;
			}

			if (n2 != null) {
				n2 = n2.parent;
			}
		}

		return n1;
	}

	public TreeNode getLCA() {
		int h1 = findNodeHeight(node1);
		int h2 = findNodeHeight(node2);

		if (h1 > h2) {
			node1 = retrackByHeight(node1, h1 - h2);
		} else if (h1 < h2) {
			node2 = retrackByHeight(node2, h2 - h1);
		}

		return traceBack(node1, node2);
	}
}

