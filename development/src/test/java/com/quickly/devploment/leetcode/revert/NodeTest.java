package com.quickly.devploment.leetcode.revert;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/6 10:23 上午
 * @Version 1.0
 */
@Slf4j
public class NodeTest {


	/**
	 * 单链表反转
	 */
	@Test
	public void revertNode() {
		Node tail = new Node("tail", null);
		Node node4 = new Node("4", tail);
		Node node3 = new Node("3", node4);
		Node node2 = new Node("2", node3);
		Node node1 = new Node("1", node2);
		tail.setNext(node2);

//		System.out.println(node1.toString());
//		System.out.println(node2.toString());
//		System.out.println(node3.toString());
//		System.out.println(node4.toString());
//		System.out.println(tail.toString());


//		Node.reverseListNode(node1);
//
//		System.out.println(node1.toString());
//		System.out.println(node2.toString());
//		System.out.println(node3.toString());
//		System.out.println(node4.toString());
//		System.out.println(tail.toString());

//		Node.swapPairs(node1);
//
//
//		System.out.println(node1.toString());
//		System.out.println(node2.toString());
//		System.out.println(node3.toString());
//		System.out.println(node4.toString());
//		System.out.println(tail.toString());

		Node solve = Node.solve(node1);
		System.out.println(solve.getNext().getData());

	}


}
