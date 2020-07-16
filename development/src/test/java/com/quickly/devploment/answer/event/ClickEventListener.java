package com.quickly.devploment.answer.event;

import java.util.EventListener;

/**
 * @Author lidengjin
 * @Date 2020/7/16 2:23 下午
 * @Version 1.0
 */
public interface ClickEventListener extends EventListener {
	void click(ClickEvent e);
}
