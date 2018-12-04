package com.vic.multithread.c.test;

/**
 * while(true)不是阻塞的！
 * @author Victor
 *
 */
public class WhileTest {

	public static void main(String[] args) {
		WhileA a = new WhileA();
		new Thread(a).start();
		
		WhileB b = new WhileB();
		new Thread(b).start();
		
		/**
		 * 会交替打印结果
		 */
	}
}

class WhileA implements Runnable {
	
	@Override
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getName());
		}
	}
	
}

class WhileB implements Runnable {
	
	@Override
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getName());
		}
	}
	
}