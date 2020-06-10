package com.quickly.devploment.leetcode.tree.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @Author lidengjin
 * @Date 2020/6/10 9:56 上午
 * @Version 1.0
 */
@Slf4j
public class DfsAndBfsTreeNode {
	private static List result = new LinkedList();

	private static TreeNode treeNode;

	@Test
	public void testDfs() {
		List<Integer> integers = DfsAndBfsTreeNode.DFSByRecursion(treeNode);
		log.info("dfs {}", integers);
	}

	@Before
	public void createTreeNode() {
		int[] array = {12,3,4,52,13,45,15,8,16};
		Arrays.sort(array);
		treeNode = ArrayConvertToTree.sortedArrayToBST(array);
		List<Integer> preOrderTraveralWithStack = ArrayConvertToTree.preOrderTraveralWithStack(treeNode);
		log.info("pre {}",preOrderTraveralWithStack);
		log.info("inorder {}",ArrayConvertToTree.inOrderTraveralWithStack(treeNode));
	}

	@After
	public void destroyTreeNode() {
		treeNode = null;
	}

	/**
	 * DFS 遍历
	 *
	 * @param root
	 * @return
	 */
	public static List<Integer> DFSByRecursion(TreeNode root) {

		if (root == null) {
			return null;
		}
        /*
        处理节点的逻辑(由于是递归的调用，定义ArrayList的时候不能写在方法内部)
         private static List<Integer> result = new ArrayList<>();
         我把 此处的 result 的定义为了一个全局变量
         */
		result.add(root.val);

		if (root.left != null) {
			DFSByRecursion(root.left);
		}
		if (root.right != null) {
			DFSByRecursion(root.right);
		}
		return result;
	}

	/**
	 * BFS 遍历 二叉树
	 *
	 * @param root
	 * @return
	 */
	public static List<Integer> BFSByQueue(TreeNode root) {
		if (root == null) {
			return null;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		//存放遍历结果，然后返回
		List<Integer> result = new ArrayList<>();

		while (!queue.isEmpty()) {
			TreeNode treeNode = queue.poll();

            /*
            处理 TreeNode 节点 的逻辑
             */
			result.add(treeNode.val);


			if (treeNode.left != null) {
				queue.add(treeNode.left);
			}
			if (treeNode.right != null) {
				queue.add(treeNode.right);
			}
		}
		return result;
	}

	@Test
	public void testBfs() {
		List<Integer> integers = DfsAndBfsTreeNode.BFSByQueue(treeNode);
		log.info("bfs {}", integers);
	}


	/**
	 * Bfs 遍历树的最小深度
	 *
	 * @param treeNode
	 * @return
	 */
	public static int minBfsDeep(TreeNode treeNode) {
		int deep = 0;
		Queue queue = new LinkedList();
		queue.add(treeNode);
		while (!queue.isEmpty()) {
			TreeNode t = (TreeNode) queue.poll();
			if (t.left == null && t.right == null) {
				break;
			}
			deep++;
			if (t.left != null) {
				TreeNode left = t.left;
				queue.add(left);
			}
			if (t.right != null) {
				TreeNode right = t.right;
				queue.add(right);
			}
		}
		return deep;
	}

	@Test
	public void minBfsTreeDepth() {
		List<Integer> integers = DfsAndBfsTreeNode.BFSByQueue(treeNode);
		log.info("bfs {}", integers);
		int i = DfsAndBfsTreeNode.minBfsDeep(treeNode);
		System.out.println(i);
	}


	/**
	 * Dfs 二叉树最大深度
	 *
	 * @param root
	 * @return
	 */
	public static int maxDfsDepth(TreeNode root) {
		// return root==null?0:Math.max(maxDepth(root.left),maxDepth(root.right))+1;
		// 等同于
		if (root == null)
			return 0;
		else
			return 1 + Math.max(maxDfsDepth(root.left), maxDfsDepth(root.right));
	}

	@Test
	public void testMaxDfsTreeDeep(){
		List<Integer> integers = DfsAndBfsTreeNode.DFSByRecursion(treeNode);
		log.info("dfs {}", integers);
		int i = DfsAndBfsTreeNode.maxDfsDepth(treeNode);
		System.out.println(i);
	}


}
