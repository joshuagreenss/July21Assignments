/**
 * 
 */
package com.smoothstack.javabasics.day2;

/**
 * @author Joshua Green
 *
 */
public class Assignment1 {

	/**
	 * @param args is list of numbers to sum
	 */
	public static void main(String[] args) {
		// Sums numbers from command line input
		int sum = 0;
		for(String val : args) {
			try {
				sum += Integer.parseInt(val);
			}
			catch(NumberFormatException ex) {
				System.out.println(val + " is not an integer.");
			}
		}	
		System.out.println("The sum of the integer inputs is " + sum);
	}

}
