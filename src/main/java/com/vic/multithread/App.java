package com.vic.multithread;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		App app = new App();
		System.out.println(app.hashCode());
		
		
		App app1 = new App();
		System.out.println(app1.hashCode());
	}
}
