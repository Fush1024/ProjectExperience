//package com.quickly.devploment.redis;
//
//
//import com.quickly.devploment.AbstractTest;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import redis.clients.jedis.Jedis;
//
///**
// * @ClassName RedisComplete
// * @Description
// * @Author LiDengJin
// * @Date 2020/3/18 14:46
// * @Version V-1.0
// **/
//
//@Slf4j
//public class RedisComplete {
//
//	public String set(String key,String value){
//		return new AbstractTest<String>() {
//			@Override
//			public String execute(Jedis connection) {
//				return connection.set(key,value);
//			}
//		}.run(key);
//	}
//
//	public String get(String key){
//		return new AbstractTest<String>() {
//			@Override
//			public String execute(Jedis jedis) {
//				return jedis.get(key);
//			}
//		}.run(key);
//	}
//
//
//	@Test
//	public void testSet(){
//		String set = new RedisComplete().set("two", "two");
//		log.info("set ? {}", set);
//		RedisComplete redisComplete = new RedisComplete();
//		String two = redisComplete.get("two");
//		log.info(two);
//	}
//}
