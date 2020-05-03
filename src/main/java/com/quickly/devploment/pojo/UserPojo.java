package com.quickly.devploment.pojo;

/**
 * @ClassName UserPojo
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/16 10:48
 * @Version V-1.0
 **/
public class UserPojo {

	private int id;
	private String username;
	private String password;

	public UserPojo(String username, String password,int id) {
		this.username = username;
		this.password = password;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserPojo() {
	}

	@Override
	public String toString() {
		return "UserPojo{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + '}';
	}

	@Override
	public int hashCode() {
		return 2;
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
}
