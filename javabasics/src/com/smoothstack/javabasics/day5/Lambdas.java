/**
 * 
 */
package com.smoothstack.javabasics.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author joshu
 *
 */
public class Lambdas {

	/**
	 * @param args
	 */
	public static void printList(String[] strings) {
		for(String s : strings) {
			System.out.println(s);
		}
	}
	
	public static String evenOddMap(List<Integer> ints) {
		StringBuilder response = new StringBuilder();
		ints.stream().forEach(val -> {
			if(val%2 == 0) {
				response.append('e');
			}
			else {
				response.append('o');
			}
			response.append(val);
			response.append(',');
		});
		response.delete(response.lastIndexOf(","),response.length());
		return response.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] strings = {"This","is","a","list","of","strings","with","various","lengths","extra","words","added","for","more","sorting"};
		
		System.out.println("Sorted by length ascending");
		Arrays.sort(strings, (s1, s2) -> s1.length()-s2.length());
		printList(strings);
		
		System.out.println("Sorted by length descending");
		Arrays.sort(strings, (s1, s2) -> -(s1.length()-s2.length()));
		printList(strings);

		System.out.println("Sorted alphabetically");
		Arrays.sort(strings,(s1,s2) -> Character.toLowerCase(s1.charAt(0)) - Character.toLowerCase(s2.charAt(0)));
		printList(strings);
		
		System.out.println("Sorted by containing e");
		Arrays.sort(strings, (s1,s2) -> {
			if(s1.contains("e") && !s2.contains("e")) {
				return -1;
			}
			else if(s2.contains("e") && !s1.contains("e")) {
				return 1;
			}
			return 0;
		});
		printList(strings);
		
		System.out.println("Sorting by containing e with static method");
		Arrays.sort(strings, (s1, s2) -> s1.length()-s2.length());
		Arrays.sort(strings,(s1,s2)->Utils.findE(s1, s2));
		printList(strings);
		
		Random rand = new Random();
		List<Integer> intList = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			intList.add(rand.nextInt(100));
		}
		System.out.println(evenOddMap(intList));
		
		List<String> aList = new ArrayList<>();
		aList.add("Not");
		aList.add("all");
		aList.add("of");
		aList.add("these");
		aList.add("words");
		aList.add("are");
		aList.add("returned");
		aList.add("and");
		aList.add("some");
		aList.add("are");
		
		
	}
}
