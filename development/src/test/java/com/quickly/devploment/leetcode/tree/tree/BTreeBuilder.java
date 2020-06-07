package com.quickly.devploment.leetcode.tree.tree;

/**
 * @Author lidengjin
 * @Date 2020/6/7 11:46 上午
 * @Version 1.0
 */

import java.util.HashMap;


public class BTreeBuilder {
	private HashMap<Integer, Integer> nodeMap = new HashMap<Integer, Integer>();
	private TreeNode root = null;
	private TreeNode node1 = null;
	private TreeNode node2 = null;

	public static Integer nodeValue1 = 401;

	public static Integer nodeValue2 = 29;



	public BTreeBuilder(Integer[] inorder, Integer[] preorder) {
		for (int i = 0; i < inorder.length; i++) {
			nodeMap.put(inorder[i], i);
		}

		buildTree(preorder);
	}

	private void buildTree(Integer[] preorder) {
		if (root == null) {
			root = new TreeNode(preorder[0]);
		}

		for (int i = 1; i < preorder.length; i++) {
			int val = preorder[i];
			TreeNode current = root;
			while (true) {
				TreeNode node = null;

				if (nodeMap.get(val) < nodeMap.get(current.val)) {
					if (current.left != null) {
						current = current.left;
					} else {
						node = new TreeNode(val);
						current.left = node;
						setNode(node);
						node.parent = current;
						break;
					}
				} else {
					if (current.right != null) {
						current = current.right;
					} else {
						node = new TreeNode(val);
						current.right = node;
						node.parent = current;
						setNode(node);
						break;
					}
				}

			}
		}
	}

	private void setNode(TreeNode node) {
		if (node != null && node.val == nodeValue1) {
			node1 = node;
		}

		if (node != null && node.val == nodeValue2) {
			node2 = node;
		}
	}

	public TreeNode getNode1() {
		return node1;
	}

	public TreeNode getNode2() {
		return node2;
	}

	public TreeNode getTreeRoot() {
		return root;
	}

	@Override
	public String toString() {
		return "BTreeBuilder{" + "nodeMap=" + nodeMap + ", root=" + root + ", node1=" + node1 + ", node2=" + node2
				+ '}';
	}
}
