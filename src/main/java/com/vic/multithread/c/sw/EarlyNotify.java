package com.vic.multithread.c.sw;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EarlyNotify {

	private List list;
	
	public EarlyNotify() {
		list = Collections.synchronizedList(new LinkedList());
	}
	
	public String removeItem() throws InterruptedException {
		print("in removeItem() - entering");
		synchronized (list) {
			//if(list.isEmpty()) { //这里用if语句会发生危险
			while(list.isEmpty()) {
				print("in removeItem() - about to wait()");
                list.wait();
                print("in removeItem() - done with wait()");
			}
			//remove element
			String item = (String) list.remove(0);
			print("in removeItem() - leaving");
			return item;
		}
	}
	
	public void addItem(String item) {
		print("in addItem() - entering");
		synchronized (list) {
			//add element
			list.add(item);
			print("in addItem() - just added: '" + item + "'");
			
			//添加后，通知所有线程
			list.notifyAll();
			print("in addItem() - just notified");
		}
		print("in addItem() - leaving");
	}
	
	private static void print(String msg) {
		String name = Thread.currentThread().getName();
		System.out.println(name + ": " + msg); 
	}
	
	public static void main(String[] args) {
		final EarlyNotify en = new EarlyNotify();
		
		Runnable runA = new Runnable(){
			@Override
			public void run() {
				try {
					String item = en.removeItem();
					print("in run() - returned: '" + item + "'");
				} catch (InterruptedException e) {
					print("interrupted!");
				} catch ( Exception x ) {
					print("threw an Exception!!!\n" + x);
				}
			}
		};
		
		Runnable runB = new Runnable(){
			@Override
			public void run() {
				en.addItem("hello");
			}
		};
		
		
		try {
			
			//启动第一个删除元素的线程
			Thread threadA1 = new Thread(runA, "threadA1");
			threadA1.start();
			
			Thread.sleep(500);
			
			//启动第二个删除元素的线程
			Thread threadA2 = new Thread(runA, "threadA2");
			threadA2.start();
			
			Thread.sleep(500);
			
			//启动增加元素的线程
			Thread threadB = new Thread(runB, "threadB");
			threadB.start();
			
			Thread.sleep(10000); // wait 10 seconds
			
            threadA1.interrupt();
            threadA2.interrupt();  
		} catch (InterruptedException e) {
		}
	}
}
