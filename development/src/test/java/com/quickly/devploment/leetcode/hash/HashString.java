package com.quickly.devploment.leetcode.hash;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lidengjin
 * @Date 2020/6/6 3:36 下午
 * @Version 1.0
 * hash 比较 字符串的异位后是否相等 问题
 * <p>
 * 1 对数据 进行 sort ，然后比对
 * 2 通过hash 进行计数的比较 ---> 推荐
 */
public class HashString {

	@Test
	public void testHashString() {
		String name = "abc321asdd";
		String name1 = "bdsaacd123";
		//		boolean anagram = isAnagram(name, name1);
		//		System.out.println(anagram);

		boolean anagram_hash = isAnagram_hash(name, name1);
		System.out.println(anagram_hash);
	}

	/**
	 * 判断两个字符串中的字母是否一样
	 * <p>
	 * 方式1  仅限字母 但是效率高点儿
	 *
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length())
			return false;
		int[] ans = new int[26];
		int sz = s.length();
		char[] sc = s.toCharArray();
		char[] tc = t.toCharArray();
		for (int i = 0; i < sz; i++) {
			ans[sc[i] - 'a']++;
			ans[tc[i] - 'a']--;
		}
		for (int i = 0; i < 26; i++) {
			if (ans[i] != 0)
				return false;
		}
		return true;
	}

	/**
	 * hash
	 * 方式2 通过hash put ,比对个数
	 *
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram_hash(String s, String t) {
		if (s.equals(t)) {
			return true;
		} else if (null == s || null == t || s.length() != t.length() || s.length() <= 0) {//如果长度不相等，直接返回
			return false;
		}
		Map<Character, Integer> smap = new HashMap<>();
		chechString(s, smap);
		// 比较个数
		for (int i = 0; i < t.length(); i++) {
			if (smap.containsKey(t.charAt(i))) {
				smap.put(t.charAt(i), smap.get(t.charAt(i)) - 1);
				if (0 == smap.get(t.charAt(i))) {
					smap.remove(t.charAt(i));
				}
			}
		}
		return smap.size() == 0;
	}

	private static void chechString(String s, Map<Character, Integer> smap) {
		for (int i = 0; i < s.length(); i++) {
			if (smap.containsKey(s.charAt(i))) {
				smap.put(s.charAt(i), smap.get(s.charAt(i)) + 1);
			} else {
				smap.put(s.charAt(i), 1);
			}
		}
	}

}
