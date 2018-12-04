package com.vic.multithread.c.b;
/**
 * t.isInterrupted()方法判断中断状态，
 * 注意：当调用sleep()方法抛出异常后，会清空中断状态设置为false。
 * @author Victor
*/
public class InterruptCheck {
	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		System.out.println("Point A: t.interrupted() = " + t.isInterrupted()); //false

		//待决中断，中断自身
		t.interrupt();
		System.out.println("Point B: t.interrupted() = " + t.isInterrupted()); //true
		System.out.println("Point C: t.interrupted() = " + t.isInterrupted()); //true
		
		try {
			Thread.sleep(2000);
			System.out.println("was NOT interrupted");
		} catch (InterruptedException e) {
			System.out.println("was interrupted");
		}
		
		//抛出异常后，会清除中断标志，这里会返回false
		System.out.println("Point D: t.isInterrupted()=" + t.isInterrupted()); //false
	}
}
