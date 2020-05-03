package com.quickly.devploment.mybean;

/**
 * @ClassName My
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/23 16:02
 * @Version V-1.0
 **/

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

	public MyInstantiationAwareBeanPostProcessor() {
		super();
		System.out.println("这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！");
		BeanInstantionUtils.beanLists.add("" + this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName());
	}

	// 接口方法、实例化Bean之前调用
	@Override
	public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
		System.out.println("InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法");
		BeanInstantionUtils.beanLists.add("" + this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName());
		return null;
	}

	// 接口方法、实例化Bean之后调用
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法");
		BeanInstantionUtils.beanLists.add("" + this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName());
		return bean;
	}

	// 接口方法、设置某个属性时调用
	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean,
			String beanName) throws BeansException {
		System.out.println("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法");
		BeanInstantionUtils.beanLists.add("" + this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName());
		return pvs;
	}
}
