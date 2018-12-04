package com.vic.multithread.a.v01;

public class TraditionalThread {
	
	public static void main(String[] args) {
		
		Thread thread = new Thread(){
			
			public void run() {
				while(true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("1: " + Thread.currentThread().getName());
					System.out.println("2: " + this.getName());
				}
				
			};
			
		};
		thread.start();
		
		Thread thread2 = new Thread(new Runnable() {
			
			public void run() {
				while(true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("1: " + Thread.currentThread().getName());
				}
			}
		});
				
		thread2.start();		
		
		//执行子类的方法
		new Thread(new Runnable() {
			
			public void run() {
				while(true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("1: " + Thread.currentThread().getName());
				}
			}
			
		}) {
			
			//覆盖父类方法
			public void run() {
				while(true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("1: " + Thread.currentThread().getName());
				}
			}
		}.start();
	}
	
	
}
