package com.quickly.devploment.answer.repos;

import java.util.List;

/**
 * @Author lidengjin
 * @Date 2020/7/2 3:58 下午
 * @Version 1.0
 */
public class UserBaseDTO {
	private String name;
	private String password;
	private List<UserInfoDTO> userInfoDTOS;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserInfoDTO> getUserInfoDTOS() {
		return userInfoDTOS;
	}

	public void setUserInfoDTOS(List<UserInfoDTO> userInfoDTOS) {
		this.userInfoDTOS = userInfoDTOS;
	}

	public UserBaseDTO() {
	}
}
