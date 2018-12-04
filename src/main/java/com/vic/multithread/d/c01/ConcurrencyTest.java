package com.vic.multithread.d.c01;

/**
 * 串行和并行的测试
 * @author Victor
 * @date 2018年3月2日 上午10:05:58
 */
public class ConcurrencyTest {
	
	private static final long count = 1000000000L;
	
	public static void main(String[] args) throws Exception {
		
		/*
		 * conut = 10000L 1万
		 * concurrency : 1ms, b = -10000
		 * serial : 0ms, b = -10000, a = 50000
		 * 
		 * conut = 100000L 10万
		 * concurrency : 3ms, b = -100000
		 * serial : 3ms, b = -100000, a = 500000
		 * 
		 * conut = 1000000L 1百万
		 * concurrency : 6ms, b = -1000000
		 * serial : 8ms, b = -1000000, a = 5000000
		 * 
		 * conut = 10000000L 1千万
		 * concurrency : 18ms, b = -10000000
		 * serial : 25ms, b = -10000000, a = 50000000
		 * 
		 * conut = 100000000L 1亿
		 * concurrency : 87ms, b = -100000000
		 * serial : 137ms, b = -100000000, a = 500000000
		 * 
		 * conut = 1000000000L 10亿
		 * concurrency : 915ms, b = -1000000000
		 * serial : 1172ms, b = -1000000000, a = 705032704
		 */
		concurrency();
		
		serial();
	}

	/**
	 * 并发
	 * @throws InterruptedException 
	 */
	private static void concurrency() throws InterruptedException {
		long start = System.currentTimeMillis();
		Thread thread = new Thread(new Runnable() {
			public void run() {
				int a = 0;
				for(int i =0; i < count; i++) {
					a += 5;
				}
			}
		});
		thread.start();
		
		int b = 0;
		for(int i = 0; i < count; i++) {
			b--;
		}
		long time = System.currentTimeMillis() - start;
		thread.join();
		System.out.println("concurrency : " + time + "ms, b = " + b);
		
	}

	/**
	 * 串行
	 */
	private static void serial() {
		long start = System.currentTimeMillis();
		int a = 0;
		for(int i =0; i < count; i++) {
			a += 5;
		}
		
		int b = 0;
		for(int i = 0; i < count; i++) {
			b--;
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("serial : " + time + "ms, b = " + b + ", a = " + a);
	}
}
