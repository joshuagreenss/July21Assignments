package javabasics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.smoothstack.javabasics.day4.Line;

public class LineTest {
	
	@Test
	public void slopeTest() {
		//Test cases are a line with slope 1, one with larger slope, one with negative slope, one with zero slope, one vertical line, and one decimal slope
		Line l1 = new Line(0,0,1,1);
		Line l2 = new Line(0,0,1,10);
		Line l3 = new Line(1,0,0,1);
		Line l4 = new Line(1,1,10,1);
		Line l5 = new Line(0,1,0,2);
		Line l6 = new Line(0,0,10,1);
		assertEquals(1, l1.getSlope(), 0.0001);
		assertEquals(10, l2.getSlope(), 0.0001);
		assertEquals(-1, l3.getSlope(), 0.0001);
		assertEquals(0, l4.getSlope(), 0.0001);
		try {
			l5.getSlope();
			fail("Did not except at zero slope");
		}catch(ArithmeticException e) {
		}
		assertEquals(0.1,l6.getSlope(),0.0001);
	}
	
	@Test
	public void distanceTest() {
		//Test cases are a zero distance line, one positive slope non-integer distance, one zero slope with distance, one negative slope non-integer distance, and one vertical line
		Line l1 = new Line(0,0,0,0);
		Line l2 = new Line(0,0,1,1);
		Line l3 = new Line(1,1,11,1);
		Line l4 = new Line(1,1,0,0);
		Line l5 = new Line(0,0,0,10);
		assertEquals(0,l1.getDistance(),0.0001);
		assertEquals(Math.sqrt(2),l2.getDistance(),0.0001);
		assertEquals(10,l3.getDistance(),0.0001);
		assertEquals(Math.sqrt(2),l4.getDistance(),0.0001);
		assertEquals(10,l5.getDistance(),0.0001);
	}
	
	@Test
	public void parallelTest() {
		//Test cases are checking two lines with slope = 1, one line with slope = 1 and one with slope = -1, two vertical lines, two horizontal lines,
		//two lines with slightly different very small slopes (should be true because difference is smaller than given delta), two lines with slightly different very large slopes,
		//two lines with small but distinguishable slopes, and vertical lines in either position
		
		Line l1 = new Line(0,0,1,1);
		Line l2 = new Line(0,1,1,2);
		Line l3 = new Line(0,1,1,0);
		Line l4 = new Line(0,0,0,10);
		Line l5 = new Line(1,0,1,10);
		Line l6 = new Line(0,0,10,0);
		Line l7 = new Line(0,1,10,1);
		Line l8 = new Line(0,0,10000,1);
		Line l9 = new Line(0,0,10001,1);
		Line l10 = new Line(0,0,1,10000);
		Line l11 = new Line(0,0,1,10001);
		Line l13 = new Line(0,0,5000,1);
		assertTrue(l1.parallelTo(l2));
		assertFalse(l1.parallelTo(l3));
		//Although vertical lines are parallel, implementation expects exception here
		try{
			l4.parallelTo(l5);
			fail("Did not except at two vertical lines");
		}catch(ArithmeticException e) {
		}
		assertTrue(l6.parallelTo(l7));
		//Slopes are 0.0001 and 0.00009999, so difference is smaller than provided delta
		assertTrue(l8.parallelTo(l9));
		assertFalse(l10.parallelTo(l11));
		assertFalse(l8.parallelTo(l13));
		try {
			l5.parallelTo(l1);
			fail("Did not except at vertical line calling");
		}catch(ArithmeticException e) {
		}
		try {
			l4.parallelTo(l1);
			fail("Did not except at vertical line passed in");
		}catch(ArithmeticException e) {
		}
	}
}
