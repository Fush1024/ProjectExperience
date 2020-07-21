package com.quickly.devploment.groovy.read;

import com.alibaba.fastjson.JSONObject;
import com.quickly.devploment.groovy.pojo.UserGroovy;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Author lidengjin
 * @Date 2020/7/21 5:49 下午
 * @Version 1.0
 */
public class GroovyTest {

	public static void main(String[] args) {
		GroovyObject groovyObject = null;
		try {
			groovyObject = (GroovyObject) GroovyClassLoader.class.getClassLoader()
					.loadClass("com.quickly.devploment.groovy.pojo.GroovyUserData").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String filePath = "/Users/lidengjin/study/sourceofgithub/mygithub/ProjectExperience/development/src/test/resources/groovy/groovy_user.txt";
		Object[] array = new Object[]{filePath};
		List<String> readLineToList = (List<String>) groovyObject.invokeMethod("readLineToList", array);
		readLineToList.stream().forEach(line -> {
			if (StringUtils.startsWith(line, "--")) {
				return;
			}
			UserGroovy userGroovy = JSONObject.parseObject(line, UserGroovy.class);
			System.out.println(userGroovy.toString());
		});
	}
}
