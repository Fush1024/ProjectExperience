package com.quickly.devploment.leetcode.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

/**
 * @Author lidengjin
 * @Date 2020/6/5 4:11 下午
 * @Version 1.0
 */
@Slf4j
public class TheCatAndFish {

	/**
	 * 小猫钓鱼
	 *
	 */
	@Test
	public void testCat() {
		//a手牌
		LinkedList<Integer> a = new LinkedList<>();
		//b手牌
		LinkedList<Integer> b = new LinkedList<>();
		int n = 3;
		for (int i = 0; i < n; i++) {
			a.add(new Random().nextInt(3));
			b.add(new Random().nextInt(3));
		}
		log.info("a {}, b {}", a, b);
		//定义一个栈，用来放置桌面手牌
		Stack<Integer> stack = new Stack();
		System.out.println("游戏开始！");

		//有一人手牌为空即为游戏结束
		while (!a.isEmpty() && !b.isEmpty()) {
			int x = a.removeFirst();
			if (a.isEmpty()) {
				//a获胜
				break;
			} else {
				if (stack.contains(x)) {
					//如果栈中有这张牌，a收牌
					a.addLast(x);
					int index = stack.search(x);
					for (int i = 0; i < index; i++) {
						a.addLast(stack.pop());
					}
				} else {
					//添加到栈中
					stack.push(x);
					//B出牌
					int y = b.removeFirst();
					if (b.isEmpty()) {
						//b获胜
						break;
					} else {
						if (stack.contains(y)) {
							//如果栈中有这张牌，b收牌
							b.addLast(y);
							int index = stack.search(y);
							for (int i = 0; i < index; i++) {
								b.addLast(stack.pop());
							}
						} else {
							stack.push(y);
						}
					}
				}
			}
		}
		if (a.isEmpty()) {
			System.out.println("a获胜！");
		}
		if (b.isEmpty()) {
			System.out.println("b获胜！");
		}
		System.out.println("游戏结束！");
	}
}
