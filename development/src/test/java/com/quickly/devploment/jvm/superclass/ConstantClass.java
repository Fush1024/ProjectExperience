package com.quickly.devploment.jvm.superclass;

/**
 * @Author lidengjin
 * @Date 2020/7/21 2:32 下午
 * @Version 1.0
 */
public class ConstantClass {
	static {
		System.out.println("Const class init");
	}
//	public static final  String HEADWORD = "hello";
	public static   String HEADWORD = "hello";
}
