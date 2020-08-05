package com.quickly.devploment.redis.webvote.executors;

/**
 * @Author lidengjin
 * @Date 2020/8/4 2:48 下午
 * @Version 1.0
 */
public interface VoteExecute<T> {
	T execute();
}
