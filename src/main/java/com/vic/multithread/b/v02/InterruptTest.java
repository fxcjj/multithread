package com.vic.multithread.b.v02;

public class InterruptTest {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				while(true) {
					if(Thread.currentThread().isInterrupted()) {
						System.out.println("interrupted");
						break;
					}
//					Thread.yield();
					System.out.println(Thread.currentThread().getName() + ", " + System.currentTimeMillis());
				}
			}
			
		});
		
		t1.start();
		
		try {
			Thread.sleep(1000);
			t1.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
