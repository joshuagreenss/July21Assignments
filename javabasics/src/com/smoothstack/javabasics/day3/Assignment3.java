/**
 * 
 */
package com.smoothstack.javabasics.day3;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author joshu
 *
 */
public class Assignment3 {

	/**
	 * @param Only argument should be a single character
	 * 		  If a string, it will grab first character of string
	 */
	public static void main(String[] args) {
		// Reads lorem ipsum text, finds number of times given character is found in text
		String infile = "resources/day3/a3input.txt";
		String line = null;
		Integer count = 0;
		Character target = args[0].toCharArray()[0];
		try(BufferedReader br = new BufferedReader(new FileReader(infile))){
			line = br.readLine();
			do{
				for(Character c : line.toCharArray()) {
					if(target.equals(c)) {
						count++;
					}
				}
				line = br.readLine();
			}while(line != null);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Character " + args[0] + " found in the text " + count + " times");
	}

}
