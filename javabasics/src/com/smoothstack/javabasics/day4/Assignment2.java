package com.smoothstack.javabasics.day4;

public class Assignment2 {
	private static String s1 = "Lock 1";
	private static String s2 = "Lock 2";
	
	public static void main(String[] args) {
		//Basic deadlock; t1 waits for s2 while t2 waits for s1
		Runnable l1 = new Runnable() {
			@Override
			public void run() {
				try{
					synchronized (s1) {
						Thread.sleep(1000);
						synchronized(s2) {
							System.out.println("thread1 done!");
						}
					}
				}catch(Exception e) {
					
				}
			}
		};
		
		Runnable l2 = new Runnable() {
			@Override
			public void run() {
				try {
					synchronized (s2) {
						Thread.sleep(1000);
						synchronized(s1) {
							System.out.println("thread2 done!");
						}
					}
				}catch(Exception e) {
					
				}
			}
		};
		Thread t1 = new Thread(l1);
		Thread t2 = new Thread(l2);
		t1.start();
		t2.start();
	}
}
