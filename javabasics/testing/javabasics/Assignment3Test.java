package javabasics;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.smoothstack.javabasics.weekend.Assignment3;

public class Assignment3Test {
	@Test
	public void doubleTest() {
		Assignment3 test = new Assignment3();
		List<Integer> l1 = List.of(1, 2, 3);
		List<Integer> l2 = List.of(-1, -2, -3);
		List<Integer> l3 = List.of();
		List<Integer> l4 = List.of(50, 50, 50, 50, 50);
		List<Integer> l5 = List.of(1, -1, 1, -1);

		assertArrayEquals(List.of(2,4,6).toArray(),test.doubling(l1).toArray());
		assertArrayEquals(List.of(-2,-4,-6).toArray(),test.doubling(l2).toArray());
		assertArrayEquals(l3.toArray(),test.doubling(l3).toArray());
		assertArrayEquals(List.of(100,100,100,100,100).toArray(),test.doubling(l4).toArray());
		assertArrayEquals(List.of(2,-2,2,-2).toArray(),test.doubling(l5).toArray());
		try {
			test.doubling(null);
			fail("Did not except at null");
		} catch(Exception e) {
			
		}
	}
	
	public static void main(String[] args) {
	}
}
