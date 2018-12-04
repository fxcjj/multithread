package com.vic.multithread.d.c01;

public class DeadLockTest {
	
	static String A = "A";
	static String B = "B";
	
	public static void main(String[] args) {
		new DeadLockTest().deadLock();
	}
	
	private void deadLock() {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (A) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					synchronized (B) {
						System.out.println("t1");
					}
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (B) {
					synchronized (A) {
						System.out.println("t2");
					}
				}
			}
			
		});
		
		t1.start();
		t2.start();
	}
	
}



