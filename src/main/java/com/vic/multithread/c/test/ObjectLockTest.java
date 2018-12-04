package com.vic.multithread.c.test;

/**
 * 1, 多个线程，同一实例对象锁
 * 		a) 多个synchronized代码块或方法之间阻塞
 * 		b) synchronized代码块或方法与非synchronized方法之间不会阻塞，但线程可以有切换
 * 2, 多个线程，不同的实例对象锁
 * 		a) 多个synchronized代码块或方法之间无影响
 * 		b) synchronized代码块或方法与非synchronized方法之间无影响
 * @author Victor
 *
 */
public class ObjectLockTest {
	
	public static void main(String[] args) {
		
		singleObj();
		
		//multiObj();
	}

	//多线程，同一实例对象
	private static void singleObj() {
		ObjectLockTest obj = new ObjectLockTest();
		ThreadA a = new ThreadA(obj);
		ThreadB b = new ThreadB(obj);
		
		new Thread(a).start();
		new Thread(b).start();
	}
	
	//多线程，不同实例对象
	private static void multiObj() {
		ObjectLockTest obj = new ObjectLockTest();
		ObjectLockTest obj1 = new ObjectLockTest();
		ThreadA a = new ThreadA(obj);
		ThreadB b = new ThreadB(obj1);
		
		new Thread(a).start();
		new Thread(b).start();
	}
	
	public void a() {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+" entering a()");
			//模拟执行任务
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void b() {
		System.out.println(Thread.currentThread().getName()+" entering b()");
		//模拟执行任务
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void c() {
		System.out.println(Thread.currentThread().getName()+" entering c()");
		//模拟执行任务
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadA implements Runnable {
	
	private ObjectLockTest obj;
	
	public ThreadA(ObjectLockTest obj) {
		this.obj = obj;
	}
	
	@Override
	public void run() {
		obj.a();
	}
	
}

class ThreadB implements Runnable {

	private ObjectLockTest obj;
	
	public ThreadB(ObjectLockTest obj) {
		this.obj = obj;
	}
	
	@Override
	public void run() {
		obj.a();
	}
	
}

