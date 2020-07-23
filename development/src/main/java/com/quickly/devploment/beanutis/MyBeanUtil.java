package com.quickly.devploment.beanutis;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author lidengjin
 * @Date 2020/7/23 5:49 下午
 * @Version 1.0
 */
@Component
public class MyBeanUtil implements ApplicationContextAware {

	private static ApplicationContext context;


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (context == null) {
			context = applicationContext;
		}
	}

	public static ApplicationContext getContext(){
		return context;
	}
}
