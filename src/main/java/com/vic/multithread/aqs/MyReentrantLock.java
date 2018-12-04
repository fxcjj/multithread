package com.vic.multithread.aqs;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyReentrantLock {
	
	AtomicInteger ai = new AtomicInteger(3);
	
	
	Sync sync;
	
	public MyReentrantLock() {
        sync = new NonfairSync();
    }
	
	public void lock() {
        sync.lock();
    }
	
	abstract static class Sync extends AbstractQueuedSynchronizer {
		
		abstract void lock();
		
	}
	
	
	static final class NonfairSync extends Sync {
		
		final void lock() {
			acquire(1);
		}
		
		@Override
		protected boolean tryAcquire(int arg) {
			System.out.println("hello");
			return true;
		}
		
	}
	
	public static void main(String[] args) {
		MyReentrantLock rl = new MyReentrantLock();
//		rl.lock();
		
		int incrementAndGet = rl.ai.incrementAndGet();
		
		System.out.println(incrementAndGet);
		
		
		
	}
	
}
