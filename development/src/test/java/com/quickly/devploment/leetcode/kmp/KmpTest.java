package com.quickly.devploment.leetcode.kmp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lidengjin
 * @Date 2020/5/28 6:36 下午
 * @Version 1.0
 */
public class KmpTest {


	// 构造模式串 pattern 的最大匹配数表d
	int[] calculateMaxMatchLengths(String pattern) {
		int[] maxMatchLengths = new int[pattern.length()];
		int maxLength = 0;
		for (int i = 1; i < pattern.length(); i++) {
			while (maxLength > 0 && pattern.charAt(maxLength) != pattern.charAt(i)) {
				maxLength = maxMatchLengths[maxLength - 1]; // ①
			}
			if (pattern.charAt(maxLength) == pattern.charAt(i)) {
				maxLength++; // ②
			}
			maxMatchLengths[i] = maxLength;
		}
		return maxMatchLengths;
	}


	// 在文本 text 中寻找模式串 pattern，返回所有匹配的位置开头
	List<Integer> search(String text, String pattern) {
		List<Integer> positions = new ArrayList<>();
		int[] maxMatchLengths = calculateMaxMatchLengths(pattern);
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
			while (count > 0 && pattern.charAt(count) != text.charAt(i)) {
				count = maxMatchLengths[count - 1];
			}
			if (pattern.charAt(count) == text.charAt(i)) {
				count++;
			}
			if (count == pattern.length()) {
				positions.add(i - pattern.length() + 1);
				count = maxMatchLengths[count - 1];
			}
		}
		return positions;
	}

	@Test
	public void testKMP(){
		String pattern = "abababzabababa";
		String text = "acdsacdaca";
		List<Integer> search = new KmpTest().search(text, pattern);
		System.out.println(search);
	}
}
