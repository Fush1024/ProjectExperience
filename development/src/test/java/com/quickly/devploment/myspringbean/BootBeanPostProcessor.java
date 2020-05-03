package com.quickly.devploment.myspringbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @ClassName Bo
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/24 11:27
 * @Version V-1.0
 **/

public class BootBeanPostProcessor implements BeanPostProcessor {

	public BootBeanPostProcessor(){
		super();
		System.out.println(this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName());
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(bean  instanceof Customer) {
			System.out.println(this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName() +"---bean ---Customer" +bean);
		}
		System.out.println(this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName() + "----bean"+bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean  instanceof Customer) {
			System.out.println(this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName() +"---Bean--Customer" +bean );
		}
		System.out.println(this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName() +"---bean"+bean);
		return bean;
	}
}
