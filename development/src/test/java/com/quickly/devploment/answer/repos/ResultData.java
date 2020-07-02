package com.quickly.devploment.answer.repos;

/**
 * @Author lidengjin
 * @Date 2020/7/2 3:57 下午
 * @Version 1.0
 */
public class ResultData<T> {
	private String msg;
	private Integer code;
	T data;

	public ResultData() {
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultData{" + "msg='" + msg + '\'' + ", code=" + code + ", data=" + data + '}';
	}
}
