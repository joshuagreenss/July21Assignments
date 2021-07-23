/**
 * 
 */
package com.smoothstack.javabasics.day4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author joshu
 *
 */
public class Assignment3 {

	/**
	 * @param args
	 */
	private static Integer items = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> buf = new ArrayList<>(10);
		Runnable producer = new Runnable() {
			public void run() {
				Integer value = 1;
				while(true) {
					if(items < 10) {
						synchronized(buf) {
							if(items < 10) {
								buf.add(value);
								items++;
							}
						}
					}
					else {
						try {
							Thread.sleep(1000);
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		Runnable consumer = new Runnable() {
			public void run() {
				Integer value = 0;
				while(true) {
					if(items > 0) {
						synchronized(buf) {
							if(items > 0) {
								value = buf.get(0);
								buf.remove(0);
								System.out.println(value);
								items--;
							}
						}
					}
					else {
						try {
							Thread.sleep(1000);
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		Thread p1 = new Thread(producer);
		Thread c1 = new Thread(consumer);
		p1.start();
		c1.start();
	}

}
