package com.quickly.devploment.gp.webmvc;

import java.util.Map;

/**
 * @ClassName GpModelAndView
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/14 14:14
 * @Version V-1.0
 **/
public class GpModelAndView {
	private String viewName;
	private Map<String,?> model;

	public GpModelAndView(String viewName) {
		this(viewName, null);
	}

	public GpModelAndView(String viewName, Map<String, ?> model) {
		this.viewName = viewName;
		this.model = model;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public Map<String, ?> getModel() {
		return model;
	}

	public void setModel(Map<String, ?> model) {
		this.model = model;
	}
}
