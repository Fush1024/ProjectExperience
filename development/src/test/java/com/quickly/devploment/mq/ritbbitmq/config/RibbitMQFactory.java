package com.quickly.devploment.mq.ritbbitmq.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

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

	private final static Integer retryCount = 3;


	public Connection getConnection() throws RuntimeException {
		int retryC = retryCount;
		for (int i = 0; i < retryC; i++) {
			try {
				return this.connectionFactory.newConnection();
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				System.out.println("retry " + i);
			}
		}
		throw new RuntimeException("connection was refuse ");
	}


}
