package com.vic.multithread.v03;

public class TraditionalThreadSynchronized {
	
	public static void main(String[] args) {
		TraditionalThreadSynchronized t = new TraditionalThreadSynchronized();
		t.init();
	}
	
	public void init() {
		final Outputer outputer = new Outputer();
		
		new Thread(new Runnable() {
			
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("zhugeliang");
				}
			}
			
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("zhugeliang");
				}
			}
			
		}).start();
	}
	
	class Outputer {
		
		public void output(String name) {
			int len = name.length();
			
			/**
			 * 1) synchronized (name) {
			 * 锁name，如果不打同一字符串，锁是无效的
			 * 
			 * 2) synchronized (this) {
			 * 锁this是可以的，是同一个对象
			 * 
			 * 3) synchronized (TraditionalThreadSynchronized.class) {
			 * 锁class，世界唯一，是可以的
			 * 
			 * 互斥要使用同一个对象
			 */
//			
			
			synchronized (name) {
				for(int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
			}
			
			System.out.println();
		}
		
	}
	
}
