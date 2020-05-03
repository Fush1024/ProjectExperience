package com.quickly.devploment;

import com.quickly.devploment.mybean.BeanInstantionUtils;
import com.quickly.devploment.mybean.Person;
import com.quickly.devploment.myspringbean.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevplomentApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("现在开始初始化容器");

		ApplicationContext factory = new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("容器初始化成功");
		//得到Preson，并使用
		Person person = factory.getBean("person", Person.class);
		System.out.println(person);

		System.out.println("现在开始关闭容器！");
		((ClassPathXmlApplicationContext) factory).registerShutdownHook();

		BeanInstantionUtils.beanLists.forEach(s->{
			System.out.println("------->>>>"+s);
		});
	}

	@Test
	public void test(){
		System.out.println("开始初始化容器");

		ApplicationContext factory = new ClassPathXmlApplicationContext("mysprings.xml");
		System.out.println("容器初始化成功");

		Customer customer = factory.getBean("customer",Customer.class);
		System.out.println(customer.info());

		System.out.println("开始关闭容器！");
		((ClassPathXmlApplicationContext)factory).registerShutdownHook();
	}
}

