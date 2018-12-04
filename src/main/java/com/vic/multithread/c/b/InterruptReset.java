package com.vic.multithread.c.b;
/**
 * Thread.interrupted()重置了中断标志为false
 * 
 * @author Victor
*/
public class InterruptReset {
	public static void main(String[] args) {
		
		System.out.println("point X: Thread.interrupted() = " + Thread.interrupted());
		
		Thread.currentThread().interrupt();
		//打印出中断状态后，重置了中断标志为false
		System.out.println("point Y: Thread.interrupted() = " + Thread.interrupted());
		
		System.out.println("point Z: Thread.interrupted() = " + Thread.interrupted());
	}
}
