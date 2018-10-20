package com.vic.multithread.v04;

/**
 * mine
 * @author Victor
 *
 */
public class Interview1 {
	
	public static void main(String[] args) {
		int count = 50;
		
		A a = new A();
		
		for(int i = 0; i < count; i++) {
			
			synchronized (Interview1.class) {
				Thread thread = new Thread(a);
				thread.start();
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				for(int j = 0; j < 50; j++) {
					System.out.print(j);
				}
				System.out.println();
			}
			
			
			
		}
	}
	
}


class A implements Runnable {
	
	public void run() {
		synchronized (this) {
			for(int i = 0; i < 10; i++) {
				System.out.print(i);
			}
			System.out.println();
		}
		
	}
	
}