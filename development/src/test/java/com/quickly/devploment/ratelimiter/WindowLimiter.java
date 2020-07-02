package com.quickly.devploment.ratelimiter;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author lidengjin
 * @Date 2020/7/2 1:56 下午
 * @Version 1.0
 */
@Slf4j
public class WindowLimiter {

	private LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS)
			.build(new CacheLoader<Long, AtomicLong>() {
				@Override
				public AtomicLong load(Long seconds) throws Exception {
					return new AtomicLong(0);
				}
			});
	private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
	//限流阈值
	private long limit = 15;

	/**
	 * 滑动时间窗口
	 * 每隔1s累加前5s内每1s的请求数量，判断是否超出限流阈值
	 */
	public void slideWindow() {
		scheduledExecutorService.scheduleWithFixedDelay(() -> {
			try {
				long time = System.currentTimeMillis() / 1000;
				//每秒发送随机数量的请求
				int reqs = (int) (Math.random() * 5) + 1;
				counter.get(time).addAndGet(reqs);
				long nums = 0;
				// time windows 5 s
				for (int i = 0; i < 5; i++) {
					nums += counter.get(time - i).get();
				}
				log.info("time=" + time + ",nums=" + nums);
				if (nums > limit) {
					log.info("限流了,nums=" + nums);
				}
			} catch (Exception e) {
				log.error("slideWindow error", e);
			} finally {
			}
		}, 5000, 1000, TimeUnit.MILLISECONDS);
	}

	public static void main(String[] args) {
		new WindowLimiter().slideWindow();
	}
}
