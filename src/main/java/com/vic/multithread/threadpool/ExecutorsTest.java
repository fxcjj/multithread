package com.vic.multithread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.vic.multithread.c.SleepUtils;

/**
 * JDK1.5的Executors
 *
 */
public class ExecutorsTest {

	public static void main(String[] args) throws Exception {
		
//    	testSingleThreadPoolExecutor();
    	
//    	testFixedThreadPool();
    	
//    	testCachedThreadPool();
    	
//    	testScheduledThreadPool();
    	
//    	testScheduledThreadPool1();
    	
//    	testSingleScheduledThreadPool();
		int c = 0;
		for(int i = 0; i < 10000; i++) {
			c += i;
		}
		
		SleepUtils.second(1);
		
		test();
		
		SleepUtils.second(1);
		System.out.println(c);
		
	}
	
	private static void test() throws Exception {
		
		new Thread(new Runnable() {
			
			public void run() {
				for(int i = 0; i < 10; i++) {
					System.out.println("send msg, no: "+i);
				}
			}
			
		}).start();
		
	}

	private static void testSingleScheduledThreadPool() {
    	ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
		pool.scheduleAtFixedRate(new Runnable(){
			public void run() {
				System.out.println("delay 1 seconds, and execute every 3 seconds");
			}
		}, 1, 2, TimeUnit.SECONDS);
	}

	private static void testScheduledThreadPool1() {
    	ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
    	pool.scheduleAtFixedRate(new Runnable(){
    		public void run() {
    			System.out.println("delay 1 seconds, and execute every 3 seconds");
    		}
    	}, 1, 3, TimeUnit.SECONDS);
	}

	private static void testScheduledThreadPool() {
    	ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
    	pool.schedule(new Runnable() {
    		public void run() {
    			System.out.println("delay 3 seconds");
    		}
    	}, 2, TimeUnit.SECONDS);
    	
	}

	private static void testCachedThreadPool() {
		ExecutorService pool = Executors.newCachedThreadPool();
		execute(pool, 5);
	}

	private static void testFixedThreadPool() {
    	ExecutorService pool = Executors.newFixedThreadPool(2);
    	execute(pool, 3);
	}

	//newSingleThreadExecutor
	private static void testSingleThreadPoolExecutor() {
		ExecutorService pool = Executors.newSingleThreadExecutor();
    	execute(pool, 3);
	}
	
	
	//执行pool
	private static void execute(ExecutorService pool, int count) {
		
		for(int i = 0; i < count; i++) {
			pool.execute(new Runnable() {
	    		public void run() {
	    			System.out.println(Thread.currentThread().getName());
	    		}
	    	});
		}
    	pool.shutdown();
	}
}
