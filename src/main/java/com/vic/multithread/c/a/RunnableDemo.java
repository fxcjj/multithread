package com.vic.multithread.c.a;
/**
 * 三个线程共同执行一个任务，有可能会出现资源竟争的问题，导致数据不正确！
 * @author Victor
*/
public class RunnableDemo {
	public static void main(String[] args) {
		/**
		 *  三个Thread共同执行一个Runnable对象中的代码
		 *  ticket可能会出现<=0的情况
		 */
		MyThread1 mt = new MyThread1();
		new Thread(mt).start();
		new Thread(mt).start();
		new Thread(mt).start();
		
		int a = 3;
		System.out.println(a--);
	}
}

class MyThread1 implements Runnable {
	private int ticket = 5;
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			if(ticket > 0) {
				try {
					/**
					 * 线程休眠500毫秒，那么多个线程可能到这里了，
					 * 然后打印ticket可能会出现-1的情况
					 * 这种情况的出现是由于:
					 * 一个线程在判断ticket为1>0后，还没有来得及减1，另一个线程已经将ticket减1，变为了0，那么接下来之前的线程再将ticket减1，便得到了-1。
					 */
					Thread.sleep(500);
					System.out.println(Thread.currentThread() + ", ticket = " + ticket--);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
