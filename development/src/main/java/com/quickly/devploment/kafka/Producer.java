package com.quickly.devploment.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @Author lidengjin
 * @Date 2020/5/20 3:46 下午
 * @Version 1.0
 */
@Component
public class Producer {
	@Autowired
	private KafkaTemplate kafkaTemplate;

	private static Gson gson = new GsonBuilder().create();

	//发送消息方法
	public void send() {
		Message message = new Message();
		message.setId("KFK_shuaideng_"+System.currentTimeMillis());
		message.setMsg(UUID.randomUUID().toString());
		message.setSendTime(new Date());
		kafkaTemplate.send("test", gson.toJson(message));
	}
}
