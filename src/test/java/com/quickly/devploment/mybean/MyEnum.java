package com.quickly.devploment.mybean;

/**
 * @ClassName MyEnum
 * @Description
 * @Author LiDengJin
 * @Date 2019/11/10 11:46
 * @Version V-1.0
 **/
public enum MyEnum {
	DO_PROBLEMS("做题", "100"),
	LISTEN_TO_MUSIC("听音乐", "90"),
	CHAT_WITH_DENG("和帅登聊天", "36");

	private String code;

	private String message;

	private MyEnum(String message, String code) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static MyEnum getEnum(String value) {
		MyEnum[] crc = MyEnum.values();
		for (int i = 0; i < crc.length; i++) {
			if (crc[i].getCode().equals(value)) {
				return crc[i];
			}
		}
		return null;
	}
}
