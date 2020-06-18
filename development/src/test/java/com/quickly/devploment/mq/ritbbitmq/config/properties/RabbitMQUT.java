package com.quickly.devploment.mq.ritbbitmq.config.properties;

import java.util.Properties;

/**
 * @Author lidengjin
 * @Date 2020/6/18 10:44 上午
 * @Version 1.0
 */
public class RabbitMQUT {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.setProperty(RabbitMQConfigureAlias.PASSWORD,"password");
		properties.setProperty(RabbitMQConfigureAlias.USERNAME,"username");
		properties.setProperty(RabbitMQConfigureAlias.PORT,"port");
		properties.setProperty(RabbitMQConfigureAlias.ADDRESS,"address");
		RabbitMQFactory rabbitMQFactory = new RabbitMQFactory(properties);

	}
}
