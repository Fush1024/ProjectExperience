package com.quickly.devploment.leetcode.tree.tree;


import org.junit.Test;

import java.util.List;

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
	 */
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
		ArrayConvertToTree.preOrderTraveralWithStack(treeNode1);
	}


	@Test
	public void testCreateTree() {
		//		int[] inorder = new int[]{28, 271, 0, 6, 561, 17, 3, 314, 2, 401, 641, 1, 257, 7, 278, 29};
		//		TreeNode treeNode = ArrayConvertToTree.sortedArrayToBST(inorder);
		//		ArrayConvertToTree.inOrderTraveralWithStack(treeNode);
		//		ArrayConvertToTree.preOrderTraveralWithStack(treeNode);
		int[] array = {1, 2, 3, 4, 5, 6};
		TreeNode treeNode = ArrayConvertToTree.sortedArrayToBST(array);
		System.out.println(treeNode);
		List<Integer> integers = ArrayConvertToTree.preOrderTraveralWithStack(treeNode);
		System.out.println();
		List<Integer> integers1 = ArrayConvertToTree.inOrderTraveralWithStack(treeNode);
		for (int i = 0; i < integers.size(); i++) {
			System.out.print(integers.get(i)+" - ");
		}
		System.out.println();
		for (int i = 0; i < integers1.size(); i++) {
			System.out.print(integers1.get(i)+" - ");
		}

	}

	@Test
	public void testTreeNodeParent(){
		int[] array = {1, 2, 3, 4, 5, 6, 234,43,32,234};
		TreeNode treeNode = ArrayConvertToTree.sortedArrayToBST(array);
		List<Integer> inOrderList = ArrayConvertToTree.inOrderTraveralWithStack(treeNode);
		List<Integer> preOrderList = ArrayConvertToTree.preOrderTraveralWithStack(treeNode);
		BTreeBuilder.nodeValue1 = 4;
		BTreeBuilder.nodeValue2 = 1;
		BTreeBuilder treeBuilder = new BTreeBuilder(inOrderList.toArray(new Integer[inOrderList.size()]), preOrderList.toArray(new Integer[preOrderList.size()]));
		TreeNode n1 = treeBuilder.getNode1();
		TreeNode n2 = treeBuilder.getNode2();
		LowestCommonAscestor lca = new LowestCommonAscestor(n1, n2);
		TreeNode ascester = lca.getLCA();
		System.out.println("The lowest common anscestor of node " + n1.val + "," + n2.val + " is " + ascester.val);
	}


}
