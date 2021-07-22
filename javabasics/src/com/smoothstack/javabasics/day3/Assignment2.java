/**
 * 
 */
package com.smoothstack.javabasics.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
		String appendText = "I'm being appended!";
		String outfile = "resources/day3/a2output.txt";
		try {
			Files.write(Paths.get(outfile), appendText.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
