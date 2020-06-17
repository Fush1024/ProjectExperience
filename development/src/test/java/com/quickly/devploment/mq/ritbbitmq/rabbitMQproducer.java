package com.quickly.devploment.mq.ritbbitmq;

/**
 * @Author lidengjin
 * @Date 2020/6/16 4:02 下午
 * @Version 1.0
 */

import com.quickly.devploment.mq.ritbbitmq.config.RibbitMQFactory;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * @author Administrator
 *
 */
public class rabbitMQproducer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Connection connection = new RibbitMQFactory().getConnection();
			Channel channel = connection.createChannel();
			AMQP.Queue.DeclareOk declareOK = channel.queueDeclare("test", false, false, false, null);
			channel.basicPublish("", "test", null, "hello rabbit".getBytes("UTF-8"));
			channel.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
