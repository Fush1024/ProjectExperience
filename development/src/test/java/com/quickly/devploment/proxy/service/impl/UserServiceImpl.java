package com.quickly.devploment.proxy.service.impl;

import com.quickly.devploment.proxy.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author lidengjin
 * @Date 2020/5/25 10:56 上午
 * @Version 1.0
 */
@Slf4j
public class UserServiceImpl implements UserService
{
	@Override
	public String sayHello(String hello) {
		log.info("hello , invoke " + hello);
		return "hello ,i'm impl : " + hello;
	}

}
