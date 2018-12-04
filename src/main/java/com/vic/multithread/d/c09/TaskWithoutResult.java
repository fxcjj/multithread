package com.vic.multithread.d.c09;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskWithoutResult implements Runnable {
	
	//等待队列
	static BlockingQueue blockingQueue = new ArrayBlockingQueue<>(10);
	static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 1L, TimeUnit.MINUTES, blockingQueue);
	
	private int sleepTime = 1000;
	
	public TaskWithoutResult() {
	}
	
	public TaskWithoutResult(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	@Override
	public void run() {
		System.out.println("Thread: " + Thread.currentThread() + " start run");
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) { //catch
			System.out.println("Thread: " + Thread.currentThread() + "was interrupted");
		}
		System.out.println("Thread: " + Thread.currentThread() + " end run");
	}
	
	
	public static void main(String[] args) {
		test01();
	}
	
	/**
	 * interrupt test
	 */
	private static void test01() {
		for(int i = 0; i < 10; i++) {
			TaskWithoutResult task = new TaskWithoutResult(1000);
			threadPoolExecutor.submit(task);
		}
		/*
		 * don't trigger interrupt
		 */
//		threadPoolExecutor.shutdown();
		
		
		/*
		 * will trigger interrupt
		 */
		threadPoolExecutor.shutdownNow();
	}
}
