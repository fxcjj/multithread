package com.vic.multithread.d.c02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CasTest {
	
	AtomicInteger atomicInteger = new AtomicInteger();
	private int i;
	
	public static void main(String[] args) {
		
		CasTest ct = new CasTest();
		
		long start = System.currentTimeMillis();
		
		List<Thread> list = new ArrayList<Thread>();
		
		for(int i = 0; i < 100; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for(int j = 0; j < 10000; j++) {
						ct.safeIncr();
						ct.unsafeIncr();
					}
				}
			});
			list.add(t);
		}
		
		for(Thread t : list) {
			t.start();
		}
		
		//等待所有线程执行完毕
		for(Thread t : list) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(ct.i);
		System.out.println(ct.atomicInteger.get());
		System.out.println("elapsed time: " + (System.currentTimeMillis() - start));
		
		
		
	}
	
	/**
	 * 使用cas线程安全计数器
	 */
	public void safeIncr() {
		for(;;) {
			int i = atomicInteger.get();
			boolean suc = atomicInteger.compareAndSet(i, ++i);
			if(suc) {
				break;
			}
		}
	}
	
	/**
	 * 非线程安全
	 */
	public void unsafeIncr() {
		i++;
	}

}
