package com.vic.multithread.c.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 限时锁
 * tryLock(long timeout, TimeUnit unit)
 * 
 * @author Victor
 *
 */
public class TryLockTest {
	public static void main(String[] args) {
		TryLock tl = new TryLock();
		new Thread(tl).start();
		new Thread(tl).start();
	}
}

class TryLock implements Runnable {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	@Override
	public void run() {
		try {
			if(lock.tryLock(5, TimeUnit.SECONDS)) {
				Thread.sleep(6000);
			} else {
				System.out.println("get lock failed");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}
	
}
