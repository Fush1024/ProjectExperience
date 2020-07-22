package com.quickly.devploment.groovy.repay;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author lidengjin
 * @Date 2020/7/22 9:26 上午
 * @Version 1.0
 */
@Slf4j
public class GroovyContext<T> {
	public static ConcurrentHashMap<String, Object> FORMULA_INSTANCE_MAP = new ConcurrentHashMap<>();

	public static  <T> T getInstance(FormulaEntity formula, Class<T> clazz) {
		GroovyClassLoader classLoader = new GroovyClassLoader();
		final String classFileName = new StringBuilder("GroovyScript").append("_").append(formula.getFormulaNo()).append("_").append(formula.getVersion())
				.append(".groovy").toString();
		Object formulaObject = FORMULA_INSTANCE_MAP.get(classFileName);
		if (formulaObject != null) {
			return clazz.cast(formulaObject);
		}
		Class groovyClass = classLoader.parseClass(formula.getGroovyScript(), classFileName);
		try {
			GroovyObject instance = (GroovyObject) groovyClass.newInstance();
			log.info("create {} instance success.", classFileName);
			;
			if (clazz.isInstance(instance)) {
				FORMULA_INSTANCE_MAP.put(classFileName, instance);
				return clazz.cast(instance);
			} else {
				log.error("Groovy 脚本类型定义不匹配");
			}
		} catch (InstantiationException | IllegalAccessException ex) {
			log.error("生成class文件失败", ex);
		}

		throw new RuntimeException("fail create groovy class");
	}
}
