package com.vic.multithread.c.reentrantlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可中断
 * @author Victor
 *
 */
public class InterruptiblyTest {
	public static void main(String[] args) throws Exception {
		DeadTask dt1 = new DeadTask(1);
		DeadTask dt2 = new DeadTask(2);
		new Thread(dt1).start();
		new Thread(dt2).start();
		Thread.sleep(3000);
		DeadlockChecker.check();
	}
}

class DeadTask implements Runnable {
	
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	int lock;
	
	public DeadTask(int lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		try {
			if(lock == 1) {
				lock1.lockInterruptibly();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock2.lockInterruptibly();
			} else {
				lock2.lockInterruptibly();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock1.lockInterruptibly();
			}
		} catch (Exception e) {
			// handle exception
		} finally {
			if(lock1.isHeldByCurrentThread()) {
				lock1.unlock();
			}
			if(lock2.isHeldByCurrentThread()) {
				lock2.unlock();
			}
			System.out.println(Thread.currentThread().getId() + ":线程退出");
		}
	}
}

class DeadlockChecker {
	private final static ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
	final static Runnable deadlockChecker = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
				if (deadlockedThreadIds != null) {
					ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);
					for (Thread t : Thread.getAllStackTraces().keySet()) {
						for (int i = 0; i < threadInfos.length; i++) {
							if(t.getId() == threadInfos[i].getThreadId()) {
								t.interrupt();
							}
						}
					}
				}
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
	};
	
	public static void check() {
		Thread t = new Thread(deadlockChecker);
		t.setDaemon(true);
		t.start();
	}
}