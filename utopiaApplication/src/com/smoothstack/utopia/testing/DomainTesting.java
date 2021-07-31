package com.smoothstack.utopia.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
		assertEquals("ABC", test.getCode());
		test.setCity("Test 2");
		test.setCode("XYZ");
		assertEquals("Test 2", test.getCity());
		assertEquals("XYZ", test.getCode());
	}

	@Test
	public void airplaneTest() {
		Airplane test = new Airplane();
		assertNull(test.getId());
		assertNull(test.getType());
		test.setId(1234);
		test.setType(4321);
		assertEquals(1234, test.getId());
		assertEquals(4321, test.getType());
		test.setId(9876);
		test.setType(6789);
		assertEquals(9876, test.getId());
		assertEquals(6789, test.getType);
	}

	@Test
	public void flightTest() {
		Flight test = new Flight();
		assertNull(test.getId());
		assertNull(test.getRoute());
		assertNull(test.getPlane());
		assertNull(test.getTime());
		assertNull(test.getReserved());
		assertNull(test.getPrice());
		test.setId(1234);
		test.setRoute(2345);
		test.setPlane(3456);
		LocalDateTime now = LocalDateTime.now();
		test.setTime(now);
		test.setReserved(5);
		test.setPrice(100);
		assertEquals(1234, test.getId());
		assertEquals(2345, test.getRoute());
		assertEquals(3456, test.getPlane());
		assertEquals(now, test.getTime());
		assertEquals(5, test.getReserved());
		assertEquals(100, test.getPrice());
	}

	@Test
	public void planeTypeTest() {
		PlaneType test = new PlaneType();
		assertNull(test.getId());
		assertNull(test.getCapacity());
		test.setId(1234);
		test.setCapacity(150);
		assertEquals(1234, test.getId());
		assertEquals(150, test.getCapacity());
	}
	
	@Test
	public void routeTest() {
		Route test = new Route();
		assertNull(test.getId());
		assertNull(test.getOrig());
		assertNull(test.getDest());
		test.setId(1234);
		test.setOrig("AAA");
		test.setDest("BBB");
		assertEquals(1234,test.getId());
		assertEquals("AAA", test.getOrig());
		assertEquals("BBB",test.getDest());
	}
	
	@Test
	public void flightBookingTest() {
		FlightBooking test = new FlightBooking();
		assertNull(test.getFlight());
		assertNull(test.getBooking());
		test.setFlight(1234);
		test.setBooking(4321);
		assertEquals(1234,test.getFlight());
		assertEquals(4321,test.getBooking());
	}
	
	@Test
	public void bookingTest() {
		Booking test = new Booking();
		assertNull(test.getId());
		assertNull(test.getActive());
		assertNull(test.getCode());
		test.setId(1234);
		test.setActive(1);
		test.setCode("abcde");
		assertEquals(1234,test.getId());
		assertEquals(1,test.getActive());
		assertEquals("abcde",test.getCode());
	}
	
	@Test
	public void paymentTest() {
		Payment test = new Payment();
		assertNull(test.getBid());
		assertNull(test.getSid());
		assertNull(test.getRefund());
		test.setBid(1234);
		test.setSid("abcde");
		test.setRefund(1);
		assertEquals(1234,test.getBid());
		assertEquals("abcde",test.getSid());
		assertEquals(1,test.getRefund());
	}
	
	@Test
	public void passengerTest() {
		Passenger test = new Passenger();
		assertNull(test.getId());
		assertNull(test.getBooking());
		assertNull(test.getGName());
		assertNull(test.getFName());
		assertNull(test.getDOB());
		assertNull(test.getGender());
		assertNull(test.getAddress());
		test.setId(1234);
		test.setBooking(4321);
		test.setGName("Hi");
		test.setFName("There");
		LocalDate now = LocalDate.now();
		test.setDOB(now);
		test.setGender("Gender");
		test.setAddress("1234 Extant Way");
		assertEquals(1234,test.getId());
		assertEquals(4321,test.getBooking());
		assertEquals("Hi",test.getGName());
		assertEquals("There",test.getFName());
		assertEquals(now,test.getDOB());
		assertEquals("Gender",test.getGender());
		assertEquals("1234 Extant Way",test.getAddress());
	}
	
	@Test
	public void bAgentTest() {
		BookingAgent test = new BookingAgent();
		assertNull(test.getBid());
		assertNull(test.getAid());
		test.setBid(1234);
		test.setAid(4321);
		assertEquals(1234,test.getBid());
		assertEquals(4321,test.getAid());
	}
	
	@Test
	public void bUserTest() {
		BookingUser test = new BookingUser();
		assertNull(test.getBid());
		assertNull(test.getAid());
		test.setBid(1234);
		test.setAid(4321);
		assertEquals(1234,test.getBid());
		assertEquals(4321,test.getAid());
	}
	
	@Test
	public void bGuestTest() {
		BookingGuest test = new BookingGuest();
		assertNull(test.getBid());
		assertNull(test.getEmail());
		assertNull(test.getPhone());
		test.setBid(1234);
		test.setEmail("e@mail.com");
		test.setPhone("555-555-5555");
		assertEquals(1234,test.getBid());
		assertEquals("e@mail.com",test.getEmail());
		assertEquals("555-555-5555",test.getPhone());
	}
	
	@Test
	public void userTest() {
		User test = new User();
		assertNull(test.getId());
		assertNull(test.getRole());
		assertNull(test.getGName());
		assertNull(test.getFName());
		assertNull(test.getUsername());
		assertNull(test.getEmail());
		assertNull(test.getPassword());
		assertNull(test.getPhone());
		test.setId(1234);
		test.setRole(4321);
		test.setGName("Hi");
		test.setFName("There");
		test.setUsername("placeholder");
		test.setEmail("e@mail.com");
		test.setPassword("password");
		test.setPhone("555-555-5555");
		assertEquals(1234,test.getId());
		assertEquals(4321,test.getRole());
		assertEquals("Hi",test.getGName());
		assertEquals("There",test.getFName());
		assertEquals("placeholder",test.getUsername());
		assertEquals("e@mail.com",test.getEmail());
		assertEquals("password",test.getPassword());
		assertEquals("555-555-5555",test.getPhone());
	}
}
