package com.quickly.devploment.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;

import java.io.IOException;

/**
 * @Author lidengjin
 * @Date 2020/5/28 11:49 上午
 * @Version 1.0
 */
public class RedisLimiter {

		private Jedis jedis;

		public RedisLimiter(Jedis jedis) {
			this.jedis = jedis;
		}
		public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {
			String key = String.format("hist:%s:%s", userId, actionKey);
			long nowTs = System.currentTimeMillis();
			redis.clients.jedis.Pipeline pipe = jedis.pipelined();
			pipe.multi();
			pipe.zadd(key, nowTs, "" + nowTs);
			pipe.zremrangeByScore(key, 0, nowTs - period * 1000);
			Response<Long> zcard = pipe.zcard(key);
			pipe.expire(key, period + 1);
			pipe.exec();
			try {
				pipe.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return zcard.get() <= maxCount;
		}
		public static void main(String[] args) {
			Jedis jedis = new Jedis("www.shuaideng.com",6379);
			RedisLimiter limiter = new RedisLimiter(jedis);
			for(int i=0;i<20;i++) {
				System.out.println(limiter.isActionAllowed("laoqian", "reply", 60, 5));
			}
		}
	}

