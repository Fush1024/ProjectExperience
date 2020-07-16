package com.quickly.devploment.answer.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @Author lidengjin
 * @Date 2020/7/16 2:23 下午
 * @Version 1.0
 */
public class EventSource {
	private Semaphore semaphore = new Semaphore(1);
	private final List<ClickEventListener> listeners = new ArrayList<>();

	public void addListener(ClickEventListener listener) throws InterruptedException {
		semaphore.acquire(1);
		listeners.add(listener);
		semaphore.release(1);
	}

	public void removeListener(ClickEventListener listener) throws InterruptedException {
		semaphore.acquire(1);
		if (!listeners.isEmpty())
			listeners.remove(listener);
		semaphore.release(1);
	}

	protected void actionPerformed() throws InterruptedException {
		semaphore.acquire(1);
		ClickEvent event = new ClickEvent(this);
		for (ClickEventListener listener : listeners) {
			listener.click(event);
		}
		semaphore.release(1);
	}


	public static void main(String[] args) {
		EventSource source = new EventSource();
		try {
			source.addListener((e) -> System.out.println("Click event performed!"));
			source.actionPerformed();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
