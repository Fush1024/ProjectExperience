package com.quickly.devploment.leetcode.kmp;

/**
 * @Author lidengjin
 * @Date 2020/7/29 11:06 上午
 * @Version 1.0
 */
public class KmpTestTWO {

	/**
	 * 根据模式字符串，生成next数组
	 */
	public static int[] makeNext(String ptn) {
		if (ptn == null)
			throw new IllegalArgumentException("ptn");

		int n = ptn.length();
		int[] next = new int[n];

		next[0] = -1;
		int j = 0; // 指针j跟踪当前待求解的模式字符
		int k = next[j]; // 假设已知next[j]==k

		while (j < n - 1)// 模式字符串的next[0]已知，故剩余n-1个待求解字符
		{
			if (k == -1 || ptn.charAt(j) == ptn.charAt(k)) {
				next[j + 1] = k + 1;
				k++;
				j++;
			} else {
				k = next[k];
			}
		}
		return next;
	}

	public static int KMP(String ptn, String txt) {
		int[] next = makeNext(ptn);

		int i = 0, j = 0;         // i指示文本，j指示模式字符串
		while (i < txt.length() && j < ptn.length()) {
			if (next[j] == -1 || ptn.charAt(j) == txt.charAt(i)) {
				i++;
				j++;
			} else {
				j = next[j];    // 模式字符串右移
			}
		}
		if (j == ptn.length())    // 匹配成功
			return i - j;
		else                      // 匹配失败
			return -1;
	}

	public static void main(String[] args) {
		String name = "ABCDEFD";
		String patten = "ABCDEFABCDEFD";
		int kmp = KMP(name, patten);
		System.out.println(kmp);
	}

	// https://www.jianshu.com/p/fe107b4e4271
}
