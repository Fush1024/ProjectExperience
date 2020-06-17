package com.quickly.devploment.mq.ritbbitmq.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import static com.quickly.devploment.mq.ritbbitmq.config.BeanMap.beanMap;
import static com.quickly.devploment.mq.ritbbitmq.config.RibbitMQConfig.*;

/**
 * @Author lidengjin
 * @Date 2020/6/16 4:10 下午
 * @Version 1.0
 */
public class RibbitMQFactory {

	private ConnectionFactory connectionFactory;


	public RibbitMQFactory() {
		if (beanMap.containsKey("connectionFactory")) {
			this.connectionFactory = (ConnectionFactory) beanMap.get("connectionFactory");
			return;
		}
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(ADDRESS);
		connectionFactory.setPort(PORT);
		connectionFactory.setUsername(USERNAME);
		connectionFactory.setPassword(PASSWORD);
		beanMap.put("connectionFactory", connectionFactory);
		return;
	}

	private volatile AtomicInteger retryCount = new AtomicInteger(3);


	public Connection getConnection() throws IOException, TimeoutException {
		try {
			for (int i = 0; i < retryCount.get(); i++) {
				return this.connectionFactory.newConnection();
			}
		} catch (IOException e) {
			retryCount.decrementAndGet();
			if (retryCount.get() <= 0) {
				e.printStackTrace();
				throw e;
			}
			return this.getConnection();
		}
		return null;
	}


}
