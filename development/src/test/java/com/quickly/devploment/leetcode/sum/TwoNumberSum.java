package com.quickly.devploment.leetcode.sum;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;

/**
 * @Author lidengjin
 * @Date 2020/6/5 11:13 上午
 * @Version 1.0
 */
@Slf4j
public class TwoNumberSum {
	/**
	 *  给一个 target ，和一个数组 找到组成target 的数组下标值
	 */
	@Test
	public void testTwoNumberSum(){
		int target = 13;
		int[] args = new int[]{1,7,4,3,6,5,6};
		int[] indexOfSum = getIndexOfSum(target, args);
		log.info("target {}, indexOfSum = {}",target, indexOfSum);
	}

	private int[] getIndexOfSum(int target, int[] args) {
		HashMap<Integer, Integer> sumMap = new HashMap<>();
		for (int i = 0; i < args.length; i++) {
			sumMap.put(args[i], i);
		}
		for (int arg : args) {
			if(sumMap.containsKey(target - arg)){
				return new int[]{sumMap.get(arg),sumMap.get(target - arg)};
			}
		}
		return null;
	}
}
