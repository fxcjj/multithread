package com.vic.multithread.c.pc;

/**
 * 生产者-消费费
 * @author Victor
 *
 */
public class TestProducerConsumer {

	public static void main(String[] args) {
		Info info = new Info();
		Producer producer = new Producer(info);
		new Thread(producer).start();
		
		Consumer consumer = new Consumer(info);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(consumer).start();
		
	}
}

class Info {
	
	private String name;
	private String content;
	
	private boolean remark = true;
	
	public synchronized void set(String name, String content) {
		while(!remark) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.setName(name);
		this.setContent(content);
		remark = false; //改变标志位，表示可以取走
		super.notify();
	}
	
	public synchronized void get() {
		while(remark) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(getName() + ", " + getContent());
		remark = true; //改变标志位，表示可以生产
		super.notify();
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	private void setContent(String content) {
		this.content = content;
	}
	
	private String getName() {
		return this.name;
	}
	
	private String getContent() {
		return this.name;
	}
	
}

class Producer implements Runnable {
	
	private Info info;
	public Producer(Info info) {
		this.info = info;
	}
	
	@Override
	public void run() {
		boolean flag = false;
		for(int i = 0; i < 10; i++) {
			if(!flag) {
				info.set("姓名--1","内容--1");
				flag = true;
			} else {
				info.set("姓名--2","内容--2");
				flag = false;
			}
		}
	}
	
}

class Consumer implements Runnable {
	
	private Info info;
	public Consumer(Info info) {
		this.info = info;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			info.get();
		}
	}
	
}
