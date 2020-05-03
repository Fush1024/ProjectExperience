package com.quickly.devploment.annotation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserServiceAspectJ
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/17 11:11
 * @Version V-1.0
 **/
@Aspect
@Component
public class UserServiceAspectJ {

	@Pointcut("execution(public * com.quickly.devploment..hello(..))")
	public void testAOP() {
	}

	@Before("testAOP()")
	public void before() {
		System.out.println("before TestAOP...");
	}

	@After("testAOP()")
	public void after() {
		System.out.println("after TestAOP ...");
	}

	@Around("testAOP()")
	public Object around(ProceedingJoinPoint p) {
		System.out.println("around before testAOP...");
		Object o = null;
		try {
			o = p.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("around after testAOP...");
		return o;
	}
}
