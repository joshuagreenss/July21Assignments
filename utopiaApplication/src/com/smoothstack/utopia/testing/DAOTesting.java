/**
 * 
 */
package com.smoothstack.utopia.testing;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.smoothstack.utopia.dao.AirportDAO;
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
import com.smoothstack.utopia.utils.ConnectionUtil;

/**
 * @author joshu
 *
 */

public class DAOTesting {
	@Test
	public void airportTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			AirportDAO testDAO = new AirportDAO(conn);
			Airport testAirport = new Airport();
			String sql = "SELECT * FROM Airport WHERE iata_id=?";
			String[] id = new String[] { "ABC" };

			testAirport.setCode("ABC");
			testAirport.setCity("Test City");
			testDAO.insert(testAirport);
			assertArrayEquals(new Airport[] { testAirport }, testDAO.query("sql", id).toArray());

			testAirport.setCity("Test 2");
			testDAO.update(testAirport);
			assertArrayEquals(new Airport[] { testAirport }, testDAO.query(sql, id).toArray());

			testDAO.delete(testAirport);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void planeTypeTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PlaneTypeDAO testDAO = new PlaneTypeDAO(conn);
			PlaneType testType = new PlaneType();
			String sql = "SELECT * FROM airplane_type WHERE id=?";
			String[] id = new String[] { "1111" };

			testType.setId(1111);
			testType.setCapacity(100);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testType);
			assertArrayEquals(List.of(testType).toArray(), testDAO.query(sql, id).toArray());

			testType.setCapacity(200);
			testDAO.update(testType);
			assertArrayEquals(List.of(testType).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testType);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void planeTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			AirplaneDAO testDAO = new AirplaneDAO(conn);
			Airplane testPlane = new Airplane();
			String sql = "SELECT * FROM Airplane WHERE id=?";
			String[] id = new String[] { "1111" };

			testPlane.setId(1111);
			testPlane.setType(1);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testPlane);
			assertArrayEquals(List.of(testPlane).toArray(), testDAO.query(sql, id).toArray());

			testPlane.setType(2);
			testDAO.update(testPlane);
			assertArrayEquals(List.of(testPlane).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testPlane);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void routeTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			RouteDAO testDAO = new RouteDAO(conn);
			Route testRoute = new Route();
			String sql = "SELECT * FROM Route WHERE id=?";
			String[] id = new String[] { "1111" };

			testRoute.setId(1111);
			testRoute.setOrig("SEA");
			testRoute.setDest("ANC");
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testRoute);
			assertArrayEquals(List.of(testRoute).toArray(), testDAO.query(sql, id).toArray());

			testRoute.setDest("LAX");
			testDAO.update(testRoute);
			assertArrayEquals(List.of(testRoute).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testRoute);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void flightTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			FlightDAO testDAO = new FlightDAO(conn);
			Flight testFlight = new Flight();
			String sql = "SELECT * FROM Flight WHERE id=?";
			String[] id = new String[] { "1111" };

			testFlight.setId(1111);
			testFlight.setRoute(12);
			testFlight.setPlane(31);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testFlight);
			assertArrayEquals(List.of(testFlight).toArray(), testDAO.query(sql, id).toArray());

			testFlight.setPrice(110f);
			LocalDateTime now = LocalDateTime.now();
			testFlight.setDeparture(now);
			testFlight.setReserved(0);
			testDAO.update(testFlight);
			assertArrayEquals(List.of(testFlight).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testFlight);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void flightBookingTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			FlightBookingDAO testDAO = new FlightBookingDAO(conn);
			FlightBooking testBooking = new FlightBooking();
			String sql = "SELECT * FROM flight_bookings WHERE flight_id=?";
			String[] id = new String[] { "1111" };

			testBooking.setFlight(1111);
			testBooking.setBooking(2222);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testBooking);
			assertArrayEquals(List.of(testBooking).toArray(), testDAO.query(sql, id).toArray());

			testBooking.setBooking(3333);
			testDAO.update(testBooking);
			assertArrayEquals(List.of(testBooking).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testBooking);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void bookingTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			BookingDAO testDAO = new BookingDAO(conn);
			Booking testBooking = new Booking();
			String sql = "SELECT * FROM Booking WHERE id=?";
			String[] id = new String[] { "1111" };

			testBooking.setId(1111);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testBooking);
			assertArrayEquals(List.of(testBooking).toArray(), testDAO.query(sql, id).toArray());

			testBooking.setActive(0);
			testBooking.setCode("ABCDE");
			testDAO.update(testBooking);
			assertArrayEquals(List.of(testBooking).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testBooking);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void paymentTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PaymentDAO testDAO = new PaymentDAO(conn);
			Payment testPayment = new Payment();
			String sql = "SELECT * FROM booking_payment WHERE booking_id=?";
			String[] id = new String[] { "1111" };
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testPayment);
			assertArrayEquals(List.of(testPayment).toArray(), testDAO.query(sql, id).toArray());

			testPayment.setId(1111);
			testPayment.setSid("IDNUMBER");
			testPayment.setRefund(0);
			testDAO.update(testPayment);
			assertArrayEquals(List.of(testPayment).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testPayment);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void passengerTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PassengerDAO testDAO = new PassengerDAO(conn);
			Passenger testPassenger = new Passenger();
			String sql = "SELECT * FROM Passenger WHERE id=?";
			String[] id = new String[] { "1111" };

			testPassenger.setId(1111);
			testPassenger.setBid(2222);
			assertArrayEquals(List.of().toArray(),
					testDAO.query("SELECT * FROM Passenger WHERE id=?", new Integer[] { 1111 }).toArray());
			testDAO.insert(testPassenger);
			assertArrayEquals(List.of(testPassenger).toArray(), testDAO.query(sql, id).toArray());

			testPassenger.setgName("First");
			testPassenger.setfName("Family");
			LocalDate now = LocalDate.now();
			testPassenger.setDob(now);
			testPassenger.setGender("G");
			testPassenger.setAddress("1234 Extant Way");
			testDAO.update(testPassenger);
			assertArrayEquals(List.of(testPassenger).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testPassenger);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void bookingAgentTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			BookingAgentDAO testDAO = new BookingAgentDAO(conn);
			BookingAgent testAgent = new BookingAgent();
			String sql = "SELECT * FROM booking_agent WHERE booking_id=?";
			String[] id = new String[] { "1111" };

			testAgent.setBid(1111);
			testAgent.setAid(2222);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray().toArray());

			testDAO.insert(testAgent);
			assertArrayEquals(List.of(testAgent).toArray(), testDAO.query(sql, id).toArray());

			testAgent.setAid(3333);
			testDAO.update(testAgent);
			assertArrayEquals(List.of(testAgent).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testAgent);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void bookingUserTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			BookingUserDAO testDAO = new BookingUserDAO(conn);
			BookingUser testUser = new BookingUser();
			String sql = "SELECT * FROM booking_user WHERE booking_id=?";
			String[] id = new String[] { "1111" };

			testUser.setBid(1111);
			testUser.setUid(2222);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testUser);
			assertArrayEquals(List.of(testUser).toArray(), testDAO.query(sql, id).toArray());

			testUser.setUid(3333);
			testDAO.update(testUser);
			assertArrayEquals(List.of(testUser).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testUser);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void bookingGuestTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			BookingGuestDAO testDAO = new BookingGuestDAO(conn);
			BookingGuest testGuest = new BookingGuest();
			String sql = "SELECT * FROM booking_guest WHERE booking_id=?";
			String[] id = new String[] { "1111" };

			testGuest.setId(1111);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray().toArray());

			testDAO.insert(testGuest);
			assertArrayEquals(List.of(testGuest).toArray(), testDAO.query(sql, id).toArray());

			testGuest.setEmail("e@mail.com");
			testGuest.setPhone("555-555-5555");
			testDAO.update(testGuest);
			assertArrayEquals(List.of(testGuest).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testGuest);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void userTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			UserDAO testDAO = new UserDAO(conn);
			User testUser = new User();
			String sql = "SELECT * FROM User WHERE id=?";
			String[] id = new String[] { "1111" };

			testUser.setId(1111);
			testUser.setRole(2222);
			assertArrayEquals(List.of().toArray(), testDAO.query(testDAO.query(sql, id).toArray()).toArray());

			testDAO.insert(testUser);
			assertArrayEquals(List.of(testUser).toArray(), testDAO.query(sql, id).toArray());

			testUser.setgName("First");
			testUser.setfName("Family");
			testUser.setUsername("username");
			testUser.setEmail("e@mail.com");
			testUser.setPassword("password");
			testUser.setPhone("555-555-5555");
			testDAO.update(testUser);
			assertArrayEquals(List.of(testUser).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testUser);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void userRoleTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			UserRoleDAO testDAO = new UserRoleDAO(conn);
			UserRole testRole = new UserRole();
			String sql = "SELECT * FROM user_role WHERE id=?";
			String[] id = new String[] { "1111" };

			testRole.setId(1111);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray().toArray());

			testDAO.insert(testRole);
			assertArrayEquals(List.of(testRole).toArray(), testDAO.query(sql, id).toArray());

			testRole.setName("ADMIN");
			testDAO.update(testRole);
			assertArrayEquals(List.of(testRole).toArray(), testDAO.query(sql, id).toArray());

			testDAO.delete(testRole);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
}
