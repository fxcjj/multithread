package com.vic.multithread.v05;

import java.util.Random;

public class ThreadScopeShareData {
	
	private static int data = 0;
	
	public static void main(String[] args) {
		
		/**
		 * 开启两个线程
		 * 由于两个线程的执行时机不同，所以出现的结果也是无法预料的。
		 * 会出现如下情况：
		 * 1) 正确，线程0生成随机数x，a.get()打印x正确，b.get()打印x正确
		 * 2) 线程0先执行到"has put data"时，此时data的值为x，切换到线程1，重新赋值为y，a.get()打印y错误，b.get打印y错误
		 * 3) 线程1先执行...
		 * 4) 关键点在为data赋值之后
		 */
		for(int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				public void run() {
					//random number
					data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName()
							+ " has put data: " + data);
					new A().get();
					new B().get();
				}
				
			}).start();
		}
	}
	
	static class A {
		public void get() {
			System.out.println("A from " + Thread.currentThread().getName() + " get data: " + data);
		}
	}
	
	static class B {
		public void get() {
			System.out.println("B from " + Thread.currentThread().getName() + " get data: " + data);
		}
	}
}
