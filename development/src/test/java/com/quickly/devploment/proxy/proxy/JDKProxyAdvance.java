package com.quickly.devploment.proxy.proxy;

import com.quickly.devploment.proxy.inteceprtor.MyInteceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author lidengjin
 * @Date 2020/5/25 11:15 上午
 * @Version 1.0
 */
public class JDKProxyAdvance implements InvocationHandler {
	Object target;

	MyInteceptor interceptor;

	public void setInterceptor(MyInteceptor interceptor) {
		this.interceptor = interceptor;
	}

	public Object getProxy(Object target, MyInteceptor interceptor) {
		this.interceptor=interceptor;
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		interceptor.doBefore();
		Object invoke = method.invoke(this.target, args);
		interceptor.doAfter();
		return invoke;
	}

}
