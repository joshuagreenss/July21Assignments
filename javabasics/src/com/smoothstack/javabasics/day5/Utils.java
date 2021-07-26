/**
 * 
 */
package com.smoothstack.javabasics.day5;

/**
 * @author joshu
 *
 */
public interface Utils {
	public static int findE(String s1, String s2) {
		if(s1.contains("e") && !s2.contains("e")) {
			return -1;
		}
		else if(s2.contains("e") && !s1.contains("e")) {
			return 1;
		}
		else return 1;
	}
}
