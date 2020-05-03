package com.quickly.devploment.gp.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName GpBeanDefinitionReader
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/14 11:06
 * @Version V-1.0
 **/
public class GpBeanDefinitionReader {
	private List<String> registryBeanClasses = new ArrayList<String>();

	private Properties config = new Properties();

	private final String SCAN_PACKAGE = "scanPackage";

	public GpBeanDefinitionReader(String... locations) {
		try (InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream(locations[0].replace("classpath:", ""))) {
			config.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		doScanner(config.getProperty(SCAN_PACKAGE));
	}

	private void doScanner(String scanPackage) {
		URL resource = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
		File classPath = new File(resource.getFile()); for (File file : classPath.listFiles()) {
			if (file.isDirectory()) {
				doScanner(scanPackage+"." + file.getName());
			}else {
				if(!file.getName().endsWith(".class")){continue;}
				String className = (scanPackage + "." +file.getName().replace(".class",""));
				registryBeanClasses.add(className);
			}
		}
	}

	public Properties getConfig(){
		return this.config;
	}

	public List<GpBeanDefinition> loadBeanDefinitions(){
		List<GpBeanDefinition> result = new ArrayList<GpBeanDefinition>();
		/*
		* ...
		* 1 省略转换过程。。。。
		*
		* */
		return result;
	}
}
