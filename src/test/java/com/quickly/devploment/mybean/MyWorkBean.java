package com.quickly.devploment.mybean;

import org.junit.Test;

/**
 * @ClassName MyWorkBean
 * @Description
 * @Author LiDengJin
 * @Date 2019/11/10 11:45
 * @Version V-1.0
 **/
public class MyWorkBean {

	@Test
	public void testXueYuanDong() {
		String previous = MyEnum.DO_PROBLEMS.getMessage() + MyEnum.LISTEN_TO_MUSIC.getMessage();
		int previousScore =
				Integer.valueOf(MyEnum.DO_PROBLEMS.getCode()) + Integer.valueOf(MyEnum.LISTEN_TO_MUSIC.getCode());
		System.out.println("xueYuan_Dong previous work is :" + previous + " , the score is " + previousScore + ", so "
				+ MyEnum.DO_PROBLEMS.getMessage() + " score is " + MyEnum.DO_PROBLEMS.getCode() + " and "
				+ MyEnum.LISTEN_TO_MUSIC + " score is " + MyEnum.LISTEN_TO_MUSIC.getCode());

		String nowDays = MyEnum.DO_PROBLEMS.getMessage() + MyEnum.LISTEN_TO_MUSIC.getMessage() + MyEnum.CHAT_WITH_DENG
				.getMessage();
		int nowDaysScore =
				Integer.valueOf(MyEnum.DO_PROBLEMS.getCode()) + Integer.valueOf(MyEnum.LISTEN_TO_MUSIC.getCode())
						+ Integer.valueOf(MyEnum.CHAT_WITH_DENG.getCode());
		System.out.println("xueYuan_Dong nowDays work is :" + nowDays + " , the score is " + nowDaysScore + " ,so "
				+ MyEnum.CHAT_WITH_DENG + " score is " + MyEnum.CHAT_WITH_DENG.getCode());
	}
}
