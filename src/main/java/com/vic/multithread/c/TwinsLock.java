package com.vic.multithread.c;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * shared mode
 * permit two threads access simultaneously
 * 
 * @author Victor
 *
 */
public class TwinsLock  implements Lock {
	
	private final Sync sync = new Sync(2);
		
	class Sync extends AbstractQueuedSynchronizer {
		
		private static final long serialVersionUID = 9038282400785841061L;
		
		public Sync(int count) {
			if(count < 0) {
				throw new IllegalArgumentException("count must large than zero. ");
			}
			setState(count);
		}
		
		@Override
		protected int tryAcquireShared(int reduceCount) {
			for(;;) {
				int current = getState();
				int newCount = current - reduceCount;
				if(newCount < 0 || compareAndSetState(current, newCount)) {
					return newCount;
				}
			}
		}
		
		@Override
		protected boolean tryReleaseShared(int returnCount) {
			for(;;) {
				int current = getState();
				int newCount = current + returnCount;
				if(compareAndSetState(current, newCount)) {
					return true;
				}
			}
		}
		
	}
	
	public void lock() {
		sync.acquireShared(1);
	}

	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	public void unlock() {
		sync.releaseShared(1);
	}

	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		final TwinsLock lock = new TwinsLock();
		for(int i = 0; i < 10; i++) {
			Worker w = new Worker(lock);
			w.setDaemon(true);
			w.start();
		}
		
		for(int i = 0; i < 10; i++) {
			SleepUtils.second(1);
			System.out.println();
		}
		
	}
	
}

class Worker extends Thread {
	
	Lock lock;
	
	public Worker(Lock lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		while(true) {
			lock.lock();
			try {
				SleepUtils.second(1);
				System.out.println(Thread.currentThread().getName());
				SleepUtils.second(1);
			} finally {
				lock.unlock();
			}
		}
	}
}
