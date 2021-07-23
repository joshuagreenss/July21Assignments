package com.smoothstack.javabasics.day4;

public class Assignment1Singleton {
	private static Assignment1Singleton instance;
	Assignment1Singleton(){
		
	}
	public static Assignment1Singleton getInstance() {
		if(instance == null) {
			synchronized (Assignment1Singleton.class){
				if(instance == null) {
					instance = new Assignment1Singleton();
				}
			}
		}
		return instance;
	}
}
