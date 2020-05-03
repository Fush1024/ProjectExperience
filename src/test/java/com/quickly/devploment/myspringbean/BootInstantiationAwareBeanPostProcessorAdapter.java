package com.quickly.devploment.myspringbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * @ClassName s
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/24 11:27
 * @Version V-1.0
 **/

public class BootInstantiationAwareBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter {

	public BootInstantiationAwareBeanPostProcessorAdapter() {
		super();
		System.out.println(this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName());
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
			throws BeansException {
		System.out.println(this.getClass().getSimpleName() + ":" + new Exception().getStackTrace()[0].getMethodName());
		if (bean instanceof Customer) {
			System.out.println(
					this.getClass().getSimpleName() + ":" + new Exception().getStackTrace()[0].getMethodName()+
							"--"+ ((Customer) bean).info());

		}
		return super.postProcessProperties(pvs, bean, beanName);
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println(this.getClass().getSimpleName() + ":" + new Exception().getStackTrace()[0].getMethodName() +
				"此时的beanClass=" + beanClass);
		//可以在此处改变此类对象，比如使用代理类
		return super.postProcessBeforeInstantiation(beanClass, beanName);
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		System.out.println(this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName()+"---bean"
		+ bean);
		return super.postProcessAfterInstantiation(bean, beanName);
	}

}
