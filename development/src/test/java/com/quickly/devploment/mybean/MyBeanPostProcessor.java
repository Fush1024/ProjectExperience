package com.quickly.devploment.mybean;

/**
 * @ClassName MyBeanPostProessor
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/23 16:00
 * @Version V-1.0
 **/

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {


	public MyBeanPostProcessor() {
		super();
		System.out.println("这是BeanPostProcessor实现类构造器！！");
		BeanInstantionUtils.beanLists.add("" + this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName());
	}

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		System.out.println("BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！");
		BeanInstantionUtils.beanLists.add("" + this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName());
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
		System.out.println("BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！");
		BeanInstantionUtils.beanLists.add("" + this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName());
		return arg0;
	}
}
