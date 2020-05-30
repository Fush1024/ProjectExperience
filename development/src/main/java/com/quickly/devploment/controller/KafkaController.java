//package com.quickly.devploment.controller;
//
//import com.quickly.devploment.kafka.Producer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Author lidengjin
// * @Date 2020/5/20 3:48 下午
// * @Version 1.0
// */
//@RestController
//@RequestMapping("/kafka")
//public class KafkaController {
//
//
//
//		@Autowired
//		private Producer producer;
//
//		@RequestMapping(value = "/send")
//		public String send() {
//			producer.send();
//			return "{\"code\":0}";
//
//	}
//}
