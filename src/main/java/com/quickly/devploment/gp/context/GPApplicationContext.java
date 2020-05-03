package com.quickly.devploment.gp.context;

import com.quickly.devploment.gp.annotation.GpAutowire;
import com.quickly.devploment.gp.annotation.GpController;
import com.quickly.devploment.gp.annotation.GpService;
import com.quickly.devploment.gp.beans.GPDefaultlistableBeanFactory;
import com.quickly.devploment.gp.beans.GpBeanWrapper;
import com.quickly.devploment.gp.config.GpBeanDefinition;
import com.quickly.devploment.gp.config.GpBeanDefinitionReader;
import com.quickly.devploment.gp.config.GpBeanPostProcessor;
import com.quickly.devploment.gp.core.GpBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName GPApplicationContext
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/14 11:00
 * @Version V-1.0
 **/
public class GPApplicationContext extends GPDefaultlistableBeanFactory implements GpBeanFactory {

	private String[] configLoactions;
	private GpBeanDefinitionReader reader;

	// 单例Ioc bean 缓存
	private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();

	// IOC 容器
	private Map<String, GpBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String, GpBeanWrapper>();


	public GPApplicationContext(String... configLoactions) {
		this.configLoactions = configLoactions; try {
			refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void refresh() throws Exception {
		// 定位配置文件
		reader = new GpBeanDefinitionReader(this.configLoactions);

		// 转换成 对应的 BeanDefinition
		List<GpBeanDefinition> gpBeanDefinitions = reader.loadBeanDefinitions();

		// 注册 放到 Ioc
		doRegisterBeanDefinition(gpBeanDefinitions);

		// 把不是延迟加载的类初始化
		doAutoWrited();

	}

	private void doRegisterBeanDefinition(List<GpBeanDefinition> beanDefinitions) throws Exception {
		for (GpBeanDefinition beanDefinition : beanDefinitions) {
			if (super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
				throw new Exception("The " + beanDefinition.getFactoryBeanName() + "is exists !");
			} super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
		}
	}


	private void doAutoWrited() {
		for (Map.Entry<String, GpBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
			String key = beanDefinitionEntry.getKey(); if (!beanDefinitionEntry.getValue().isLazyInit()) {
				try {
					getBean(key);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String[] getBeanDefinitionNames() {
		return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
	}

	@Override
	public Object getBean(String name) throws Exception {
		//
		GpBeanDefinition gpBeanDefinition = super.beanDefinitionMap.get(name);

		try {

			// 生成通知事件
			GpBeanPostProcessor gpBeanPostProcessor = new GpBeanPostProcessor();

			Object instance = instantiateBean(gpBeanDefinition); if (instance == null) {return null;}
			gpBeanPostProcessor.postProcessBeforeInitialization(instance, name);
			GpBeanWrapper gpBeanWrapper = new GpBeanWrapper(instance);

			this.factoryBeanInstanceCache.put(name, gpBeanWrapper);

			gpBeanPostProcessor.postProcessAfterInitialization(instance, name);

			populateBean(name, instance);

		} catch (Exception e) {
		} return null;
	}


	private void populateBean(String className, Object instance) {
		Class aClass = instance.getClass();
		if (!(aClass.isAnnotationPresent(GpController.class) || aClass.isAnnotationPresent(GpService.class))) {
			return;
		} Field[] fields = aClass.getDeclaredFields(); for (Field field : fields) {
			if (!field.isAnnotationPresent(GpAutowire.class)) {continue;}
			GpAutowire annotation = field.getAnnotation(GpAutowire.class);
			String autowireBeanName = annotation.value().trim(); if ("".equals(autowireBeanName)) {
				autowireBeanName = field.getType().getName();
			} field.setAccessible(true);

			try {
				field.set(instance, this.factoryBeanInstanceCache.get(autowireBeanName).getWrapperInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}



		}


	}

	private Object instantiateBean(GpBeanDefinition beanDefinition) {
		Object instance = null; String className = beanDefinition.getBeanClassName(); try {
			if (this.factoryBeanObjectCache.containsKey(className)) {
				instance = this.factoryBeanObjectCache.get(className);
			} else {
				Class<?> aClass = Class.forName(className); instance = aClass.newInstance();
				this.factoryBeanObjectCache.put(beanDefinition.getFactoryBeanName(), instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} return instance;
	}

	@Override
	public Object getBean(Class<?> beanClass) throws Exception {
		return null;
	}

	public int getBeanDefinitionCount() {
		return this.beanDefinitionMap.size();
	}

	public Properties getConfig() {
		return this.reader.getConfig();
	}
}
