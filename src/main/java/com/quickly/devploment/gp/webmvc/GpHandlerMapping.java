package com.quickly.devploment.gp.webmvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @ClassName GpHandlerMapping
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/14 14:12
 * @Version V-1.0
 **/
public class GpHandlerMapping {
	private Object controller;
	private Method method;
	private Pattern pattern;


	public GpHandlerMapping(Object controller, Method method, Pattern pattern) {
		this.controller = controller;
		this.method = method;
		this.pattern = pattern;
	}

	public Object getController() {
		return controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
}
