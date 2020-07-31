package com.quickly.devploment.face.two;


/**
 * @Author lidengjin
 * @Date 2020/7/31 2:14 下午
 * @Version 1.0
 */
public class NodeMove {


	/**
	 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
	 *     使用双指针解决，外加一个虚拟节点，实现 返回链表的头节点
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {3, 2, 5, 8, 4, 7, 6, 9};
		ListNode head = new ListNode(arr);
		head = removeNthFromEnd(head, 2);
		System.out.println(head.toString());
	}

	static ListNode removeNthFromEnd(ListNode head, int n) {
		//当链表为空或者要删除的节点小于等于0的时候，直接返回head
		if (head == null || n <= 0)
			return head;
		//建立一个虚拟的表头结点，因为需要删除的节点有可能是头结点，
		// 所以建立虚拟头结点可以不用分是否是头结点两种情况
		ListNode tempHead = new ListNode(0);
		tempHead.next = head;
		ListNode p = tempHead, q = tempHead;
		//p指针比q指针先跑n次
		for (int i = 0; i < n; i++) {
			//如果p为空的时候，说明这个节点的长度不足n，返回head
			if (p == null)
				return head;
			else {
				p = p.next;
			}
		}
		//p，q一起往前跑，直到p的next为空，
		// q所指向的下一个结点就是要删除的元素的位置
		while (p.next != null) {
			p = p.next;
			q = q.next;
		}

		//删除q指向的节点的下一个元素
		q.next = q.next.next;
		//删除虚拟头结点
		return tempHead.next;
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		public ListNode(int[] arr) {
			if (arr == null || arr.length == 0)
				throw new IllegalArgumentException("arr can to be empty");
			this.val = arr[0];
			ListNode cur = this;
			for (int i = 1; i < arr.length; i++) {
				cur.next = new ListNode(arr[i]);
				cur = cur.next;
			}
		}

		@Override
		public String toString() {
			StringBuilder res = new StringBuilder();
			ListNode cur = this;
			while (cur != null) {
				res.append(cur.val + "->");
				cur = cur.next;
			}
			res.append("NULL");
			return res.toString();
		}
	}
}
