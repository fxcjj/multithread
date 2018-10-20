package com.vic.multithread.v07;

public class IncreDecre {

	public static void main(String[] args) {
		
		final IncreDecreProcesser p = new IncreDecreProcesser();
		
		for(int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				public void run() {
					p.incre();
				}
			}).start();
			
			new Thread(new Runnable() {
				public void run() {
					p.decre();
				}
			}).start();
		}
		
	}
	
}

class IncreDecreProcesser {
	
	private int i;
	
	public synchronized void decre() {
		i--;
		System.out.println(Thread.currentThread().getName() + " decre: " + i);
	}
	
	public synchronized void incre() {
		i++;
		System.out.println(Thread.currentThread().getName() + " incre: " + i);
	}
}
