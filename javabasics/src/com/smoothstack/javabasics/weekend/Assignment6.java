package com.smoothstack.javabasics.weekend;

public class Assignment6 {
	public static class SampleSingleton {
		private SampleSingleton() {}
		
		private static Object conn = null;

		private static SampleSingleton instance = null;

		public static SampleSingleton getInstance() {
			//Double checked for safety
			if(instance == null) {
				synchronized (conn) {
					if (instance == null) {
						instance = new SampleSingleton();
					}
				}
			}
			return instance;
		}
	}
}
