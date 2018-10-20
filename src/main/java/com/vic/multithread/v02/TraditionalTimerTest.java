package com.vic.multithread.v02;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {
	
	static int count = 0;
	
	public static void main(String[] args) {
		
		//在Timer的constructor里面初始化和启动了线程
		//1
		/*new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " bombing!");
			}
			
		}, 5000, 3000);*/
		
		//2
		/*class MyTimerTask extends TimerTask {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " bombing!");
				count = (count+1) % 2;
				new Timer().schedule(new MyTimerTask(), 2000 + 2000 * count);
			}
			
		}
		
		new Timer().schedule(new MyTimerTask(), 2000);*/
		
		//3
		test3();
		
		while(true) {
			System.out.println(LocalTime.now().getSecond());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class MyTimerTask1 extends TimerTask {
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " bombing!");
			new Timer().schedule(new MyTimerTask2(), 2000 + 2000);
		}
		
	}
	
	class MyTimerTask2 extends TimerTask {
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " bombing!");
			new Timer().schedule(new MyTimerTask1(), 2000);
		}
		
	}
	
	private static void test3() {
		
		TraditionalTimerTest x = new TraditionalTimerTest();
		
		new Timer().schedule(x.new MyTimerTask1(), 2000);
		
		while(true) {
			System.out.println(LocalTime.now().getSecond());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	
}
