package javabasics;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.smoothstack.javabasics.weekend.Assignment5;

public class Assignment5Test {
	@Test
	public void clumpTest() {
		Assignment5 base = new Assignment5();
		
		List<Integer> l1 = List.of(2,4,8);
		List<Integer> l2 = List.of(1,2,4,8,1);
		List<Integer> l3 = List.of(2,4,4,8);
		List<Integer> l4 = List.of();
		List<Integer> l5 = List.of(-1,-1,4,7,5);
		List<Integer> l6 = List.of(0);
		
		assertTrue(base.groupSumClump(l1, 10));
		assertTrue(base.groupSumClump(l2, 14));
		assertFalse(base.groupSumClump(l3,14));
		assertFalse(base.groupSumClump(l4, 0));
		assertTrue(base.groupSumClump(l5, 2));
		assertTrue(base.groupSumClump(l6, 0));
	}
}
