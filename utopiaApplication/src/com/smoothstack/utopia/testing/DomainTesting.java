package com.smoothstack.utopia.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.smoothstack.utopia.domain.Airport;

public class DomainTesting {
	@Test
	public void airportTest() {
		Airport test = new Airport();
		assertNull(test.getCity());
		assertNull(test.getCode());
		test.setCity("Test City");
		test.setCode("ABC");
		assertEquals("Test City", test.getCity());
		assertEquals("ABC",test.getCode());
		test.setCity("Test 2");
		test.setCode("XYZ");
		assertEquals("Test 2", test.getCity());
		assertEquals("XYZ",test.getCode());
	}
}
