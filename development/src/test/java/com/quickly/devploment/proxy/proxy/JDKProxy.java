package com.quickly.devploment.proxy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author lidengjin
 * @Date 2020/5/25 10:57 上午
 * @Version 1.0
 */
public class JDKProxy implements InvocationHandler {

	Object target;

	public Object getProxy(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}




	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("======JDKDynamicProxy 调用之前=======");
		Object invoke = method.invoke(this.target, args);
		System.out.println("======JDKDynamicProxy 调用之后=======");
		return invoke;

	}
}
