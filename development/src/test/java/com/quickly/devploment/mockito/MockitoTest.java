package com.quickly.devploment.mockito;

import com.quickly.devploment.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author lidengjin
 * @Date 2020/7/23 4:28 下午
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MockitoTest {



	@Mock
	protected UserService userService;

	@Test
	public void testMockito(){
		String name = userService.getName("name");
		System.out.println(name);
//
		String hello = userService.hello();
		System.out.println(hello);
	}

	@Before
	public void setUp(){
		MockitoUtil.buildUserService(userService);
	}
}
