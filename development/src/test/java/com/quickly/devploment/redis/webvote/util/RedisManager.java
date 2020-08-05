package com.quickly.devploment.redis.webvote.util;

import redis.clients.jedis.Jedis;

/**
 * @Author lidengjin
 * @Date 2020/8/4 2:30 下午
 * @Version 1.0
 */
public class RedisManager {
	private static final String redisHost = "www.shuaideng.com";
	private static final int port = 6379;
	private static Jedis jedis;

	public static void getConnection() {
		if (jedis == null) {
			// DCL 进行获取 jedis
			synchronized (RedisManager.class) {
				if (jedis == null) {
					jedis = new Jedis(redisHost, port);
				}
			}
		}
	}

	public static void releaseConnection() {
		if (jedis != null) {
			synchronized (RedisManager.class) {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
	}

	public static Jedis getJedis(){
		return jedis;
	}

}
