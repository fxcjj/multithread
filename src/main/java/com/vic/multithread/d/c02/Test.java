package com.vic.multithread.d.c02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) {
		/*while(true) {
			
		}*/
		
		ExecutorService ex = Executors.newFixedThreadPool(1);
		ex.execute(new TestTask());
		
	}
	
}

class TestTask implements Runnable {
	
	@Override
	public void run() {
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

