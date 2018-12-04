package com.vic.multithread.d.c06;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {

	public static void main(String[] args) throws Exception {
		
		//测试put方法及interrupt
//		testPutAndInterrupt();
		
		//测试超时退出
		testOffer();
		
	}

	private static void testOffer() throws Exception {
		BlockingQueue<String> abq = new ArrayBlockingQueue<String>(2);
		boolean a = abq.offer("a", 3, TimeUnit.SECONDS);
		System.out.println(a);
		boolean b = abq.offer("b", 3, TimeUnit.SECONDS);
		System.out.println(b);
		boolean c = abq.offer("c", 3, TimeUnit.SECONDS);
		System.out.println(c);
	}
	
	private static void testPutAndInterrupt() throws Exception {
		final BlockingQueue<String> abq = new ArrayBlockingQueue<String>(2);
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					abq.put("a");
					abq.put("b");
					abq.put("c");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		Thread.sleep(3000);
		
		t.interrupt();
	}

}
