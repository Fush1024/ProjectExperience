package com.quickly.devploment.groovy.pojo;

import com.alibaba.fastjson.JSON;
import com.quickly.devploment.answer.repos.UserInfoDTO;

import java.util.Arrays;
import java.util.List;

/**
 * @Author lidengjin
 * @Date 2020/7/21 5:44 下午
 * @Version 1.0
 */
public class UserGroovy {
	private String name;
	private int age;
	private List<UserInfoDTO> userInfos;

	public UserGroovy() {
	}

	@Override
	public String toString() {
		return "UserGroovy{" + "name='" + name + '\'' + ", age=" + age + ", userInfos=" + userInfos + '}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<UserInfoDTO> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfoDTO> userInfos) {
		this.userInfos = userInfos;
	}

	public static void main(String[] args) {
		UserGroovy userGroovy = new UserGroovy();
		userGroovy.setAge(10);
		userGroovy.setName("name1");
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setUsername("username1");
		userInfoDTO.setPassword("password1");
		userInfoDTO.setClassroom("classroom1");
		UserInfoDTO userInfoDTO1 = new UserInfoDTO();
		userInfoDTO1.setUsername("username2");
		userInfoDTO1.setPassword("password2");
		userInfoDTO1.setClassroom("classroom2");
		userGroovy.setUserInfos(Arrays.asList(userInfoDTO,userInfoDTO1));
		String string = JSON.toJSONString(userGroovy);
		System.out.println(string);
	}
}
