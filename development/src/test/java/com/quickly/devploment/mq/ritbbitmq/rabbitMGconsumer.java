package com.quickly.devploment.mq.ritbbitmq;

/**
 * @Author lidengjin
 * @Date 2020/6/16 4:03 下午
 * @Version 1.0
 */


import com.quickly.devploment.mq.ritbbitmq.config.RibbitMQFactory;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;


/**
 * 消费者
 * @author Administrator
 *
 */
public class rabbitMGconsumer {



	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Connection connection = new RibbitMQFactory().getConnection();
			Channel channel = connection.createChannel();
			AMQP.Queue.DeclareOk declareOK = channel.queueDeclare("test", false, false, false, null);
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume("test", true, consumer);
			while (true) {
				QueueingConsumer.Delivery deliver = consumer.nextDelivery();
				System.out.println("reciver messager:"+new String(deliver.getBody()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
