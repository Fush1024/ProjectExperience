package com.quickly.devploment.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author lidengjin
 * @Date 2020/5/28 1:49 下午
 * @Version 1.0
 * @Description 基于漏斗思想的基于内存的限流
 */
public class FunnelRateLimiter {
	static class Funnel {
		int capacity;
		float leakingRate;
		int leftQuota;
		long leakingTs;

		public Funnel(int capacity, float leakingRate) {
			this.capacity = capacity;
			this.leakingRate = leakingRate;
			this.leftQuota = capacity;
			this.leakingTs = System.currentTimeMillis();
		}

		void makeSpace() {
			long nowTs = System.currentTimeMillis();
			long deltaTs = nowTs - leakingTs;
			int deltaQuota = (int) (deltaTs * leakingRate);
			if (deltaQuota < 0) { // 间隔时间太长，整数数字过大溢出
				this.leftQuota = capacity;
				this.leakingTs = nowTs;
				return;
			}
			if (deltaQuota < 1) { // 腾出空间太小，最小单位是1
				return;
			}
			this.leftQuota += deltaQuota;
			this.leakingTs = nowTs;
			if (this.leftQuota > this.capacity) {
				this.leftQuota = this.capacity;
			}
		}

		boolean watering(int quota) {
			makeSpace();
			if (this.leftQuota >= quota) {
				this.leftQuota -= quota;
				return true;
			}
			return false;
		}
	}

	private Map<String, Funnel> funnels = new HashMap<>();

	public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
		String key = String.format("%s:%s", userId, actionKey);
		Funnel funnel = funnels.get(key);
		if (funnel == null) {
			funnel = new Funnel(capacity, leakingRate);
			funnels.put(key, funnel);
		}
		return funnel.watering(1); // 需要1个quota
	}

	public static void main(String[] args) {
		FunnelRateLimiter funnelRateLimiter = new FunnelRateLimiter();
		AtomicInteger count = new AtomicInteger();
		for (int i = 0; i < 1000 ; i++) {
			boolean actionAllowed = funnelRateLimiter.isActionAllowed("user1", "doAdd", 200, 0.5f);
			if(actionAllowed){
				count.incrementAndGet();
			}
			System.out.println(actionAllowed);
		}

		System.out.println(count);
	}
}
