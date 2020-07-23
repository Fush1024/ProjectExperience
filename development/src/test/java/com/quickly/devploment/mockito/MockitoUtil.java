package com.quickly.devploment.mockito;

import com.quickly.devploment.service.UserService;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * @Author lidengjin
 * @Date 2020/7/23 5:04 下午
 * @Version 1.0
 */
public class MockitoUtil {
	public static void buildUserService(UserService userService) {
		Mockito.when(userService.getName(Matchers.anyString())).thenReturn("mockito value").thenThrow(new RuntimeException("runtime exception"));
		Mockito.when(userService.hello()).thenReturn("mockito hello").thenThrow(new RuntimeException("runtime exception"));
	}
}
