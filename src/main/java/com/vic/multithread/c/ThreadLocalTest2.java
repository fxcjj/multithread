package com.vic.multithread.c;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SimpleDateFormat是线程不安全的
 * 执行会报如下错误
 * Exception in thread "pool-1-thread-127" java.lang.NumberFormatException: For input string: ""
 * Exception in thread "pool-1-thread-125" java.lang.NumberFormatException: multiple points
 * 
 * 使用ThreadLocal解决！
 * 见ThreadLocalTest3.java文件
 * @author Victor
 *
 */
public class ThreadLocalTest2 {

	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static class ParseDate implements Runnable {
		int i = 0;
		public ParseDate(int i) {
			this.i = i;
		}
		
		@Override
		public void run() {
			try {
					Date t = sdf.parse("2016-02-16 17:00:" + i % 60);
					System.out.println(i + ":" + t);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws ParseException {
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i = 0; i < 1000; i++) {
			es.execute(new ParseDate(i));
		}
	}
}



