package com.dj.spring.bean.pojo.test;

import com.dj.spring.bean.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @Author lidengjin
 * @Date 2020/7/3 3:14 下午
 * @Version 1.0
 */
public class POJOTest {

	@Test
	public void testPojo(){
//		// 基于xml ，applicationContext >> BeanFactory
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-bean.xml");
		User bean = (User) beanFactory.getBean("user");


//		System.out.println(bean);
//		System.out.println(bean.sayHello());

	}

}
