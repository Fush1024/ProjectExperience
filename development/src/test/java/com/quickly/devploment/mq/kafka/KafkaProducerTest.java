package com.quickly.devploment.mq.kafka;

/**
 * @Author lidengjin
 * @Date 2020/6/17 2:46 下午
 * @Version 1.0
 */

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Title: KafkaProducerTest
 * Description:
 * kafka 生产者demo
 * Version:1.0.0
 */
public class KafkaProducerTest implements Runnable {

	private final KafkaProducer<String, String> producer;
	private final String topic;

	public KafkaProducerTest(String topicName) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "www.shuaideng.com:9092");
		props.put("acks", "all");
		props.put("retries", 3);
		props.put("batch.size", 16384);
		props.put("key.serializer", StringSerializer.class.getName());
		props.put("value.serializer", StringSerializer.class.getName());
		this.producer = new KafkaProducer<String, String>(props);
		this.topic = topicName;
	}

	@Override
	public void run() {
		int messageNo = 1;
		try {
			for (; ; ) {
				String messageStr = "你好，这是第" + messageNo + "条数据";
				producer.send(new ProducerRecord<String, String>(topic, "Message", messageStr));
				//生产了100条就打印
				if (messageNo % 100 == 0) {
					System.out.println("发送的信息:" + messageStr);
				}
				//生产1000条就退出
				if (messageNo % 1000 == 0) {
					System.out.println("成功发送了" + messageNo + "条");
					break;
				}
				messageNo++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}

	public static void main(String args[]) {
		KafkaProducerTest test = new KafkaProducerTest("KAFKA_TEST");
		Thread thread = new Thread(test);
		thread.start();
	}
}
