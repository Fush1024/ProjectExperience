package com.quickly.devploment.controller;

import com.quickly.devploment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Description
 * @Author LiDengJin
 * @Date 2019/9/30 11:11
 * @Version V-1.0
 **/
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Resource(name = "userServiceImpll")
	private UserService userService;

	/**
	 *  获取用户名称
	 * @param name
	 * @return
	 */
	@RequestMapping("/getusername")
	@ResponseBody
	public String getUserName(String name){
		log.info("访问了 "+name);
		return userService.getName(name);
	}

	@RequestMapping("/insertuser")
	@ResponseBody
	public String saveUser(String name){
		return userService.saveUser(name);
	}
}
