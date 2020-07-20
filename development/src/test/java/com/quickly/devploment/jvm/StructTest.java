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
		 * 				new 一个对象时，首先去元空间中的常量池中 定位到类的符号引用，并且检查该类是否被加载，解析，初始化过。如果没有那么就需要进行类加载过程。
		 * 				new 对象的时候，需要分配内存，1 指针碰撞 ，2 空闲列表，和垃圾收集算法有关（是否有空间压缩整理的能力），内存连续---指针碰撞，内存不连续---空闲列表
		 * 				当频繁的创建对象是，可能会引起并发问题，比如，userPojo 分配了空间，但是指针还没指过去，可能会造成并发问题，那么解决两种：1 虚拟机使用 cas + 失败重试
		 * 					2 使用TLAB 线程内部分配空间。开始的时候，给线程划分内存区域。
		 * 				创建完毕之后，就需要对该内存区域进行初始化 零值。保证了对象的实例字段 在Java代码中可以不赋初始值 就可以使用。
		 * 				对象头中保存了，哈希码，类的元数据信息，GC 年龄信息等，以及偏向锁 等。
		 * 				但是此时从虚拟机的角度看，是已经对象产生了，但是 对象创建的时候，这个构造函数还没有执行，class 文件中的init 方法也没有执行，那么字段都是默认的零值。
		 * 				对象{
		 * 					1 对象头 mark word
		 * 					2 实例数据 各种类型信息 字段信息，以及父类信息。
		 * 					3 对齐填充 占位符作用，任何对象的大小，都必须是 8字节的整数倍。如果不是的话，需要填充。
		 * 				}
		 * 				reference 如何寻找到堆中的对象？ 1 句柄，2 直接指针，1---堆中存在句柄池，然后定位访问到对象。2 直接指针 ， 速度快
		 * 			jdk1.8 以及之后就是元空间  之前是 永久代---方法区 ，这个Class 的类，属性，方法名 变量等，放在元空间, local 放在元空间的运行时常量池中，
		 * 	线程独享区域
		 * 		本地方法栈 执行本地的方法
		 * 		虚拟机栈  ，里面有局部变量表，栈帧，动态链接等。
		 * 		程序计数器 进行指令的切换
		 *
		 */

		/**
		 *  java 虚拟机栈 ，--> 栈帧  {
		 *     局部变量表{
		 *         编译期可知的 基本数据类型，引用，addressReturn ,数据以变量槽的形式存在，long double 两个变量槽
		 *     }
		 *     操作数栈，动态链接，方法出口
		 *  }
		 *  堆 { 几乎所有的对象 以及数组 } ---> 栈上逃逸 以及标量替换
		 *  	划分出 线程私有的分配缓冲区 TLAB ，提升对象分配时的效率。
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

	@Test
	public void testIntern() {
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
	}


}
