package com.quickly.devploment.leetcode.revert;

/**
 * @Author lidengjin
 * @Date 2020/6/6 10:22 上午
 * @Version 1.0
 */
public class Node {
	private Object data;//数据域
	private Node next;//指针域

	public Node(Object data) {
		this.data = data;
	}

	public Node(Object data, Node next) {
		this.data = data;
		this.next = next;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node{" + "data=" + data + ", next=" + next + '}';
	}

	public static Node reverseListNode(Node head) {
		//单链表为空或只有一个节点，直接返回原单链表
		if (head == null || head.getNext() == null) {
			return head;
		}
		//前一个节点指针
		Node preNode = null;
		//当前节点指针
		Node curNode = head;
		//下一个节点指针
		Node nextNode = null;

		while (curNode != null) {
			nextNode = curNode.getNext();//nextNode 指向下一个节点
			curNode.setNext(preNode);//将当前节点next域指向前一个节点
			preNode = curNode;//preNode 指针向后移动
			curNode = nextNode;//curNode指针向后移动
		}
		return preNode;
	}


	/**
	 * 链表两两互换
	 *
	 * @param head
	 * @return
	 */
	public static Node swapPairs(Node head) {
		// 链表头增加虚拟结点 dummy
		Node dummy = new Node(-1);
		dummy.next = head;
		head = dummy;
		// 循环退出条件，注意链表结点数单双的情况
		while (head.next != null && head.next.next != null) {
			// 开始反转
			Node a = head.next;
			Node b = a.next;
			head.next = b; // 步骤①
			a.next = b.next; // 步骤①
			b.next = a; // 步骤②
			// dummy 指针前移
			head = a;
		}
		return dummy.next;
	}

	/**
	 * 龟兔赛跑寻找循环链表入口
	 *
	 * @param head
	 * @return
	 */
	public static Node solve(Node head) {
		Node rabbit = head;
		Node turtle = head;
		while (rabbit != null && rabbit.next != null) {
			rabbit = rabbit.next.next;
			turtle = turtle.next;
			if (rabbit == turtle)
				break;
		}
		if (rabbit == null || rabbit.next == null)
			return null;
		rabbit = head;
		while (rabbit != turtle) {
			rabbit = rabbit.next;
			turtle = turtle.next;
		}
		return rabbit;
	}
}
