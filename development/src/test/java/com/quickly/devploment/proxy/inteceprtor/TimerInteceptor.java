package com.quickly.devploment.proxy.inteceprtor;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author lidengjin
 * @Date 2020/5/25 11:06 上午
 * @Version 1.0
 */
@Slf4j
public class TimerInteceptor implements MyInteceptor {

	ThreadLocal<Long> threadLocal = new ThreadLocal<>();


	@Override
	public void doBefore() {
		long start = System.currentTimeMillis();
		log.info("线程 {} ,开始 ：{} ", Thread.currentThread().getName(), start);
		threadLocal.set(start);
	}

	@Override
	public void doAfter() {
		long end = threadLocal.get().longValue();
		log.info(" 线程i ，{} 结束于 ：{} ", Thread.currentThread().getName() ,System.currentTimeMillis() - end);
		threadLocal.remove();
	}
}
