package com.quickly.devploment.leetcode.revert;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName IntegersTarget
 **/
public class IntegersTarget {
	@Test
	public void test1(){
		int[] a = {1,2,3,3,2};
		int[] ints = twoSum(a, 6);
		for (int i = 0; i < ints.length; i++) {
			System.out.println(ints[i]);
		}

//		int reverse = reverse(1009);
//		System.out.println(reverse);

		System.out.println(1/10);
	}

	public int[] twoSum(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			return null;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				int[] result = new int[2];
				result[0] = map.get(target - nums[i]);
				result[1] = i;
				return result;
			}
			map.put(nums[i], i);
		}

		return null;
	}


	public int reverse(int x) {
		long result = 0;
		for (; x != 0; x /= 10) {
			result = result * 10 + x % 10;
		}
		if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
			return 0;
		}
		return (int)result;
	}


}
