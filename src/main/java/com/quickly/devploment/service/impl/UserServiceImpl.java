package com.quickly.devploment.service.impl;

import com.quickly.devploment.mapper.UserMapper;
import com.quickly.devploment.pojo.UserPojo;
import com.quickly.devploment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author LiDengJin
 * @Date 2019/9/30 11:12
 * @Version V-1.0
 **/
@Service(value = "userServiceImpll")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 *  获取用户名称
	 * @param name
	 * @return
	 */
	@Override
	public String getName(String name) {
		synchronized (name) {
			if (name == null) {
				name = "default";
			}
		}
		return name +"___"+ System.currentTimeMillis();
	}

	@Override
	@Transactional
	public String saveUser(String name) {
//		UserMapper userMapper = MyApplicationContext.getBean("userMapper");
//		UserPojo userPojo = getUserPojo(name);
		UserPojo userPojo = new UserPojo();
		userPojo.setUsername(name+"11_Exception" );
		userPojo.setPassword(name+"22_Exception" );
		userMapper.saveUser(userPojo);
		throw new RuntimeException("这是一个异常");
		//		System.out.println(userPojo.getId());
		//		return JSON.toJSONString(userPojo);
	}

//	@Transactional
	public UserPojo getUserPojo(String name) {
		UserPojo userPojo = new UserPojo();
		userPojo.setPassword(name + "11");
		userPojo.setUsername(name + "22");
//		UserMapper userMapper = MyApplicationContext.getBean("userMapper");
		userMapper.saveUser(userPojo);
		return userPojo;
	}

	@Override
	public String hello(){
		return "hello"+ new Random().nextInt(10);
	}
}
