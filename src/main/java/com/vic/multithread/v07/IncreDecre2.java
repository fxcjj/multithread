package com.vic.multithread.v07;

public class IncreDecre2 {

	public static void main(String[] args) {
		
		ShareData sd = new ShareData();
		new Thread(new Inc(sd)).start();
		new Thread(new Dec(sd)).start();
	}
	
}

class ShareData {
	
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

class Inc implements Runnable {
	
	ShareData sd;
	public Inc(ShareData sd) {
		this.sd = sd;
	}
	
	public void run() {
		while(true) {
			sd.incre();
		}
	}
}

class Dec implements Runnable {
	
	ShareData sd;
	public Dec(ShareData sd) {
		this.sd = sd;
	}
	
	public void run() {
		while(true) {
			sd.decre();
		}
	}
}
