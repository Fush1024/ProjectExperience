package com.quickly.devploment.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author lidengjin
 * @Date 2020/5/20 3:49 下午
 * @Version 1.0
 */
@Component
public class Consumer {
	@KafkaListener(topics = {"test"})
	public void listen(ConsumerRecord<?, ?> record){

		Optional<?> kafkaMessage = Optional.ofNullable(record.value());

		if (kafkaMessage.isPresent()) {

			Object message = kafkaMessage.get();
			System.out.println("---->"+record);
			System.out.println("---->"+message);

		}

	}
}
