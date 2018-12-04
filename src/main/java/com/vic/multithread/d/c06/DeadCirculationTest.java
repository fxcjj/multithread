package com.vic.multithread.d.c06;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DeadCirculationTest {

	public static void main(String[] args) throws InterruptedException {
		
		final Map<String, String> map = new HashMap<String, String>();
		Thread t = new Thread(new Runnable() {
			
			public void run() {
				for(int i = 0; i < 10000; i++) {
					new Thread(new Runnable() {
						
						public void run() {
							map.put(UUID.randomUUID().toString(), "");
						}
						
					}, "abc"+i).start();
				}
			}
			
		}, "abc");
		
		t.start();
		t.join();
		
	}

}
