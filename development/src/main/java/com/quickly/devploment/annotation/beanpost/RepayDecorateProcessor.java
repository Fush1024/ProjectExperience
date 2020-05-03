package com.quickly.devploment.annotation.beanpost;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * @ClassName Ra
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/23 11:55
 * @Version V-1.0
 **/
@Component
public class RepayDecorateProcessor implements BeanFactoryPostProcessor, PriorityOrdered {

	@Bean
	public BeanPostHandler getBeanHandler() {
		return new BeanPostHandler();
	}


	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)
			throws BeansException {
		System.out.println("hahahah  =----=-");
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}

