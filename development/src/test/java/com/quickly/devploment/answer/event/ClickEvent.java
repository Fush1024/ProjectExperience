package com.quickly.devploment.answer.event;

import java.util.EventObject;

/**
 * @Author lidengjin
 * @Date 2020/7/16 2:23 下午
 * @Version 1.0
 */
public class ClickEvent extends EventObject {
	private EventSource src;

	/**
	 * Constructs a prototypical Event.
	 *
	 * @param source The object on which the Event initially occurred.
	 * @throws IllegalArgumentException if source is null.
	 */
	public ClickEvent(EventSource source) {
		super(source);
		this.src = source;
	}
}
