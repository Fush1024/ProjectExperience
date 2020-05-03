package com.quickly.devploment.gp.webmvc;

/**
 * @ClassName GpHandlerAdapter
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/14 14:13
 * @Version V-1.0
 **/
public class GpHandlerAdapter {
	public boolean supports(Object handler) {
		return (handler instanceof GpHandlerMapping);
	}


}
