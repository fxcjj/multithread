package com.vic.multithread.c;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest1 {

	public static void main(String[] args) {
		TestNum tn = new TestNum();
		
		//三个线程共享TestClient
		TestClient tc1 = new TestClient(tn);
		TestClient tc2 = new TestClient(tn);
		TestClient tc3 = new TestClient(tn);
		
		tc1.start();
		tc2.start();
		tc3.start();
		
	}
	
}

class TestNum {
	// 通过匿名类覆盖ThreadLocal的initialValue()方法
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			return 0;
		};
	};
	
	private static AtomicInteger num = new AtomicInteger();
	
	// 获取下一个序列值
	public int getNextNum() {
		seqNum.set(seqNum.get() + 1);
		return seqNum.get();
	}
	
	public int getNextNum1() {
		return num.incrementAndGet();
	}
}

class TestClient extends Thread {
	TestNum testNum;
	
	public TestClient(TestNum testNum) {
		this.testNum = testNum;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 3; i++) {
			//每个线程打印3个序列值
			System.out.println(Thread.currentThread().getName()+", num = " + testNum.getNextNum());
		}
	}
}
