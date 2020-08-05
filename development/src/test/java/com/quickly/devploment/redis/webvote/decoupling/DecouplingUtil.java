package com.quickly.devploment.redis.webvote.decoupling;

import com.quickly.devploment.redis.webvote.executors.VoteExecute;
import com.quickly.devploment.redis.webvote.util.RedisManager;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author lidengjin
 * @Date 2020/8/4 2:53 下午
 * @Version 1.0
 */
@Slf4j
public class DecouplingUtil {

	public static <T> T executeVote(VoteExecute<T> voteExecute) {
		tryConnection();
		try {
			return voteExecute.execute();
		} finally {
			tryRelease();
		}

	}

	private static void tryRelease() {
		try {
			RedisManager.releaseConnection();
		} catch (Exception e) {
			log.info("release connection exception", e);
		}
	}

	private static void tryConnection() {
		try {
			RedisManager.getConnection();
		} catch (Exception e) {
			log.info("get connection exception ", e);
		}

	}
}
