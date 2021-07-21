/**
 * 
 */
package com.smoothstack.javabasics.day1;

/**
 * @author Joshua Green 7/19/2021
 *
 */
public class Assignment1 {

	private static void printDots(int ln) {
		for(int i = 0; i < ln+8; i++) {
			System.out.print(".");
		}
		System.out.print("\n");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Generate text, use cases to loop through diff behavior
		for(int i = 1; i <= 4; i++) {
			System.out.print(i);
			System.out.println(")");
			switch(i) {
			case(1):
				for(int j = 0; j < 4; j++) {
					for(int k = 0; k <= j; k++) {
						System.out.print("*");
					}
					System.out.print("\n");
				}
				printDots(i);
				break;
			case(2):
				printDots(i);
				for(int j = 4; j > 0; j--) {
					for(int k = j; k > 0; k--) {
						System.out.print("*");
					}
					System.out.print("\n");
				}
				break;
			case(3):
				for(int j = 0; j < 4; j++) {
					for(int k = 0; k < 5-j; k++) {
						System.out.print(" ");
					}
					for(int k = 0; k < 2*j+1; k++) {
						System.out.print("*");
					}
					System.out.print("\n");
				}
				printDots(i);
				break;
			case(4):
				printDots(i);
				for(int j = 3; j >= 0; j--) {
					for(int k = 0; k < 5-j; k++) {
						System.out.print(" ");
					}
					for(int k = 0; k < 2*j+1; k++) {
						System.out.print("*");
					}
					System.out.print("\n");
				}
			}
		}	
		return;
	}
}
