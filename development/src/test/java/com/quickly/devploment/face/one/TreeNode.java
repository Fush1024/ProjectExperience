package com.quickly.devploment.face.one;

/**
 * @Author lidengjin
 * @Date 2020/7/30 4:54 下午
 * @Version 1.0
 */

/**
 * 给定一个二叉搜索树(BST)，找到树中第 K 小的节点
 */
public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

class Solution {

	private class ResultType {

		boolean found;  // 是否找到

		int val;  // 节点数目

		ResultType(boolean found, int val) {
			this.found = found;
			this.val = val;
		}
	}

	public int kthSmallest(TreeNode root, int k) {
		return kthSmallestHelper(root, k).val;
	}

	private ResultType kthSmallestHelper(TreeNode root, int k) {

		if (root == null) {
			return new ResultType(false, 0);
		}

		ResultType left = kthSmallestHelper(root.left, k);

		// 左子树找到，直接返回
		if (left.found) {
			return new ResultType(true, left.val);
		}

		// 左子树的节点数目 = K-1，结果为 root 的值
		// 因为寻找 第 k 个 小的数的值 那么 当 k-left.value 的时候 就是这个值了。
		if (k - left.val == 1) {
			return new ResultType(true, root.val);
		}

		// 右子树寻找
		ResultType right = kthSmallestHelper(root.right, k - left.val - 1);

		if (right.found) {
			return new ResultType(true, right.val);
		}

		// 没找到，返回节点总数
		return new ResultType(false, left.val + 1 + right.val);
	}

	public static void main(String[] args) {
		TreeNode treeNode1 = new TreeNode(10);
		TreeNode treeNode2 = new TreeNode(8);
		TreeNode treeNode3 = new TreeNode(12);
		TreeNode treeNode4 = new TreeNode(6);
		TreeNode treeNode5 = new TreeNode(9);

		treeNode1.left = treeNode2;
		treeNode1.right = treeNode3;
		treeNode2.left = treeNode4;
		treeNode2.right = treeNode5;

		int i = new Solution().kthSmallest(treeNode1, 2);
		System.out.println(i);
	}
}
