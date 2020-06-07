package com.quickly.devploment.leetcode.tree.tree;

/**
 * @Author lidengjin
 * @Date 2020/6/7 11:46 上午
 * @Version 1.0
 */


public class BinaryTree {
	public static void main(String[] s) {

		// 中序
		Integer[] inorder = {28, 271, 0, 6, 561, 17, 3, 314, 2, 401, 641, 1, 257, 7, 278, 29};
		// 前序
		Integer[] preorder= {314, 6, 271, 28, 0, 561, 3, 17, 7, 2, 1, 401, 641, 257, 278, 29};
		BTreeBuilder treeBuilder = new BTreeBuilder(inorder, preorder);
		TreeNode n1 = treeBuilder.getNode1();
		TreeNode n2 = treeBuilder.getNode2();
		LowestCommonAscestor lca = new LowestCommonAscestor(n1, n2);
		TreeNode ascester = lca.getLCA();
		System.out.println("The lowest common anscestor of node " + n1.val + "," + n2.val + " is " + ascester.val);
	}
}

