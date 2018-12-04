package com.vic.multithread.aqs;

public class MyNode {
	
	volatile int waitStatus;
	
	//predecessor
	private MyNode prev;
	
	//successor
	private MyNode next;
	
	public static void main(String[] args) {
		MyNode mn = new MyNode();
		System.out.println(mn.waitStatus);
	}
	
	
}
