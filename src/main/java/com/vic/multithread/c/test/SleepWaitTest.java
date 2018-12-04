package com.vic.multithread.c.test;

/**
 * 
 * @author Victor
*/
public class SleepWaitTest implements Runnable {
	
	
	@Override
	public void run() {
		try {
			synchronized (SleepWaitTest.class) {
				System.out.println(Thread.currentThread()+"enter sync");
			//			Thread.sleep(5000L);
			wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SleepWaitTest t1 = new SleepWaitTest();
		//SleepWaitTest t2 = new SleepWaitTest();
		new Thread(t1).start();
		//new Thread(t2).start();
	}
}
