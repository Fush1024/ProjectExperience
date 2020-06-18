package com.quickly.devploment.mq.ritbbitmq.config.properties;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * @Author lidengjin
 * @Date 2020/6/18 10:36 上午
 * @Version 1.0
 */
public class RabbitMQFactory {


	private ConnectionFactory connectionFactory;

	public RabbitMQFactory(Properties properties) {
		this.connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(properties.getProperty(RabbitMQConfigureAlias.ADDRESS));
		connectionFactory.setPort(Integer.parseInt(properties.getProperty(RabbitMQConfigureAlias.PORT)));
		connectionFactory.setUsername(properties.getProperty(RabbitMQConfigureAlias.USERNAME));
		connectionFactory.setPassword(properties.getProperty(RabbitMQConfigureAlias.PASSWORD));
	}

	/**
	 * 默认重试3次
	 */
	private final static Integer retryCount = 3;

	public ConnectionFactory getConnectionFactory() {
		return this.connectionFactory;
	}

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
