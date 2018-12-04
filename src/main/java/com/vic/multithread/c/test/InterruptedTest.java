package com.vic.multithread.c.test;

/**
 * 第一次调用Thread.interrupted()方法返回true，同时重置中断状态为false
 * 这个方法只是单纯的重置中断标志。
 * 那么之前的待决中断(interrupt())的状态变为false，那么调用sleep()方法时就不会抛出异常喽！
 * @author Victor
 */
public class InterruptedTest {

	public static void main(String[] args) {
		Thread.currentThread().interrupt();
//		System.out.println(Thread.interrupted()); //true
//		System.out.println(Thread.interrupted()); //false
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.interrupted()); //false
	}
}
