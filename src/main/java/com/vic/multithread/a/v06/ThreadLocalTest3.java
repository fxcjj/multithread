package com.vic.multithread.a.v06;

import java.util.Random;

public class ThreadLocalTest3 {
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				public void run() {
					//random number
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName()
							+ " has put data: " + data);
					UserData.getThreadUserData().setAge(data);
					UserData.getThreadUserData().setName("name" + data);
					new A().get();
					new B().get();
				}
				
			}).start();
		}
	}
	
	static class A {
		public void get() {
			UserData user = UserData.getThreadUserData();
			System.out.println("A from " + Thread.currentThread().getName() + " get data: " + user.getName() + ", " + user.getAge());
		}
	}
	
	static class B {
		public void get() {
			UserData user = UserData.getThreadUserData();
			System.out.println("B from " + Thread.currentThread().getName() + " get data: " + user.getName() + ", " + user.getAge());
		}
	}
}

class UserData {
	
	private static ThreadLocal<UserData> tl = new ThreadLocal<UserData>();
	
	private UserData() {
	}
	
	public static synchronized UserData getThreadUserData() {
		UserData u = tl.get();
		if(u == null) {
			u = new UserData();
			tl.set(u);
		}
		return u;
	}
	
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
