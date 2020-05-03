package com.quickly.devploment.supers;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName D1
 * @Description
 * @Author LiDengJin
 * @Date 2019/12/27 10:30
 * @Version V-1.0
 **/
public class D1 implements D {
	@Override
	public String getAll(A a) {
		return a.Call();
	}

	private String name;

	@Override
	public String toString() {
		return "D1{" + "name='" + name + '\'' + '}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		D1 d1 = new D1();
		System.out.println(d1.getAll(new B()));

		List<? super Number> list = new ArrayList<>();
		list.add(Integer.MAX_VALUE);
		Object object = list.get(0);

	}
}
