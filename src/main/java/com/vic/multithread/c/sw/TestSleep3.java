package com.vic.multithread.c.sw;

/**
 * 测试sleep和wait是否释放持有锁
 * @author Victor
 */
public class TestSleep3 implements Runnable {
	
	@Override
	public void run() {
		work();
	}

	private synchronized void work() {
		for(int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread() + " i = " + i);
			try {
				//Thread.sleep(1000);
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		/*
		 * 一个对象由多个线程执行
		 */
		TestSleep3 ts1 = new TestSleep3();
		Thread t1= new Thread(ts1);
		t1.start();
		
		Thread t2 = new Thread(ts1);
		t2.start();
	}
/*
 使用sleep(1000)的情况，没有释放锁
 Thread[Thread-0,5,main] i = 0
Thread[Thread-0,5,main] i = 1
Thread[Thread-0,5,main] i = 2
Thread[Thread-0,5,main] i = 3
Thread[Thread-0,5,main] i = 4
Thread[Thread-0,5,main] i = 5
Thread[Thread-0,5,main] i = 6
Thread[Thread-0,5,main] i = 7
Thread[Thread-0,5,main] i = 8
Thread[Thread-0,5,main] i = 9
Thread[Thread-1,5,main] i = 0
Thread[Thread-1,5,main] i = 1
Thread[Thread-1,5,main] i = 2
Thread[Thread-1,5,main] i = 3
Thread[Thread-1,5,main] i = 4
Thread[Thread-1,5,main] i = 5
Thread[Thread-1,5,main] i = 6
Thread[Thread-1,5,main] i = 7
Thread[Thread-1,5,main] i = 8
Thread[Thread-1,5,main] i = 9

 
 使用wait(1000)的情况，释放持有锁
Thread[Thread-0,5,main] i = 0
Thread[Thread-1,5,main] i = 0
Thread[Thread-1,5,main] i = 1
Thread[Thread-0,5,main] i = 1
Thread[Thread-0,5,main] i = 2
Thread[Thread-1,5,main] i = 2
Thread[Thread-1,5,main] i = 3
Thread[Thread-0,5,main] i = 3
Thread[Thread-1,5,main] i = 4
Thread[Thread-0,5,main] i = 4
Thread[Thread-0,5,main] i = 5
Thread[Thread-1,5,main] i = 5
Thread[Thread-1,5,main] i = 6
Thread[Thread-0,5,main] i = 6
Thread[Thread-0,5,main] i = 7
Thread[Thread-1,5,main] i = 7
Thread[Thread-0,5,main] i = 8
Thread[Thread-1,5,main] i = 8
Thread[Thread-1,5,main] i = 9
Thread[Thread-0,5,main] i = 9
 */
}
