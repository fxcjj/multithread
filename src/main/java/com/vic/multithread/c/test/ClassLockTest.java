package com.vic.multithread.c.test;

/**
 * 类锁
 * @author Victor
 *
 */
public class ClassLockTest {
	
	public static void main(String[] args) {
		ClassLockTest clazz = new ClassLockTest();
		
		ThreadC c = new ThreadC(clazz);
		ThreadD d = new ThreadD(clazz);
		
		new Thread(c).start();
		new Thread(d).start();
		
		
	}
	
	public void a() {
		synchronized (ClassLockTest.class) {
			System.out.println(Thread.currentThread().getName()+" entering a()");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void b() {
		synchronized (ClassLockTest.class) {
			System.out.println(Thread.currentThread().getName()+" entering b()");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void c() {
		System.out.println(Thread.currentThread().getName()+" entering c()");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class ThreadC implements Runnable {
	
	private ClassLockTest clazz;
	
	public ThreadC(ClassLockTest clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public void run() {
		clazz.a();
	}
	
}

class ThreadD implements Runnable {

	private ClassLockTest clazz;
	
	public ThreadD(ClassLockTest clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public void run() {
		clazz.b();
	}
	
}