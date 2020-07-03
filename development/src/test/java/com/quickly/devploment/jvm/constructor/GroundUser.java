package com.quickly.devploment.jvm.constructor;

/**
 * @Author lidengjin
 * @Date 2020/7/3 4:29 下午
 * @Version 1.0
 */
public class GroundUser extends SubUser{


	static {
		System.out.println("Ground static ");

	}

	public GroundUser() {
		System.out.println("Ground constructor ");
	}
}
