package com.vic.multithread.c;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Priority {
	
	static volatile boolean notStart = true;
	static volatile boolean notEnd = true;
	
	public static void main(String[] args) throws InterruptedException {
		List<Job> jobs = new ArrayList<Job>();
		for(int i = 0; i < 10; i++) {
			int p = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
			Job job = new Job(p);
			jobs.add(job);
			Thread t = new Thread(job, "Thread:"+i);
			t.setPriority(p); //set priority
			t.start();
		}
		notStart = false;
		TimeUnit.SECONDS.sleep(10);
		notEnd = false;
		
		for(Job j : jobs) {
			System.out.println("Job Priority: "+j.priority+", Count: "+j.jobCount);
		}
	}
	
	static class Job implements Runnable {
		
		int priority;
		long jobCount;
		
		public Job(int p) {
			this.priority = p;
		}
		
		public void run() {
			while(notStart) {
				Thread.yield();
			}
			while(notEnd) {
				Thread.yield();
				jobCount++;
			}
		}
	}

}
