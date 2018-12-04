package com.vic.multithread.c;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {

	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		}).start();
		
		
		//获取Java线程管理MXBean
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		
		//不需要获取同步的monitor和synchronization信息，获取活线程和堆栈信息
		/**
		 * Returns the thread info for all live threads with stack trace and synchronization information. 
		 * Some threads included in the returned array may have been terminated when this method returns. 
		 */
		ThreadInfo[] dumpAllThreads = threadMXBean.dumpAllThreads(true, true);
		
		for(ThreadInfo ti : dumpAllThreads) {
			System.out.println(ti.getThreadId()+", "+ti.getThreadName());
		}
		
	}

}
