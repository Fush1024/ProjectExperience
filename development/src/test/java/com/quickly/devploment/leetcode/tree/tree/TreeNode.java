package com.quickly.devploment.leetcode.tree.tree;

/**
 * @Author lidengjin
 * @Date 2020/6/7 11:49 上午
 * @Version 1.0
 */
public class TreeNode {
	// 二叉树
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode parent;

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public TreeNode(int x) {
		val = x;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "TreeNode{" + "val=" + val + ", left=" + left + ", right=" + right + '}';
	}
}
