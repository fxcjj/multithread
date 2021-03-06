package com.vic.multithread.c.b;
/**
 * t.interrupt()方法中断线程，处于休眠状态的线程t将会被中断，
 * 同时抛出异常并重置中断标志为false
 * Note:
 * while或for循环不会被打断
 * @author Victor
*/
public class SleepInterrupt implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("in run() - about to sleep for 20 seconds");
			Thread.sleep(20000);
			System.out.println("in run() - woke up");
		} catch (InterruptedException e) {
			System.out.println("in run() - interrupted while sleeping");
			//处理完中断异常后，返回到run()方法入口
			//如果没有return, 线程不会实际被中断，它会继续打印下面的信息
			e.printStackTrace();
			return;
		}
		System.out.println("in run() - leaving normally");
	}
	
	public static void main(String[] args) {
		SleepInterrupt si = new SleepInterrupt();
		Thread t = new Thread(si);
		t.start();
		//主线程休眠2秒，从而确保刚才启动的线程有机会执行一段时间
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("in main() - interrupting other thread");
		//中断线程t
		t.interrupt();
		System.out.println("in main() - leaving");
	}

}
