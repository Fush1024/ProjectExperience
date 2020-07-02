package com.quickly.devploment.jvm;

import org.junit.Test;

/**
 * @Author lidengjin
 * @Date 2020/6/30 2:57 下午
 * @Version 1.0
 */
public class StructTest {
	public static final String local = "local";
	@Test
	public void testStruct(){
		/*
		 *  java 虚拟机
		 * 		运行时数据区
		 * 线程共享区域
		 * 			堆 这个对象的实例
		 * 				new 对象的时候，需要分配内存，1 指针碰撞 ，2 空闲列表，和垃圾收集算法有关（是否有空间压缩整理的能力），内存连续---指针碰撞，内存不连续---空闲列表
		 * 				当频繁的创建对象是，可能会引起并发问题，比如，userPojo 分配了空间，但是指针还没指过去，可能会造成并发问题，那么解决两种：1 虚拟机使用 cas + 失败重试
		 * 					2 使用TLAB 线程内部分配空间。开始的时候，给线程划分内存区域。
		 * 			jdk1.8 以及之后就是元空间  之前是 永久代---方法区 ，这个Class 的类，属性，方法名 变量等，放在元空间, local 放在元空间的运行时常量池中，
		 * 	线程独享区域
		 * 		本地方法栈 执行本地的方法
		 * 		虚拟机栈  ，里面有局部变量表，栈帧，动态链接等。
		 * 		程序计数器 进行指令的切换
		 *
		 */

		UserPojo userPojo = new UserPojo();
		UserPojo userPojo1 = new UserPojo();
		userPojo.setName("name");

	}

	class UserPojo{
		private String name;

		public UserPojo() {
		}

		public UserPojo(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}


}
