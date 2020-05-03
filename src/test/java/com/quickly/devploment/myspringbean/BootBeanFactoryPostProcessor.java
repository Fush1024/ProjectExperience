package com.quickly.devploment.myspringbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @ClassName Mys
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/24 11:26
 * @Version V-1.0
 **/
public class BootBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	public BootBeanFactoryPostProcessor(){
		super();
		System.out.println(this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName());
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory
			configurableListableBeanFactory) throws BeansException {
		BeanDefinition bd = configurableListableBeanFactory.getBeanDefinition("customer");
		PropertyValue[]pvs = bd.getPropertyValues().getPropertyValues();
		String aa = "";
		for(PropertyValue pv:pvs) {
			aa += pv.getName()+"="+pv.getValue();
		}
		System.out.println("【BootBeanFactoryPostProcessor】修改前的属性"+aa);

		bd.getPropertyValues().addPropertyValue(new PropertyValue("desc","这是在BootBeanFactoryPostProcessor设置的属性描述,已经不是这个初始的了"));
		bd.getPropertyValues().addPropertyValue(new PropertyValue("customerId","111111"));

		System.out.println(this.getClass().getSimpleName()+ ":" + new Exception().getStackTrace()[0].getMethodName());
	}

}
