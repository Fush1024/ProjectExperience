package com.quickly.devploment.leetcode.pruning;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Author lidengjin
 * @Date 2020/6/11 8:59 上午
 * @Version 1.0
 */
@Slf4j
public class NEmpress {
	/**
	 * 用深度优先搜索查找N皇后问题
	 *
	 * @author dell
	 */
	public static void main(String args[]) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<String> arrayResult = new ArrayList<String>();
		int n = 4;
		returnNQueen(array, arrayResult, n);
		System.out.println("总的解的个数为：" + arrayResult.size());
		Iterator<String> it = arrayResult.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	/**
	 * 深度优先搜索DFS可以采用递归的方式求解
	 *
	 * @param array为存储的是数字，表示每一行的皇后所在的列数
	 * @param arrayResult为一个解，主要把解转换成字符串
	 * @param n皇后的问题规模
	 */
	public static void returnNQueen(ArrayList<Integer> array, ArrayList<String> arrayResult, int n) {
		// 判断ArrayList是否已满
		if (array.size() == n) {
			arrayResult.add(array.toString());
		}
		// 没满的情况
		for (int i = 0; i < n; i++) {
			if (checkIsNQueen(array, i)) {
				array.add(i);
				returnNQueen(array, arrayResult, n);//递归求解
				array.remove(array.size() - 1);
			}
		}
	}

	/**
	 * 对解进行检查，主要有两种：1、皇后出现在同一列上
	 * 2、皇后在两个对角线上
	 *
	 * @param array存放的以求出的皇后的位置
	 * @param k新的皇后的位置
	 * @return 如果将新的皇后插入，检查此时是否会有冲突
	 */
	public static boolean checkIsNQueen(ArrayList<Integer> array, int k) {
		int n = array.size();// 得到数组的大小
		for (int i = 0; i < n; i++) {
			// 第一种情况
			if (array.get(i) == k) {
				return false;
			}

			// 第二种情况
			if (n - i == Math.abs(k - array.get(i))) {
				return false;
			}
		}
		return true;
	}

}
