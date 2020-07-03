package com.dj.spring.bean.pojo;

/**
 * @Author lidengjin
 * @Date 2020/7/3 3:09 下午
 * @Version 1.0
 */
public class User {

	private String name;

	public String sayHello() {
		return this.name + "say hello ";
	}

	private String getName() {
		return "user";
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" + "name='" + name + '\'' + '}';
	}
}
