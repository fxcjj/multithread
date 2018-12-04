package com.vic.multithread.c.sw;

/**
 * 线程这一概念，可以理解成进程中的一个小单元。这个单元是一个独立的执行单元，
 * 但是与进程中的其他线程共享进程中的内存单元。
 * 由于Cpu资源是有限的，所以进程中的多个线程要抢占Cpu，这也导致进程中的多个线程交替执行。
 * Thread.Sleep() 本身的含义是当前线程挂起一定时间。
 * Thread.Sleep(0) MSDN上的解释是挂起此线程能使其他等待线程执行。这样的解释容易导致误解，
 * 我们可以这样理解，其实是让当前线程挂起，使得其他线程可以和当前线程再次的抢占Cpu资源。
 * @author Victor
 */
public class TestSleep2 implements Runnable {
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				System.out.println("TestSleep2 i = " + i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {

		TestSleep2 ts = new TestSleep2();
		Thread t = new Thread(ts);
		t.start();
		
		for(int i = 0; i < 100; i++) {
			System.out.println("main i = " + i);
			Thread.sleep(80);
		}
/*output:
main i = 0
TestSleep2 i = 0
main i = 1
TestSleep2 i = 1
main i = 2
TestSleep2 i = 2
main i = 3
TestSleep2 i = 3
main i = 4
TestSleep2 i = 4
main i = 5
main i = 6
TestSleep2 i = 5
main i = 7
TestSleep2 i = 6
main i = 8
TestSleep2 i = 7
main i = 9
TestSleep2 i = 8
main i = 10
main i = 11
TestSleep2 i = 9
main i = 12
...
TestSleep2 i = 94
TestSleep2 i = 95
TestSleep2 i = 96
TestSleep2 i = 97
TestSleep2 i = 98
TestSleep2 i = 99
*/
/*
 * 由于TestSleep2线程的休眠时间（100）大于main线程的休眠时间（80），所以main线程会有更多的执行机会，从打印结果也可以看得出来！
 */
	}
}
