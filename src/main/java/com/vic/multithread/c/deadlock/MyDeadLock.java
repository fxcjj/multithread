package com.vic.multithread.c.deadlock;

/**
 * 
 * @author Victor
 */
public class MyDeadLock implements Runnable {
	
	private int flag;
	
	static Object lock = new Object();
	static Object lock1 = new Object();
	
	
	@Override
	public void run() {
		
		if(flag == 0) {
			synchronized(lock) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(lock1) {
				}
			}
		}
		
		if(flag == 1) {
			synchronized(lock1) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(lock) {
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		MyDeadLock d1 = new MyDeadLock();
		MyDeadLock d2 = new MyDeadLock();
		d1.flag = 0;
		d2.flag = 1;
		
		new Thread(d1).start();
		new Thread(d2).start();
		
	}
	
}
