package com.quickly.devploment.gp.config;

/**
 * @ClassName GpBeanPostProcessor
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/14 13:48
 * @Version V-1.0
 **/
public class GpBeanPostProcessor {
	public Object postProcessBeforeInitialization(Object bean, String name) throws Exception {
		return bean;
	}


	public Object postProcessAfterInitialization(Object bean, String name) throws Exception {
		return bean;
	}
}
