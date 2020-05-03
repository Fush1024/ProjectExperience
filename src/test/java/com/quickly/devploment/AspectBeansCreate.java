package com.quickly.devploment;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ClassName AspectBeansCreate
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/23 16:31
 * @Version V-1.0
 **/
@Aspect
@Component
public class AspectBeansCreate {
	@Pointcut("execution(public * com.quickly.devploment.mybean..*.*(..))")
	public void pointCut() { }

	@Before("pointCut()")
	public void before(JoinPoint joinPoint) {
		System.out.println("123123123");
	}
	@AfterReturning(pointcut = "pointCut()",returning = "o")
	public void afterResult(JoinPoint joinPoint, Object o){
		System.out.println("543234");
	}




}
