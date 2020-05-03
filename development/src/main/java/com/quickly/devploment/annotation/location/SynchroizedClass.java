package com.quickly.devploment.annotation.location;

import com.quickly.devploment.annotation.TestAnnotationConfig;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName SynchroizedClass
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/17 16:02
 * @Version V-1.0
 **/
public class SynchroizedClass extends TestAnnotationConfig {
	Object obj1 = new Object();
	Object obj2 = new Object();
	public void fun1() {

		synchronized (obj1) {
			fun2();
		}
	}


	public void fun2() {

		synchronized (obj2) {
			while (true) {
				System.out.println("Synchroized");
			}
		}

	}

	public static void main(String[] args) {
//		SynchroizedClass synchroizedClass = new SynchroizedClass();
//		synchroizedClass.fun1();


		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource(""));
		xmlBeanFactory.getBean("a");


		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("xxx.xml");
		Object xxxClass = classPathXmlApplicationContext.getBean("xxxClass");


		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = "2019-10-18";
		String date2 = "2019-10-18";
		try {
		    Date parse = simpleDateFormat.parse(date1);
			Date parse1 = simpleDateFormat.parse(date2);
			System.out.println(parse.after(parse1));

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testAddDays(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH,22);
		Date time = c.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(time));
	}
}
