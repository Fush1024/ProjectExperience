package com.quickly.devploment.proxy;

import com.quickly.devploment.proxy.inteceprtor.TimerInteceptor;
import com.quickly.devploment.proxy.proxy.JDKProxy;
import com.quickly.devploment.proxy.proxy.JDKProxyAdvance;
import com.quickly.devploment.proxy.service.UserService;
import com.quickly.devploment.proxy.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author lidengjin
 * @Date 2020/5/25 10:59 上午
 * @Version 1.0
 */
@Slf4j
public class ProxyTest {

	@Test
	public void testJDkProxy() {
		UserService serviceInterface = new UserServiceImpl();
		JDKProxy jdkDynamicProxy = new JDKProxy();
		UserService proxy = (UserService) jdkDynamicProxy.getProxy(serviceInterface);
		System.out.println(proxy.sayHello("haha"));
		String json = "{\"name\": \"lidengjin\"}";
		String format = "[0-9]*";
	}

	@Test
	public void testProxyAdvance() {

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
				Runtime.getRuntime().availableProcessors() * 2, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

		//
		//		UserService serviceInterface = new UserServiceImpl();
		//		JDKProxyAdvance jDKDynamicProxyAdvanced = new JDKProxyAdvance();
		//		UserService proxy = (UserService) jDKDynamicProxyAdvanced.getProxy(serviceInterface, new TimerInteceptor());
		//		String s = proxy.sayHello("hello" + Thread.currentThread().getName());
		//		System.out.println(s);

		for (int i = 0; i < 10; i++) {
			threadPoolExecutor.execute(new MyTask(i));
		}

		threadPoolExecutor.shutdown();

	}

	class MyTask implements Runnable {

		private int taskNum;

		public MyTask(int num) {
			this.taskNum = num;
		}

		@Override
		public void run() {
			UserService serviceInterface = new UserServiceImpl();
			JDKProxyAdvance jDKDynamicProxyAdvanced = new JDKProxyAdvance();
			UserService proxy = (UserService) jDKDynamicProxyAdvanced.getProxy(serviceInterface, new TimerInteceptor());
			String s = proxy.sayHello("hello" + Thread.currentThread().getName());
			System.out.println(s);
		}

	}

}
