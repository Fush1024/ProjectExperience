package com.quickly.devploment.current.execute;

/**
 * @Author lidengjin
 * @Date 2020/7/6 1:53 下午
 * @Version 1.0
 */

@FunctionalInterface
public interface WithinLockExecutor<T> {

	T execute();
}
