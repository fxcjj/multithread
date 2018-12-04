package com.vic.multithread.c;

public class Synchronized {
	public static void main(String[] args) {
		// 对Synchronized Class对象进行加锁
		synchronized (Synchronized.class) {
			System.out.println("a");
		}
		// 静态同步方法，对Synchronized Class对象进行加锁
		m();
	}

	public static synchronized void m() {
		System.out.println("b");
	}
}