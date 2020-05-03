package com.quickly.devploment.gp.beans;

import com.quickly.devploment.gp.config.GpBeanDefinition;
import com.quickly.devploment.gp.context.GPAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName GPDefaultlistableBeanFactory
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/14 10:58
 * @Version V-1.0
 **/
public class GPDefaultlistableBeanFactory extends GPAbstractApplicationContext {
	// 存储Bean 定义的
	protected final Map<String, GpBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String,GpBeanDefinition>();

}
