package com.quickly.devploment.jvm.constructor;

/**
 * @Author lidengjin
 * @Date 2020/7/3 4:26 下午
 * @Version 1.0
 */
public class SuperUser {
	static {
		System.out.println("Super static ");
	}

	public SuperUser() {
		System.out.println("Super Constructor ");
	}
}
