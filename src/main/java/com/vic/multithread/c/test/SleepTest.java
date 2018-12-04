package com.vic.multithread.c.test;

public class SleepTest {
	
	public static void main(String[] args) {
		SleepA a = new SleepA();
		for(int i = 0; i < 10; i++) {
			new Thread(a).start();
		}
		
	}
	
}

class SleepA implements Runnable {

	@Override
	public void run() {
		synchronized (SleepA.class) {
			try {
				Thread.sleep(1000);
				System.out.println("a");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
