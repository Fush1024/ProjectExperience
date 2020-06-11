package com.quickly.devploment.leetcode.pruning;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lidengjin
 * @Date 2020/6/10 2:11 下午
 * @Version 1.0
 */
@Slf4j
public class Pruning {

	/**
	 * 剪枝 添加括号
	 *
	 * @param
	 * @return
	 */
	public static List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList();
		String str = new String();
		generate(list, str, n, n);
		return list;
	}

	//list的改动是全局性的，但是str每次传递时并不是将本身传给下面，而是str+‘（’或者str+')'传递，所以下一层的改动并不会影响上一层。
	public static void generate(List<String> list, String str, int left, int right) {
		if (left == 0 && right == 0) {
			list.add(str);
			return;
		}
		if (left > 0) {
			generate(list, str + '(', left - 1, right);
		}
		if (right > left) {
			generate(list, str + ')', left, right - 1);
		}
	}

	@Test
	public void testPruning() {
		List<String> strings = Pruning.generateParenthesis(3);
		log.info("pruning {}", strings);
	}

}
