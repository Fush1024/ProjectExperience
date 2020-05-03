package com.quickly.devploment.keyeat;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserA
 * @Description
 * @Author LiDengJin
 * @Date 2019/11/22 18:37
 * @Version V-1.0
 **/
public class UserA {
	public UserA() {
//		try {
//			Thread.sleep(0,0);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	public UserA(List<UserB> users) {
		this.users = users;
	}

	List<UserB> users;

	@Override
	public String toString() {
		return "UserA{" + "users=" + users + '}';
	}

	public List<UserB> getUsers() {
		return users;
	}

	public void setUsers(List<UserB> users) {
		this.users = users;
	}

	class UserB {
		private Integer stage;

		@Override
		public String toString() {
			return "UserB{" + "stage=" + stage + '}';
		}

		public Integer getStage() {
			return stage;
		}

		public void setStage(Integer stage) {
			this.stage = stage;
		}
	}

	public static void main(String[] args) {
		UserA userA = new UserA();
		UserB userB = userA.new UserB();
		userB.setStage(2);
		UserB userB1 = userA.new UserB();
		userB1.setStage(4);

		UserB userB2 = userA.new UserB();
		userB2.setStage(1);
		List<UserB> userBS = new ArrayList<>();
		userBS.add(userB);
		userBS.add(userB1);
		userBS.add(userB2);
		userA.setUsers(userBS);
		String s = JSONObject.toJSONString(userA);
		System.out.println(s);

		List<UserB> collect = userA.getUsers().stream().sorted(Comparator.comparing(UserB::getStage).reversed())
				.collect(Collectors.toList());
		String co = JSONObject.toJSONString(collect);
		System.out.println(co);
		String after = JSONObject.toJSONString(userA);
		System.out.println(after);
	}
}
