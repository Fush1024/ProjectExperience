package com.quickly.devploment.leetcode.tree.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author lidengjin
 * @Date 2020/6/10 9:56 上午
 * @Version 1.0
 */
@Slf4j
public class TestDfsByTreeNode {
	private static List result = new LinkedList();

	private static TreeNode treeNode;

	@Test
	public void testDfs() {
		List<Integer> integers = TestDfsByTreeNode.DFSByRecursion(treeNode);
		log.info("dfs {}", integers);
	}

	@Before
	public void createTreeNode(){
		int[] array = {1, 23, 32, 4, 51, 65, 78, 8, 91,10};
		treeNode = ArrayConvertToTree.sortedArrayToBST(array);
	}

	@After
	public void destroyTreeNode(){
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
	public void testBfs(){
		List<Integer> integers = TestDfsByTreeNode.BFSByQueue(treeNode);
		log.info("bfs {}", integers);
	}


}
