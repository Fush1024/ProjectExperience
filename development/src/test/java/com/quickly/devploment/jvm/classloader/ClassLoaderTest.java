package com.quickly.devploment.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author lidengjin
 * @Date 2020/7/21 2:56 下午
 * @Version 1.0
 */
public class ClassLoaderTest {
	public static void main(String[] args) throws Exception {
		ClassLoader myLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				try {
					String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
					InputStream is = getClass().getResourceAsStream(fileName);
					if (is == null) {
						return super.loadClass(name);
					}
					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name, b, 0, b.length);
				} catch (IOException e) {
					throw new ClassNotFoundException(name);
				}
			}
		};
		Object obj = myLoader.loadClass("com.quickly.devploment.jvm.classloader.ClassLoaderTest").newInstance();
		System.out.println(obj.getClass());
		// 因为类加载器不一样 所以两个类不一样。
		System.out.println(obj instanceof com.quickly.devploment.jvm.classloader.ClassLoaderTest);
	}
}
