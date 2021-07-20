/**
 * 
 */
package com.smoothstack.javabasics.day2;

import java.util.Random;

/**
 * @author Joshua Green
 *
 */
public class Assignment2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Generate random 10x10 array, find largest value in array
		// Final position is 0 indexed
		int[][] arr = new int[10][10];
		int maxVal = -1, maxX = 0, maxY = 0;
		Random rand = new Random();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				arr[i][j] = rand.nextInt(1000);
			}
		}
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(arr[i][j] > maxVal) {
					maxVal = arr[i][j];
					maxX = i;
					maxY = j;
				}
			}
		}
		System.out.println("Highest value in array is " + maxVal + " at (" + maxX + ", " + maxY + ").");
		
	}
}
