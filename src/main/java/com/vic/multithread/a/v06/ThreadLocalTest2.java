package com.vic.multithread.a.v06;

import java.util.Random;

public class ThreadLocalTest2 {
	
	
	static ThreadLocal<User> tl = new ThreadLocal<User>();
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				public void run() {
					//random number
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName()
							+ " has put data: " + data);
					User user = new User();
					user.setAge(data);
					user.setName("name" + data);
					tl.set(user);
					new A().get();
					new B().get();
				}
				
			}).start();
		}
	}
	
	static class A {
		public void get() {
			User user = tl.get();
			System.out.println("A from " + Thread.currentThread().getName() + " get data: " + user.getName() + ", " + user.getAge());
		}
	}
	
	static class B {
		public void get() {
			User user = tl.get();
			System.out.println("B from " + Thread.currentThread().getName() + " get data: " + user.getName() + ", " + user.getAge());
		}
	}
}

class User {
	
	private String name;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
