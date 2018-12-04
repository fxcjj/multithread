package com.vic.multithread.d.c09;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskBusyWithoutResult implements Runnable {
	
	//等待队列
	static BlockingQueue blockingQueue = new ArrayBlockingQueue<>(1);
	static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 1L, TimeUnit.MINUTES, blockingQueue);
	
	public TaskBusyWithoutResult() {
	}
	
	@Override
	public void run() {
		System.out.println("Thread: " + Thread.currentThread() + " start run");
		
		int i = 10000 * 10000;
		while(i > 0) {
			i--;
		}
		System.out.println("Thread: " + Thread.currentThread() + " end run");
	}
	
	
	public static void main(String[] args) {
		test01();
	}
	
	private static void test01() {
		for(int i = 0; i < 20; i++) {
			TaskBusyWithoutResult task = new TaskBusyWithoutResult();
			threadPoolExecutor.submit(task);
		}
//		threadPoolExecutor.shutdownNow();
	}
}
