package com.quickly.devploment.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lidengjin
 * @Date 2020/7/16 5:32 下午
 * @Version 1.0
 */
public class LambdaTest {
	class User{
		String name;
		int age ;

		public User() {
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
	}

	@Test
	public void testAnyMatch(){
		List<User> list = new ArrayList<>();
		User user = new User();
		user.setAge(1);
		user.setName("1");

		User user1 = new User();
		user1.setAge(2);
		user1.setName("2");

		list.add(user1);
		list.add(user);

		if(list.stream().anyMatch(u->u.getAge() == 11)){
			System.out.println(1);
		}else {
			System.out.println(2);
		}
	}
}
