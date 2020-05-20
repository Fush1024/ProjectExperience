package com.quickly.devploment.supers;

import java.util.Arrays;

/**
 * @ClassName A
 * @Description
 * @Author LiDengJin
 * @Date 2019/12/27 10:28
 * @Version V-1.0
 **/
public class A implements Cloneable {

	private D d;

	private String[] arrays;

	public String[] getArrays() {
		return arrays;
	}

	public void setArrays(String[] arrays) {
		this.arrays = arrays;
	}

	public D getD() {
		return d;
	}

	public void setD(D d) {
		this.d = d;
	}

	public String Call() {
		return "a";
	}

	private String name;

	public A() {
	}

	public A(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "A{" + "d=" + d + ", arrays=" + Arrays.toString(arrays) + ", name='" + name + '\'' + '}';
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}


	/**
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		final A a1 = new A();
		a1.setName("zhangsan");
		D d = new D1();
		((D1) d).setName("DDD");

		a1.setD(d);
//		A a2 = new A();
//		a2.setName("zhangsan");
//
//		System.out.println(a1.equals(a2));

		String[] arrays = new String[]{"2","3"};
		a1.setArrays(arrays);
		System.out.println(a1.toString());

		A clone = (A) a1.clone();
		System.out.println(clone.toString());
	}
}
