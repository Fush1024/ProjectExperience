//package com.quickly.devploment;
//
//import com.quickly.devploment.redis.ConstInterface;
//import lombok.extern.slf4j.Slf4j;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.exceptions.JedisClusterException;
//
///**
// * @ClassName AbstractTest
// * @Description
// * @Author LiDengJin
// * @Date 2020/3/18 14:47
// * @Version V-1.0
// **/
//
//@Slf4j
//public abstract class AbstractTest<T>  {
//
//	protected String file = "";
//
//	private int retryTimes ;
//
//	{
//		init();
//	}
//	public AbstractTest() {
//		this.file = ConstInterface.retryTimes+"";
//		this.retryTimes = ConstInterface.retryTimes;
//	}
//
//	private static void init() {
//		initRedis();
//	}
//
//	protected static JedisPool jedisPool;
//
//	private static void initRedis() {
//		// 池基本配置
//		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxIdle(5);
//		config.setTestOnBorrow(false);
//		config.setMaxTotal(8);
//		jedisPool = new JedisPool(config,"120.78.140.235",6379,3000,"redis001");
//
//	}
//
//	public abstract T execute(Jedis jedis);
//
//	public T run(String key){
//		if (key == null) {
//			throw new JedisClusterException("No way to dispatch this command to Redis Cluster.");
//		}
//		 return this.processRedis(key,this.retryTimes);
//
//	}
//
//	private T processRedis(String key, int retryTimes) {
//		if(retryTimes<0){
//			throw new IllegalStateException("retry more times ");
//		}
//		Jedis resource = jedisPool.getResource();
//		try {
//		 return execute(resource);
//		}catch (Exception e){
//			log.info("Error of redis complete,retry connection ... ");
//			return processRedis(key,retryTimes-1);
//		}finally {
//			releaseRedisSource(resource);
//		}
//	}
//
//
//	private void releaseRedisSource(Jedis resource) {
//		if(resource != null){
//			resource.close();
//		}
//	}
//
//}
