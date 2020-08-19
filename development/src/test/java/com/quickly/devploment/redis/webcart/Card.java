package com.quickly.devploment.redis.webcart;

import com.quickly.devploment.redis.webvote.util.RedisManager;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Author lidengjin
 * @Date 2020/8/6 10:41 上午
 * @Version 1.0
 */
public class Card {
	@Test
	public void testCard(){
		RedisManager.getConnection();
		Jedis jedis = RedisManager.getJedis();

		jedis.close();
	}
}
