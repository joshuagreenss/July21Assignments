package com.smoothstack.utopia.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

import com.smoothstack.utopia.domain.Airplane;
import com.smoothstack.utopia.domain.Airport;
import com.smoothstack.utopia.domain.Booking;
import com.smoothstack.utopia.domain.BookingAgent;
import com.smoothstack.utopia.domain.BookingGuest;
import com.smoothstack.utopia.domain.BookingUser;
import com.smoothstack.utopia.domain.Flight;
import com.smoothstack.utopia.domain.FlightBooking;
import com.smoothstack.utopia.domain.Passenger;
import com.smoothstack.utopia.domain.Payment;
import com.smoothstack.utopia.domain.PlaneType;
import com.smoothstack.utopia.domain.Route;
import com.smoothstack.utopia.domain.User;
import com.smoothstack.utopia.domain.UserRole;

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
	}

	@Test
	public void airplaneTest() {
		Airplane test = new Airplane();
		assertNull(test.getId());
		assertNull(test.getType());
		test.setId(1234);
		test.setType(4321);
		assertEquals(Integer.valueOf(1234), test.getId());
		assertEquals(Integer.valueOf(4321), test.getType());
	}

	@Test
	public void flightTest() {
		Flight test = new Flight();
		assertNull(test.getId());
		assertNull(test.getRoute());
		assertNull(test.getPlane());
		assertNull(test.getDeparture());
		assertNull(test.getReserved());
		assertNull(test.getPrice());
		test.setId(1234);
		test.setRoute(2345);
		test.setPlane(3456);
		LocalDateTime now = LocalDateTime.now();
		test.setDeparture(now);
		test.setReserved(5);
		test.setPrice(100f);
		assertEquals(Integer.valueOf(1234), test.getId());
		assertEquals(Integer.valueOf(2345), test.getRoute());
		assertEquals(Integer.valueOf(3456), test.getPlane());
		assertEquals(now, test.getDeparture());
		assertEquals(Integer.valueOf(5), test.getReserved());
		assertEquals(100, test.getPrice(), 0.0001);
	}

	@Test
	public void planeTypeTest() {
		PlaneType test = new PlaneType();
		assertNull(test.getId());
		assertNull(test.getCapacity());
		test.setId(1234);
		test.setCapacity(150);
		assertEquals(Integer.valueOf(1234), test.getId());
		assertEquals(Integer.valueOf(150), test.getCapacity());
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
		assertEquals(Integer.valueOf(1234), test.getId());
		assertEquals("AAA", test.getOrig());
		assertEquals("BBB", test.getDest());
	}

	@Test
	public void flightBookingTest() {
		FlightBooking test = new FlightBooking();
		assertNull(test.getFlight());
		assertNull(test.getBooking());
		test.setFlight(1234);
		test.setBooking(4321);
		assertEquals(Integer.valueOf(1234), test.getFlight());
		assertEquals(Integer.valueOf(4321), test.getBooking());
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
		assertEquals(Integer.valueOf(1234), test.getId());
		assertEquals(Integer.valueOf(1), test.getActive());
		assertEquals("abcde", test.getCode());
	}

	@Test
	public void paymentTest() {
		Payment test = new Payment();
		assertNull(test.getId());
		assertNull(test.getSid());
		assertNull(test.getRefund());
		test.setId(1234);
		test.setSid("abcde");
		test.setRefund(1);
		assertEquals(Integer.valueOf(1234), test.getId());
		assertEquals("abcde", test.getSid());
		assertEquals(Integer.valueOf(1), test.getRefund());
	}

	@Test
	public void passengerTest() {
		Passenger test = new Passenger();
		assertNull(test.getId());
		assertNull(test.getBid());
		assertNull(test.getgName());
		assertNull(test.getfName());
		assertNull(test.getDob());
		assertNull(test.getGender());
		assertNull(test.getAddress());
		test.setId(1234);
		test.setBid(4321);
		test.setgName("Hi");
		test.setfName("There");
		LocalDate now = LocalDate.now();
		test.setDob(now);
		test.setGender("Gender");
		test.setAddress("1234 Extant Way");
		assertEquals(Integer.valueOf(1234), test.getId());
		assertEquals(Integer.valueOf(4321), test.getBid());
		assertEquals("Hi", test.getgName());
		assertEquals("There", test.getfName());
		assertEquals(now, test.getDob());
		assertEquals("Gender", test.getGender());
		assertEquals("1234 Extant Way", test.getAddress());
	}

	@Test
	public void bAgentTest() {
		BookingAgent test = new BookingAgent();
		assertNull(test.getBid());
		assertNull(test.getAid());
		test.setBid(1234);
		test.setAid(4321);
		assertEquals(Integer.valueOf(1234), test.getBid());
		assertEquals(Integer.valueOf(4321), test.getAid());
	}

	@Test
	public void bUserTest() {
		BookingUser test = new BookingUser();
		assertNull(test.getBid());
		assertNull(test.getUid());
		test.setBid(1234);
		test.setUid(4321);
		assertEquals(Integer.valueOf(1234), test.getBid());
		assertEquals(Integer.valueOf(4321), test.getUid());
	}

	@Test
	public void bGuestTest() {
		BookingGuest test = new BookingGuest();
		assertNull(test.getId());
		assertNull(test.getEmail());
		assertNull(test.getPhone());
		test.setId(1234);
		test.setEmail("e@mail.com");
		test.setPhone("555-555-5555");
		assertEquals(Integer.valueOf(1234), test.getId());
		assertEquals("e@mail.com", test.getEmail());
		assertEquals("555-555-5555", test.getPhone());
	}

	@Test
	public void userTest() {
		User test = new User();
		assertNull(test.getId());
		assertNull(test.getRole());
		assertNull(test.getgName());
		assertNull(test.getfName());
		assertNull(test.getUsername());
		assertNull(test.getEmail());
		assertNull(test.getPassword());
		assertNull(test.getPhone());
		test.setId(1234);
		test.setRole(4321);
		test.setgName("Hi");
		test.setfName("There");
		test.setUsername("placeholder");
		test.setEmail("e@mail.com");
		test.setPassword("password");
		test.setPhone("555-555-5555");
		assertEquals(Integer.valueOf(1234), test.getId());
		assertEquals(Integer.valueOf(4321), test.getRole());
		assertEquals("Hi", test.getgName());
		assertEquals("There", test.getfName());
		assertEquals("placeholder", test.getUsername());
		assertEquals("e@mail.com", test.getEmail());
		assertEquals("password", test.getPassword());
		assertEquals("555-555-5555", test.getPhone());
	}

	@Test
	public void roleTest() {
		UserRole test = new UserRole();
		assertNull(test.getId());
		assertNull(test.getName());
		test.setId(1234);
		test.setName("Exists");
		assertEquals(Integer.valueOf(1234), test.getId());
		assertEquals("Exists", test.getName());
	}
}
