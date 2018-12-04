package com.vic.multithread.c.sw;

/**
 * 测试wait()方法，当多个线程执行到wait()方法时，
 * 多个线程等待该对象的对象锁，执行notify()方法时，
 * 会通知其中的一个，不影响其他线程
 * @author Victor
 */
public class TestWait implements Runnable {

	private Object testLock = new Object();
	
	@Override
	public void run() {
		synchronized (testLock) {
			for(int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName()+", number = "+i);
					testLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void myNotifyAll() {
		synchronized (testLock) {
			testLock.notifyAll();
		}
	}
	
	public static void main(String[] args) {
		final TestWait tw = new TestWait();
		new Thread(tw).start();
		new Thread(tw).start();
		new Thread(tw).start();
		new Thread(tw).start();
		
		try {
			Thread.sleep(5000);
			tw.myNotifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
