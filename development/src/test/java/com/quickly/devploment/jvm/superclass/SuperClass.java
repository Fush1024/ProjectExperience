package com.quickly.devploment.jvm.superclass;

/**
 * @Author lidengjin
 * @Date 2020/7/21 2:28 下午
 * @Version 1.0
 */
public class SuperClass {
	static {
		System.out.println("super class static  ");
	}
	// 加不加final 会决定是否执行 static 的内部逻辑
	public static final String value = "super value";

	static class SubClass extends SuperClass{
		static {
			System.out.println("sub class static ");
		}
	}
}
