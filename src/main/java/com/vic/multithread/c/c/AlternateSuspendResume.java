package com.vic.multithread.c.c;
/**
 * 
 * @author Victor
*/
public class AlternateSuspendResume implements Runnable {
	
	private volatile int firstVal;
	private volatile int secondVal;
	//增加标志位，用来实现线程的挂起和恢复
	//使用volatile保证了共享变量的可见性！
	private volatile boolean suspended;
	
	//判断两者是否相等
	public boolean areValuesEqual() {
		return firstVal == secondVal;
	}
	
	@Override
	public void run() {
		try {
			suspended = false;
			firstVal = 0;
			secondVal = 0;
			workMethod();
		} catch (InterruptedException e) {
			System.out.println("interrupted while in workMethod()");
		}
	}
	
	public void workMethod() throws InterruptedException {
		int val = 1;
		while(true) {
			
			//仅当线程挂起时才执行
			waitWhileSuspended();
			
			stepOne(val);
			stepTwo(val);
			val++;
			
			Thread.sleep(200);
			
			//仅当线程挂起时才执行
			waitWhileSuspended();
		}
	}
	
	public void stepOne(int newVal) throws InterruptedException {
		firstVal = newVal;
		Thread.sleep(300);
	}
	
	public void stepTwo(int newVal) {
		secondVal = newVal;
	}
	
	public void suspendRequest() {
		suspended = true;
	}
	
	public void resumeRequest() {
		suspended = false;
	}
	
	public void waitWhileSuspended() throws InterruptedException {
		//这是一个“繁忙等待”技术的示例。  
        //它是非等待条件改变的最佳途径，因为它会不断请求处理器周期地执行检查，   
        //更佳的技术是：使用Java的内置“通知-等待”机制  
		while(suspended) {
			Thread.sleep(200);
		}
	}
	
	public static void main(String[] args) {
		AlternateSuspendResume asr = new AlternateSuspendResume();
		Thread t = new Thread(asr);
		t.start();
		
		//休眠1秒，让其他线程有机会获得执行
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { }
		
		for(int i = 0; i < 10; i++) {
			asr.suspendRequest();
			
			/**让线程有机会注意到挂起请求
				注意：这里休眠时间一定要大于stepOne操作对firstVal赋值后的休眠时间，即300ms,
				目的是为了防止在执行asr.areValuesEqual()进行比较时，恰逢stepOne操作执行完，而stepTwo操作还没执行
			 */
			try {
				Thread.sleep(450);
			} catch (InterruptedException e) { }
			
			System.out.println("asr.areValuesEqual() = " + asr.areValuesEqual());
			
			asr.resumeRequest();
			
			try {   
                //线程随机休眠0~2秒  
                Thread.sleep(( long ) (Math.random() * 2000.0) );  
            } catch ( InterruptedException x ) {  
            }  
		}
		
		System.exit(0); //中断应用程序
	}

}
