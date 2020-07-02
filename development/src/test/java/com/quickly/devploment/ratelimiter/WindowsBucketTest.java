package com.quickly.devploment.ratelimiter;

/**
 * @Author lidengjin
 * @Date 2020/6/4 5:01 下午
 * @Version 1.0
 * 滑动时间窗口 简单 demo
 */
public class WindowsBucketTest {
	public static void main(String[] args) throws InterruptedException {
		int windowLength = 500;
		int arrayLength = 3;
		calculate(windowLength,arrayLength);

		Thread.sleep(100);
		calculate(windowLength,arrayLength);

		Thread.sleep(200);
		calculate(windowLength,arrayLength);

		Thread.sleep(200);
		calculate(windowLength,arrayLength);

		Thread.sleep(500);
		calculate(windowLength,arrayLength);

		Thread.sleep(500);
		calculate(windowLength,arrayLength);

		Thread.sleep(500);
		calculate(windowLength,arrayLength);

		Thread.sleep(500);
		calculate(windowLength,arrayLength);

		Thread.sleep(500);
		calculate(windowLength,arrayLength);
	}

	private static void calculate(int windowLength,int arrayLength){
		long time = System.currentTimeMillis();
		long timeId = time/windowLength;
		long currentWindowStart = time - time % windowLength;
		int idx = (int)(timeId % arrayLength);
		System.out.println("time="+time+",currentWindowStart="+currentWindowStart+",timeId="+timeId+",idx="+idx);
	}
}
