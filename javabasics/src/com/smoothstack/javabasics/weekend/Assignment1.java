package com.smoothstack.javabasics.weekend;

public class Assignment1 {
	
	//Returns modulus of number, uses != 0 for ease of processing negative numbers
	public static PerformOperation isOdd() {
		PerformOperation op = (i) -> i%2 != 0;
		return op;
	}
	
	//Iterates through integers until reaches a factor (not prime) or square exceeds number (prime)
	//Special cases for 1 and 0, uses absolute value for negative primes, may not be appropriate
	public static PerformOperation isPrime() {
		PerformOperation op = (i) -> {
			int a = Math.abs(i);
			if(a == 1 || a == 0) {
				return false;
			}
			for(int j = 2; j*j < a; j++) {
				if(a%j == 0) {
					return false;
				}
			}
			return true;
		};
		return op;
	}
	
	//Converts absolute value of integer to string, compares indices from first half to reverse in second half
	//until reaches halfway
	public static PerformOperation isPalindrome() {
		PerformOperation op = (i) -> {
			String s = String.valueOf(Math.abs(i));
			for(int j = 0; j < s.length()/2; j++) {
				if(s.charAt(j) != s.charAt(s.length()-j-1)) {
					return false;
				}
			}
			return true;
		};
		return op;
	}
}
