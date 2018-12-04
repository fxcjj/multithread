package com.vic.multithread.c.d;

/**
 * 
 * @author Victor
 */
public class Volatile implements Runnable {

	private int value;
	private volatile boolean missedIt;
	private long creationTime;

	public Volatile() {
		value = 10;
		missedIt = false;
		creationTime = System.currentTimeMillis();
	}

	@Override
	public void run() {
		print("entering run()");

		while (value < 20) {
			System.out.println("aaaaa");
			/**
			 * 这里的休眠好让workMehtod()方法执行，
			 * 为missedIt赋值为true，这样才会继续执行（中断）。
			 */
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (missedIt) {
				
				/**
				 * 应该记录是的10
				 */
				int curValue = value;

				Object lock = new Object();

				/**
				 * 同步块进入和离开会同步该线程的所有变量
				 */
				synchronized (lock) {
					// do nothing
				}

				/**
				 * 应该记录的是50
				 */
				int valueAfterSync = value;
				print("in run() - see value=" + curValue + ", but rumor has it that it changed!");
				print("in run() - valueAfterSync=" + valueAfterSync);
				break;
			}
		}

		print("leaving run()");
	}

	public void workMethod() throws InterruptedException {
		print("entering workMethod()");
		print("in workMethod() - about to sleep for 2 seconds");
		
		// 确保Volatile线程执行（if(missedIt)）
		//Thread.sleep(2000);
		
		/**
		 * 如果把missedIt放在value之前，那么程序将从if(missedId)break，符合之前的理论
		 * 
		 */
		//missedIt = true;
		
		
		
		/**
		 * 仅在此改变value的值
		 * 修改完之后，由于value没有被volatile修改，所以Volatitle线程不会察觉到它的变化
		 */
		value = 50;
		
		print("in workMethod() - just set value=" + value);
		print("in workMethod() - about to sleep for 5 seconds");
		//Thread.sleep(5000);
		/**
		 * 仅在此改变missedIt的值
		 * 由于missedIt是volatile的，所以立即会被Volatitle线程察觉到，那么在Volatile线程中就会break
		 */
		missedIt = true;

		
		print("in workMethod() - just set missedIt=" + missedIt);
		print("in workMethod() - about to sleep for 3 seconds");
		Thread.sleep(3000);
		print("leaving workMethod()");
	}

	private void print(String msg) {
		long interval = System.currentTimeMillis() - creationTime;
		String tmpStr = "    " + (interval / 1000.0) + "000";
		int pos = tmpStr.indexOf(".");
		String secStr = tmpStr.substring(pos - 2, pos + 4);
		String nameStr = "        " + Thread.currentThread().getName();
		nameStr = nameStr.substring(nameStr.length() - 8, nameStr.length());
		System.out.println(secStr + " " + nameStr + ": " + msg);
	}
	
	public static void main(String[] args) {
		try {  
            //通过该构造函数可以获取实时时钟的当前时间  
            Volatile vol = new Volatile();  
  
            //稍停100ms，以让实时时钟稍稍超前获取时间，使print（）中创建的消息打印的时间值大于0  
            Thread.sleep(100);    
  
            Thread t = new Thread(vol);  
            t.start();
  
            //休眠100ms，让刚刚启动的线程有时间运行  
            Thread.sleep(100);    
            //workMethod方法在main线程中运行  
            vol.workMethod();  
        } catch ( InterruptedException x ) {  
            System.err.println("one of the sleeps was interrupted");  
        }  
	}
}
