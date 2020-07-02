package com.quickly.devploment.answer.repos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author lidengjin
 * @Date 2020/7/2 4:00 下午
 * @Version 1.0
 */
public class UserResultTest {
	public static ResultData<UserBaseDTO> data;

	@Test
	public void testUser() {
		System.out.println("data ---> " + data);
		String string = JSONObject.toJSONString(data);

		System.out.println("string ---->" + string);
		ResultData resultData = JSON.parseObject(string, ResultData.class);
		System.out.println("parse --->" + resultData);
	}

	@Before
	public void before() {
		data = new ResultData<>();
		data.setCode(100);
		data.setMsg("msg");
		UserBaseDTO userBaseDTO = new UserBaseDTO();
		userBaseDTO.setName("baseName");
		userBaseDTO.setPassword("basePassword");

		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setClassroom("classroom1");
		userInfoDTO.setPassword("infopassword");
		userInfoDTO.setUsername("infousername");
		UserInfoDTO userInfoDTO1 = new UserInfoDTO();
		userInfoDTO1.setClassroom("classroom1");
		userInfoDTO1.setPassword("infopassword");
		userInfoDTO1.setUsername("infousername");
		userBaseDTO.setUserInfoDTOS(Arrays.asList(userInfoDTO, userInfoDTO1));

		data.setData(userBaseDTO);
	}
}
