package com.quickly.devploment.annotation;

import com.quickly.devploment.service.UserService;
import com.quickly.devploment.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ClassName TestAnnotationConfig
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/17 10:31
 * @Version V-1.0
 **/
@Configuration
@EnableAspectJAutoProxy
public class TestAnnotationConfig {
	@Bean
	public UserService userService(){
		return new UserServiceImpl();
	}
}
