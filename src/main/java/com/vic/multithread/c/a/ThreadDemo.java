package com.vic.multithread.c.a;
/**
 * 三个线程各自卖票，线程是独立的，
 * 不会受其他线程影响，so，这是安全的
 * 
 * @author Victor
*/
public class ThreadDemo  {
	public static void main(String[] args) {
		/**
		 * 三个Thread各自卖票，没有关系
		 * 所以ticket不会出现<=0的情况
		 */
		new MyThread().start();
		new MyThread().start();
		new MyThread().start();

	}
}

class MyThread extends Thread {
	
	private int ticket = 5;
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			if(ticket > 0) {
				System.out.println(Thread.currentThread() + ", ticket = " + ticket--);
			}
		}
	}
}
