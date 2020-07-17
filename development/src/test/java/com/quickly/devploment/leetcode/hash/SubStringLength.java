package com.quickly.devploment.leetcode.hash;

import java.util.HashMap;

/**
 * @Author lidengjin
 * @Date 2020/7/17 5:16 下午
 * @Version 1.0
 */
public class SubStringLength {

	/**
	 * 滑动窗口
	 *
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstringTwo(String s) {
		if (s.length() == 0)
			return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int max = 0;
		int left = 0;
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				left = Math.max(left, map.get(s.charAt(i)) + 1);
			}
			map.put(s.charAt(i), i);
			max = Math.max(max, i - left + 1);
		}
		return max;

	}

	public static void main(String[] args) {
		String s = "abcdeafdasdcxaaws";
		int i = lengthOfLongestSubstringTwo(s);
		System.out.println(i);
	}
}
