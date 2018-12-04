package com.vic.multithread.c.sw;

public class MissedNotify {
	
	private Object proceedLock;
	
	//该标志位用来标志线程是否需要等待
	private boolean okToProceed;
	
	public MissedNotify() {
		print("in MissedNotify()");
		proceedLock = new Object();
		//初始为false，不设置可以否？因为boolean默认的就是false
		//okToProceed = false;
		System.out.println(okToProceed);
	}
	
	public void waitToProceed() throws InterruptedException {
		print("in waitToProceed() - entered");
		synchronized (proceedLock) {
			//while循环判断，这里不用if的原因是为了防止早期通知
			while(okToProceed == false) {
				print("in waitToProceed() - about to wait()");
				proceedLock.wait();
				print("in waitToProceed() - back from wait()");
			}
		}
		print("in waitToProceed() - leaving");
	}
	
	public void proceed() {
		print("in proceed() - entered");  
        synchronized ( proceedLock ) {  
            print("in proceed() - about to notifyAll()");
            //如果此句先于wait()执行，则wait()方法不会执行，因为为false时wait()方法才会执行
            //如果此句后于wait()执行，则一切正常，该对象从等待池中唤醒，进行竞争对象锁行列
            okToProceed = true;
            proceedLock.notifyAll();  
            print("in proceed() - back from notifyAll()");  
        }  
        print("in proceed() - leaving");  
	}
	
	private static void print(String msg) {
		String name = Thread.currentThread().getName();
		System.out.println(name + ": " + msg); 
	}
	
	public static void main(String[] args) {
		final MissedNotify mn = new MissedNotify();
		Runnable runA = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(400);
					mn.waitToProceed();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread threadA = new Thread(runA, "threadA");  
        threadA.start();
        
        Runnable runB = new Runnable() {
			@Override
			public void run() {
				try {
					//此句先执行，则会造成notify的遗漏
					Thread.sleep(500);
					mn.proceed();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread threadB = new Thread(runB, "threadB");  
        threadB.start();
        
        try {   
            Thread.sleep(10000);
        } catch ( InterruptedException x ) {}
        
      //试图打断wait阻塞  
      print("about to invoke interrupt() on threadA");  
      threadA.interrupt();
	}
	
}
