package com.vic.multithread.c.sw;

/**
 * sleep测试
 * @author Victor
 */
public class TestSleep implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++)
			System.out.println(i + " run...");
		// 第二执行
		System.out.println(System.currentTimeMillis());
	}

	public static void main(String[] args) {

		TestSleep ts = new TestSleep();
		Thread t = new Thread(ts);
		t.start();

		try {
			long startTime = System.currentTimeMillis();
			// 第一执行
			System.out.println("startTime = " + startTime); // startTime = 1465 893 596 342

			/**
			 * sleep是阻塞线程的，阻塞过程当中其它线程就会获得cpu执行时间。
			 * 当其它线程执行（完毕/执行中）时，main休眠时间到期，则会返回到可运行状态，立即执行与否取决于当前上下文。
			 * 阻塞的线程取决于你放在哪个线程！
			 */
			Thread.sleep(5000);

			long endTime = System.currentTimeMillis();
			// 第三执行
			System.out.println("endTime = " + endTime); // endTime = 1465893601343

			System.out.println("elapsed time = " + (endTime - startTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("aaaaaaaaaaaa");

	}
}
