package com.quickly.devploment.gp.config;

/**
 * @ClassName GpBeanDefinition
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/14 10:54
 * @Version V-1.0
 **/
public class GpBeanDefinition {
	private String beanClassName;
	private boolean lazyInit = false;
	private String factoryBeanName;

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}

	public boolean isLazyInit() {
		return lazyInit;
	}

	public void setLazyInit(boolean lazyInit) {
		this.lazyInit = lazyInit;
	}

	public String getFactoryBeanName() {
		return factoryBeanName;
	}

	public void setFactoryBeanName(String factoryBeanName) {
		this.factoryBeanName = factoryBeanName;
	}
}
