/**
 * 
 */
package com.smoothstack.javabasics.day1;
import java.util.*;

/**
 * @author joshu
 *
 */
public class Assignment2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		Scanner reader = new Scanner(System.in);
		int myVal = rand.nextInt(100) + 1;
		int guesses = 0;
		int guess;
		System.out.println("Guess what number I'm thinking!");
		while(guesses < 5) {
			guess = reader.nextInt();
			if(guess >= myVal - 10 && guess <= myVal + 10) {
				System.out.println("Close enough, my number was " + String.valueOf(myVal) + ".");
				reader.close();
				return;
			}
			guesses++;
			if(guesses < 5) {
				System.out.println("Too far, keep guessing!");
			}
		}
		System.out.println("Too many guesses, my number was " + String.valueOf(myVal) + ".");
		reader.close();
		return;
	}

}
