package com.quickly.devploment.answer.repos;

/**
 * @Author lidengjin
 * @Date 2020/7/2 3:58 下午
 * @Version 1.0
 */
public class UserInfoDTO {
	private String username;
	private String password;
	private String classroom;

	public UserInfoDTO() {
	}

	@Override
	public String toString() {
		return "UserInfoDTO{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", classroom='"
				+ classroom + '\'' + '}';
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
}
