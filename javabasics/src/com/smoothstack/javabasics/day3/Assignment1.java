/**
 * 
 */
package com.smoothstack.javabasics.day3;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Joshua Green 7/21/2021
 *
 */
public class Assignment1 {

	/**
	 * @param No args
	 */
	private static void recurDirDive(Path p, int i) {
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(p)){
			for (Path curP : ds) {
				for(int j = 0; j < i; j++) {
					System.out.print('-');
				}
				System.out.println(curP.getFileName());
				if(Files.isDirectory(curP)) {
					recurDirDive(curP, i+1);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	public static void main(String[] args) {
		// Recursively dives into directories and lists them
		String name = "PATH_NAME_HERE";
		Path p = FileSystems.getDefault().getPath(name, args);
		recurDirDive(p, 0);
	}
}
