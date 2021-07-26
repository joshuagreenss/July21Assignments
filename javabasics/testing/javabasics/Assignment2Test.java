package javabasics;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.smoothstack.javabasics.weekend.Assignment2;

public class Assignment2Test {
	@Test
	public void rightTest() {
		Assignment2 test = new Assignment2();
		List<Integer> l1 = List.of(1,2,3,4,5);
		List<Integer> l2 = List.of(12,54,39,10);
		List<Integer> l3 = List.of(6031,48291,98592051);
		List<Integer> l4 = List.of(2);
		List<Integer> l5 = List.of();
		assertArrayEquals(List.of(1,2,3,4,5).toArray(),test.getRight(l1).toArray());
		assertArrayEquals(List.of(2,4,9,0).toArray(),test.getRight(l2).toArray());
		assertArrayEquals(List.of(1,1,1).toArray(),test.getRight(l3).toArray());
		assertArrayEquals(List.of(2).toArray(),test.getRight(l4).toArray());
		assertArrayEquals(List.of().toArray(),test.getRight(l5).toArray());
		try {
			assertArrayEquals(null,test.getRight(null).toArray());
			fail("Did not except at null");
		} catch(Exception e) {
			
		}
	}
}
