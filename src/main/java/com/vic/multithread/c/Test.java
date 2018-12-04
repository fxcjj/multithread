package com.vic.multithread.c;

/**
 * 
 *
 */
public class Test implements Runnable {
	
	private static int a;
	
	private synchronized void inc() {
		a++;
	}
	
	public void run() {
		for(int i = 0; i < 5; i++) {
			inc();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Test t = new Test();
		Thread thread = new Thread(t);
		thread.start();
		
		//等待thread线程执行完毕
		thread.join();
		
		System.out.println(a);
		
	}

}
