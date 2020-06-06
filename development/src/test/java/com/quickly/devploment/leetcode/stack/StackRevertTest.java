package com.quickly.devploment.leetcode.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author lidengjin
 * @Date 2020/6/5 3:19 下午
 * @Version 1.0
 */
@Slf4j
public class StackRevertTest {

	private static Map<Character, Character> bracketCharHashMap;

	static {
		bracketCharHashMap = new HashMap<>();
		bracketCharHashMap.put(')','(');
		bracketCharHashMap.put('}','{');
		bracketCharHashMap.put(']','[');
	}

	/**
	 * 测试是否是回文数 通过栈的思想
	 */
	@Test
	public void testRevertWithMiddle() {
		String name = "hahahaha";
		char[] c = name.toCharArray();
		int middle = name.length() / 2;
		char[] a = new char[middle];
		int top = 0;
		for (int i = 0; i < middle; i++) {
			a[top++] = c[i];
		}
		int next;
		if (c.length % 2 == 0) {
			next = middle;
		} else
			next = middle + 1;
		for (int i = next; i < c.length; i++) {
			if (c[i] != a[--top]) {
				break;
			}
		}
		if (top == 0) {
			System.out.println("yes");
		} else
			System.out.println("no");
	}

	/**
	 * 括号匹配 stack
	 */
	@Test
	public void testBracketStack(){
		String bracket = "({[]}{}(())()){}";
		System.out.println(bracketStack(bracket));
	}


	private boolean bracketStack(String bracket) {
		bracket = bracket.trim();
		char[] chars = bracket.toCharArray();
		Stack<Character> stack = new Stack();
		if(chars.length % 2 != 0 ){
			return false;
		}
		for (int i = 0; i < chars.length; i++) {
			char aChar = chars[i];
			if(bracketCharHashMap.containsKey(aChar)){
				return bracketCharHashMap.get(aChar) == stack.pop();
			}
			stack.push(aChar);
		}
		if(stack.isEmpty()){
			return true;
		}
		return false;
	}

}


