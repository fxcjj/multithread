package com.vic.multithread.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	
	private Lock lock = new ReentrantLock();
	
	private final Condition condition = lock.newCondition();
	
	public static void main(String[] args) throws Exception {
		MyService ms = new MyService();
//		ms.test1();
		ms.test2();
		
		Thread.sleep(500);
		
		ms.signal();
		
	}
	
	
	private void signal() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					condition.signal();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}


	public void test2() {
		
		try {
			lock.lock();
			System.out.println("Start wait");
			condition.await();
			for(int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + ", " + (i + 1));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		
	}
	
	
	public void test1() {
		lock.lock();
		for(int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + ", " + (i + 1));
		}
		lock.unlock();
	}
	
	
	
}
