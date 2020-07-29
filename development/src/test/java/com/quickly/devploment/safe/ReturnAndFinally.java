package com.quickly.devploment.safe;

/**
 * @Author lidengjin
 * @Date 2020/7/2 10:44 上午
 * @Version 1.0
 */
public class ReturnAndFinally {

	public static void main(String[] args) {
		System.out.println("result "+testReturn());
	}

	private static int testReturn() {
		int i = 10;
		try {
//			int s = 10/0;
			return returnMethod(i);
		} catch (Exception e) {
			i = 20;
			System.out.println("catch " + i);
		} finally {
			i = 30;
			System.out.println("finally " + i);
		}
		return i;
	}

	private static int returnMethod(int i) {
		System.out.println("return --" + i);
//		throw new RuntimeException();
		return i;
	}
}
