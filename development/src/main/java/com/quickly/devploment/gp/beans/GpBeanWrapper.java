package com.quickly.devploment.gp.beans;

/**
 * @ClassName GpBeanWrapper
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/14 10:55
 * @Version V-1.0
 **/
public class GpBeanWrapper {
	private Object wrapperInstance;

	private Class<?> wrapperClass;


	public GpBeanWrapper(Object wrapperInstance) {
		this.wrapperInstance = wrapperInstance;
	}

	public Object getWrapperInstance() {
		return this.wrapperInstance;
	}



	public Class<?> getWrapperClass() {
		return this.wrapperInstance.getClass();
	}


}
