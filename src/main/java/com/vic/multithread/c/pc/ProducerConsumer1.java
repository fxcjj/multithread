package com.vic.multithread.c.pc;

import java.util.LinkedList;

public class ProducerConsumer1 {

	public static void main(String[] args) {
		Storage storage = new Storage();
		
		Producerb p1 = new Producerb(storage);
		Producerb p2 = new Producerb(storage);
		Producerb p3 = new Producerb(storage);
		Producerb p4 = new Producerb(storage);
		Producerb p5 = new Producerb(storage);
		Producerb p6 = new Producerb(storage);
		p1.setNum(10);
		p2.setNum(10);
		p3.setNum(10);
		p4.setNum(10);
		p5.setNum(10);
		p6.setNum(80);
		
		Consumerb c1 = new Consumerb(storage);
		Consumerb c2 = new Consumerb(storage);
		Consumerb c3 = new Consumerb(storage);
		c1.setNum(50);
		c2.setNum(20);
		c3.setNum(30);
		
		new Thread(p1).start();
		new Thread(p2).start();
		new Thread(p3).start();
		new Thread(p4).start();
		new Thread(p5).start();
		new Thread(p6).start();
		
		new Thread(c1).start();
		new Thread(c2).start();
		new Thread(c3).start();
	}

}

/**
 * 生产者
 */
class Producerb implements Runnable {
	
	public Producerb(Storage storage) {
		this.storage = storage;
	}
	
	//生产产品数量
	private int num;
	
	//仓库
	private Storage storage;
	
	@Override
	public void run() {
		produce(num);
	}
	
	//调用仓库的生产
	private void produce(int num) {
		storage.produce(num);
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
}

/**
 * 消费者
 */
class Consumerb implements Runnable {

	public Consumerb(Storage storage) {
		this.storage = storage;
	}
	
	//消费产品数量
	private int num;
	
	//仓库
	private Storage storage;
	
	@Override
	public void run() {
		consume(num);
	}

	// 调用仓库的消费
	private void consume(int num) {
		storage.consume(num);
	}
	
	public void setNum(int num) {
		this.num = num;
	}
}

/**
 * 仓库
 */
class Storage {
	
	private static final int MAX_SIZE = 100;
	
	private LinkedList<Object> list = new LinkedList<Object>();
	
	// 生产num个产品
	void produce(int num) {
		// 同步代码块
		synchronized (list) {
			// 超过最大仓库容量，等待
			if(list.size() + num > MAX_SIZE) {
				System.out.println("【要生产的产品数量】:" + num + "/t【库存量】:"  
                        + list.size() + "/t暂时不能执行生产任务!");  
				try {
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 可以生产
			for(int i = 0; i < num; i++) {
				list.add(new Object());
			}
			System.out.println("【已经生产产品数】:" + num + "/t【现仓储量为】:" + list.size());  
			//唤醒所有在list上等待的线程
			list.notifyAll();
		}
	}
	
	// 消费num个产品
	void consume(int num) {
		// 同步代码块
		synchronized (list) {
			// 库存不足
			if(num > list.size()) {
				System.out.println("【要消费的产品数量】:" + num + "/t【库存量】:"  
                        + list.size() + "/t暂时不能执行消费任务!");
				try {
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for(int i = 0; i < num; i++) {
				list.remove();
			}
			System.out.println("【已经消费产品数】:" + num + "/t【现仓储量为】:" + list.size()); 
			list.notifyAll();
		}
	}

	public LinkedList<Object> getList() {
		return list;
	}

	public void setList(LinkedList<Object> list) {
		this.list = list;
	}
	
}