package com.quickly.devploment.gp.core;

/**
 * @ClassName GpBeanFactory
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/14 10:52
 * @Version V-1.0
 **/
public interface GpBeanFactory {
	Object getBean(String name) throws Exception;

	public Object getBean(Class<?> beanClass) throws Exception;
}
