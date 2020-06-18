package com.quickly.devploment.thread;

/**
 * @Author lidengjin
 * @Date 2020/6/18 9:51 上午
 * @Version 1.0
 */
public class VolatileTestOne {

	boolean stop = false;
	//	volatile boolean stop = false;

	private double aDouble = 0.0d;
	public static void main(String[] args) throws Exception{
		VolatileTestOne v = new VolatileTestOne();
		Thread ta = new Thread(()->v.execute());
		ta.start();
		Thread.sleep(2000);
		Thread tb = new Thread(()->v.shutdown());
		tb.start();
	}

	public void execute(){
		while(!stop){
//			String a = "a";
			if(aDouble++ == 3.0d){
				System.out.println("aaa");
			}
//			System.out.print("");
		}
	}
	public void shutdown(){
		System.out.println("do stop");
		stop = true;
	}



}

