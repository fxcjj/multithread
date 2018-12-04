package com.vic.multithread.c;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
	
	private AtomicInteger atomicI = new AtomicInteger(0);
	private int i = 0;
	
	public static void main(String[] args) throws Exception {
		
		final Counter cas = new Counter();
		
		List<Thread> ts = new ArrayList<Thread>(600);
		
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < 100; i++) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					for(int i = 0; i < 10000; i++) {
						cas.count();
						cas.safeCount();
					}
				}
			});
			ts.add(t);
		}
		
		for(Thread t : ts) {
			t.start();
		}
		
		//等待所有线程执行完毕
		for(Thread t : ts) {
			t.join();
		}
		
		System.out.println(cas.i);
		System.out.println(cas.atomicI.get());
		System.out.println(System.currentTimeMillis() - start);
	}

	protected void safeCount() {
		for(;;) {
			int i = atomicI.get();
			boolean suc = atomicI.compareAndSet(i, ++i);
			if(suc) {
				break;
			}
		}
	}

	protected void count() {
		i++;
	}

}
