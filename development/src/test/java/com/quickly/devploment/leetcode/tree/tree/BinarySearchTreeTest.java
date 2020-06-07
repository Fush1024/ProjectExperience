package com.quickly.devploment.leetcode.tree.tree;


import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/7 11:20 上午
 * @Version 1.0
 */
public class BinarySearchTreeTest {



	public boolean isValidBST(TreeNode root) {
		return isValidBST_recursion(root, null, null);
	}

	private boolean isValidBST_recursion(TreeNode root, Integer min, Integer max) {
		if (root == null)
			return true;
		if ((min != null && root.val <= min) || (max != null && root.val >= max))
			return false;
		return isValidBST_recursion(root.left, min, root.val) && isValidBST_recursion(root.getRight(), root.val, max);
	}

	/**
	 * 校验某个树 是否是二叉树。
	 * 1 通过 root 与 left 的 max 和 right 的 min 进行比较 ，进行递归
	 * 2 通过中序遍历  ，有序性，验证是否是二叉树
	 * */
	@Test
	public void testCheckBinarySearchTree() {
		TreeNode treeNode1 = new TreeNode(10);
		TreeNode treeNode2 = new TreeNode(1);
		TreeNode treeNode3 = new TreeNode(3);
		TreeNode treeNode4 = new TreeNode(5);
		TreeNode treeNode5 = new TreeNode(19);
		TreeNode treeNode6 = new TreeNode(34);
		treeNode1.setLeft(treeNode4);
		treeNode1.setRight(treeNode5);
		treeNode4.setLeft(treeNode3);
		treeNode3.setLeft(treeNode2);
		treeNode5.setRight(treeNode6);
		System.out.println(isValidBST(treeNode1));
	}
}
