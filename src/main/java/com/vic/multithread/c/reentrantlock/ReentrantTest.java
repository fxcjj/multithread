package com.vic.multithread.c.reentrantlock;

/**
 * 锁的可重入
 * @author Victor
 *
 */
public class ReentrantTest {
	
	final static Child child = new Child(); //为了保证锁唯一

	public static void main(String[] args) {
		for(int i = 0; i < 50; i++) {
			new Thread(child).start();
		}
	}
}

class Child extends Father implements Runnable {
	
	// 第一次调用，获取锁
	public synchronized void doSomething() {
		System.out.println("1child.doSomething()");
		doAnthorThing(); //调用自己类中的其他synchronized方法
	}
	
	// 第二次调用，本身已经获取锁了
	public synchronized void doAnthorThing() {
		super.doSomething();
		System.out.println("3child.doAnthorThing()");
	}
	
	@Override
	public void run() {
		doSomething();
	}
}

class Father {

	public synchronized void doSomething() {
		System.out.println("2father.doSomething()");
	}
	
}