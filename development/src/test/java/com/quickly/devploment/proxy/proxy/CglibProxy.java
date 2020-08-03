package com.quickly.devploment.proxy.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author lidengjin
 * @Date 2020/7/31 5:00 下午
 * @Version 1.0
 */
public class CglibProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
		System.out.println(method.getName());
		Object o1 = methodProxy.invokeSuper(o, args);
		System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
		return o1;
	}
	/*   https://blog.csdn.net/riemann_/article/details/86849078
 	1 描述Java动态代理的几种实现方式，分别说出相应的优缺点
		jdk动态代理是由java内部的反射机制来实现的，cglib动态代理底层则是借助asm来实现的
		反射机制在生成类的过程中比较高效，而asm在生成类之后的相关执行过程中比较高效（可以通过将asm生成的类进行缓存，这样解决asm生成类过程低效问题）。还有一点必须注意：jdk动态代理的应用前提，必须是目标类基于统一的接口。如果没有上述前提，jdk动态代理不能应用。由此可以看出，jdk动态代理有一定的局限性，cglib这种第三方类库实现的动态代理应用更加广泛，且在效率上更有优势。
		jdk动态代理是jdk原生就支持的一种代理方式，它的实现原理，就是通过让target类和代理类实现同一接口，代理类持有target对象，来达到方法拦截的作用，这样通过接口的方式有两个弊端，一个是必须保证target类有接口，第二个是如果想要对target类的方法进行代理拦截，那么就要保证这些方法都要在接口中声明，实现上略微有点限制。
		Cglib是一个优秀的动态代理框架，它的底层使用ASM在内存中动态的生成被代理类的子类，使用CGLIB即使代理类没有实现任何接口也可以实现动态代理功能。CGLIB具有简单易用，它的运行速度要远远快于JDK的Proxy动态代理：
		Cglib有两种可选方式，继承和引用。第一种是基于继承实现的动态代理，所以可以直接通过super调用target方法，但是这种方式在spring中是不支持的，因为这样的话，这个target对象就不能被spring所管理，所以cglib还是才用类似jdk的方式，通过持有target对象来达到拦截方法的效果。

	 */

	/*
	1 泛型解决了什么问题？ https://www.cnblogs.com/huansky/p/8043149.html
		解决了程序向下转型 容易造成的 ClassCastException ,泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。这种参数类型可以用在类、接口和方法的创建中，分别称为泛型类、泛型接口、泛型方法
		泛型的好处是在编译的时候检查类型安全，并且所有的强制转换都是自动和隐式的，提高代码的重用率。
	 */

	/*
	2 内存溢出 怎么解决？
		1 Java 堆溢出 ，可能是 jvm 设置的内存太小，对象所需的内存 太大。2 流量/数据的峰值，用户数量突然激增，并超过了预期的阈值， 解决办法，调整 -xms -xmx 参数，使用压力测试来调整，从而达到最优值
			尽量避免大对象的申请，想文件上传，大批量从数据库读取数据，尽量分块或者分批处理。 尽量提高一次请求的执行速度，垃圾回收越早越好。否则大量的并发来了的时候，再来新的请求就无法分配内存了。容易造成系统的雪崩。
		2 java 堆内存泄漏
			1 当对象不再被引用程序使用，但是垃圾回收器无法识别 无法回收的场景，解决的话 重写 equals 方法。
		3 垃圾回收超时内存溢出
			1 当应用程序耗尽所有的可用内存的时候，GC 开销限制超过了 错误，而GC多次未能清除，当JVM 花费大量的时间执行 GC ，而收益甚微，而一旦整个GC 过程超过限制，便会触发错误。默认JVM 配置GC 时间 超过98%，回收内存低于2%。
				要减少对象的生命周期，尽量能快的进行垃圾回收
		4 Metaspace 内存溢出
			1 元空间的溢出，出现这个问题的原因是 系统的代码非常多，或者引用的第三方包 非常多，或者动态代码生成类家在等方式，导致元空间的内存占用很大。
				1 优化参数配置，初始空间大小，达到改值就会出发垃圾收集进行类型写在，并且进行动态修改，如果释放了大量的空间，就适当的降低该值，如果释放了很少的空间，就提高该值。
				2 慎用第三方包
	 */
}
