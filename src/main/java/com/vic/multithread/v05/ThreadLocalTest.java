package com.vic.multithread.v05;

import java.util.Random;

public class ThreadLocalTest {
	
	
	static ThreadLocal<Integer> tl = new ThreadLocal<Integer>();
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				public void run() {
					//random number
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName()
							+ " has put data: " + data);
					tl.set(data);
					new A().get();
					new B().get();
				}
				
			}).start();
		}
	}
	
	static class A {
		public void get() {
			Integer data = tl.get();
			System.out.println("A from " + Thread.currentThread().getName() + " get data: " + data);
		}
	}
	
	static class B {
		public void get() {
			Integer data = tl.get();
			System.out.println("A from " + Thread.currentThread().getName() + " get data: " + data);
		}
	}
}
