package com.vic.multithread.c;

public class ShutDown {

	public static void main(String[] args) {
		
		Runner one = new Runner();
		
		Thread t1 = new Thread(one, "RunnerThread");
		t1.start();
		
		SleepUtils.second(10); //让Runner执行一会儿
		
		//中断
		t1.interrupt();
		
		Runner two = new Runner();
		t1 = new Thread(two, "RunnerThread");
		t1.start();
		SleepUtils.second(1);
		two.cancel();
		
	}
	
	private static class Runner implements Runnable {
		
		private long i;
		private volatile boolean on = true;
		
		
		public void run() {
			while(on && !Thread.currentThread().isInterrupted()) {
				i++;
			}
			System.out.println("Count i = "+i);
		}
		
		public void cancel() {
			on = false;
		}
	}
}
