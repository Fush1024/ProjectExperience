package com.quickly.devploment.myspringbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * @ClassName My
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/24 11:25
 * @Version V-1.0
 **/
public class Customer implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

	private Long customerId;
	private String customerName;
	private String desc;
	private BeanFactory beanFactory;
	private String beanName;


	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
		System.out.println(this.getClass().getSimpleName() + ":" + new Exception().getStackTrace()[0].getMethodName());
	}

	@Override
	public void setBeanName(String s) {
		this.beanName = s;
		System.out.println(this.getClass().getSimpleName() + ":" + new Exception().getStackTrace()[0].getMethodName());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(this.getClass().getSimpleName() + ":" + new Exception().getStackTrace()[0].getMethodName());
	}

	@Override
	public void destroy() throws Exception {
		System.out.println(this.getClass().getSimpleName() + ":" + new Exception().getStackTrace()[0].getMethodName());
	}


	public void initCustomer() throws Exception {
		System.out.println(this.getClass().getSimpleName() + ":" + new Exception().getStackTrace()[0].getMethodName());
	}


	public void destroyCustomer() throws Exception {
		System.out.println(this.getClass().getSimpleName() + ":" + new Exception().getStackTrace()[0].getMethodName());
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String info() {
		return this.getCustomerId() + "/" + this.getCustomerName() + "/" + this.getDesc();
	}

	@Override
	public String toString() {
		return "Customer{" + "customerId=" + customerId + ", customerName='" + customerName + '\'' + ", desc='" + desc
				+ '\'' + ", beanFactory=" + beanFactory + ", beanName='" + beanName + '\'' + '}';
	}
}
