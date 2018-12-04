package com.vic.multithread.c;
/**
 * 
 * @author Victor
*/
public class Father {
	
	public synchronized void hi() {
		System.out.println(this);
		System.out.println("Father hi");
	}
	
	public static void main(String[] args) {
		Father f = new Child();
		f.hi();
	}
	
}

class Child extends Father {
	public synchronized void hi() {
		System.out.println(this);
		System.out.println("Child hi");
		super.hi();
	}
}