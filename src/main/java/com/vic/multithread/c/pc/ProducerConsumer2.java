package com.vic.multithread.c.pc;

public class ProducerConsumer2 {

		public static void main(String[] args) {
			Data data = new Data();
			Producer2 p = new Producer2(data);
			Consumer2 c = new Consumer2(data);
			new Thread(p).start();
			new Thread(c).start();
		}
}

class Data {
	String name;
	boolean flag;
	Object lock = new Object();
	
	//produce
	public void set(String name) {
		synchronized (lock) {
			while(flag) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			setName(name);
			System.out.println("produce: " + name);
			flag = true;
			lock.notify();
		}
	}
	
	public void get() {
		synchronized (lock) {
			while(!flag) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("consume: " + getName());
			flag = false;
			lock.notify();
		}
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

class Producer2 implements Runnable {
	Data data;	
	public Producer2(Data data) {
		this.data = data;
	}
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			data.set("name"+i);
		}
		
	}
}

class Consumer2 implements Runnable {
	Data data;	
	public Consumer2(Data data) {
		this.data = data;
	}
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			data.get();
		}
	}
}