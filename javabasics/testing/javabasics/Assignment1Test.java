package javabasics;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.smoothstack.javabasics.weekend.Assignment1;
import com.smoothstack.javabasics.weekend.PerformOperation;

public class Assignment1Test {
	@Test
	public void oddTest() {
		PerformOperation test = Assignment1.isOdd();
		assertTrue(test.operation(1));
		assertTrue(test.operation(-1));
		assertFalse(test.operation(0));
		assertFalse(test.operation(20));
		assertFalse(test.operation(-10));
		try {
			test.operation(null);
			fail("Odd did not except at null");
		}catch(Exception e) {
			
		}
	}
	
	@Test public void primeTest() {
		PerformOperation test = Assignment1.isPrime();
		assertTrue(test.operation(2));
		assertFalse(test.operation(1));
		assertFalse(test.operation(0));
		assertTrue(test.operation(7));
		assertFalse(test.operation(100));
		assertTrue(test.operation(-2));
		assertFalse(test.operation(-1));
		try {
			test.operation(null);
			fail("Prime did not except at null");
		}catch(Exception e){
			
		}
	}
	@Test public void palindromeTest() {
		PerformOperation test = Assignment1.isPalindrome();
		assertTrue(test.operation(111));
		assertFalse(test.operation(12));
		assertTrue(test.operation(1234321));
		assertTrue(test.operation(1221));
		assertFalse(test.operation(122));
		assertTrue(test.operation(-111));
		assertFalse(test.operation(-123));
		assertTrue(test.operation(1));
		try {
			test.operation(null);
			fail("Palindrome did not except at null");
		}catch(Exception e) {
			
		}
	}
}
