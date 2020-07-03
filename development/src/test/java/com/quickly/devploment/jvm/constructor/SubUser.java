package com.quickly.devploment.jvm.constructor;

/**
 * @Author lidengjin
 * @Date 2020/7/3 4:27 下午
 * @Version 1.0
 */
public class SubUser extends SuperUser{

	static {
		System.out.println("SubUser static ");
	}

	public SubUser() {
		System.out.println("SubUser constructor ");
	}
}
