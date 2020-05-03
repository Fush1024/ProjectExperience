package com.quickly.devploment.supers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @ClassName BMessage
 * @Description
 * @Author LiDengJin
 * @Date 2019/12/30 17:24
 * @Version V-1.0
 **/
public class BMessage extends AMessage implements Iterable<AMessage> {

	private List<AMessage> messages;

	public List<AMessage> getMessages() {
		return messages;
	}

	@Override
	public String toString() {
		return "BMessage{" + "messages=" + messages + '}';
	}

	public void setMessages(List<AMessage> messages) {
		this.messages = messages;
	}

	@Override
	public Iterator<AMessage> iterator() {
		return messages.iterator();
	}

	@Override
	public void forEach(Consumer<? super AMessage> action) {

	}

	@Override
	public Spliterator<AMessage> spliterator() {
		return null;
	}

	public static void main(String[] args)  {
//		BMessage bMessage;
//		bMessage = createMessage();
//		for (AMessage aMessage : bMessage) {
//			System.out.println(aMessage.toString());
//		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		String format = String
				.format("%04d%02d%02d%02d%02d%02d%03d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
						cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
						cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND));
		System.out.println(format);
	}

	private static BMessage createMessage() {
		BMessage bMessage = new BMessage();
		AMessage aMessage = new AMessage();
		AMessage aMessage1 = new AMessage();
		aMessage.setName("message one ");
		aMessage1.setName("message two ");
		bMessage.setMessages(new ArrayList(){{add(aMessage);add(aMessage1);}});
		return bMessage;
	}
}
