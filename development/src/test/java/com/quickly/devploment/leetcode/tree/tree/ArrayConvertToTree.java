package com.quickly.devploment.leetcode.tree.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author lidengjin
 * @Date 2020/6/7 3:36 下午
 * @Version 1.0
 */
public class ArrayConvertToTree {

	/**
	 * 数组转二叉树
	 *
	 * @param nums
	 * @return
	 */
	public static TreeNode sortedArrayToBST(int[] nums) {
		return sortedArrayToBST(nums, 0, nums.length - 1);
	}

	private static TreeNode sortedArrayToBST(int[] nums, int left, int right) {
		if (left > right)
			return null;                                        //边界条件，注意是left>right
		int mid = (left + right) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = sortedArrayToBST(nums, left, mid - 1);        //递归向左探索，范围变成left~mid-1;
		root.right = sortedArrayToBST(nums, mid + 1, right);
		return root;
	}


	/**
	 * 前序遍历
	 *
	 * @param node
	 */
	public static List<Integer> preOrderTraveralWithStack(TreeNode node) {

		ArrayList<Integer> arrayList = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode treeNode = node;
		while (treeNode != null || !stack.isEmpty()) {
			//迭代访问节点的左孩子，并入栈
			while (treeNode != null) {
				arrayList.add(treeNode.val);
//				System.out.print(treeNode.val + " ");
				stack.push(treeNode);
				treeNode = treeNode.left;
			}
			//如果节点没有左孩子，则弹出栈顶节点，访问节点右孩子
			if (!stack.isEmpty()) {
				treeNode = stack.pop();
				treeNode = treeNode.right;
			}
		}
		return arrayList;
	}

	/**
	 * 中序遍历
	 *
	 * @param node
	 */
	public static List<Integer> inOrderTraveralWithStack(TreeNode node) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode treeNode = node;
		ArrayList<Integer> arrayList = new ArrayList<>();
		while (treeNode != null || !stack.isEmpty()) {
			while (treeNode != null) {
				stack.push(treeNode);
				treeNode = treeNode.left;
			}
			if (!stack.isEmpty()) {
				treeNode = stack.pop();
				arrayList.add(treeNode.val);
//				System.out.print(treeNode.val + " ");
				treeNode = treeNode.right;
			}
		}
		return arrayList;
	}

	/**
	 * 后序遍历
	 *
	 * @param node
	 */
	public static List<Integer> postOrderTraveralWithStack(TreeNode node) {

		ArrayList<Integer> arrayList = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode treeNode = node;
		TreeNode lastVisit = null;   //标记每次遍历最后一次访问的节点
		while (treeNode != null || !stack.isEmpty()) {//节点不为空，结点入栈，并且指向下一个左孩子
			while (treeNode != null) {
				stack.push(treeNode);
				treeNode = treeNode.left;
			}
			//栈不为空
			if (!stack.isEmpty()) {
				//出栈
				treeNode = stack.pop();
				/**
				 * 这块就是判断treeNode是否有右孩子，
				 * 如果没有输出treeNode.data，让lastVisit指向treeNode，并让treeNode为空
				 * 如果有右孩子，将当前节点继续入栈，treeNode指向它的右孩子,继续重复循环
				 */
				if (treeNode.right == null || treeNode.right == lastVisit) {
					arrayList.add(treeNode.val);
//					System.out.print(treeNode.val + " ");
					lastVisit = treeNode;
					treeNode = null;
				} else {
					stack.push(treeNode);
					treeNode = treeNode.right;
				}

			}

		}
		return arrayList;
	}
}
