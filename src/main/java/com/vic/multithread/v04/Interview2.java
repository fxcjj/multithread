package com.vic.multithread.v04;

public class Interview2 {
	
	public static void main(String[] args) {
		int count = 50;
		
		Business a = new Business();
		
		for(int i = 0; i < count; i++) {
			a.sub();
			a.main();
		}
	}
	
}


class Business {
	
	boolean bShouldSub = true;
	
	public synchronized void sub() {
		while(!bShouldSub) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < 10; i++) {
			System.out.print(i);
		}
		System.out.println();
		
		bShouldSub = false;
		this.notify();
	}
	
	public synchronized void main() {
		
		while(bShouldSub) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i = 0; i < 50; i++) {
			System.out.print(i);
		}
		System.out.println();
		
		bShouldSub = true;
		this.notify();
	}
}