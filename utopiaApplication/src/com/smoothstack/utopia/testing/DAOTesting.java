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

import com.smoothstack.utopia.dao.AirplaneDAO;
import com.smoothstack.utopia.dao.AirplaneTypeDAO;
import com.smoothstack.utopia.dao.AirportDAO;
import com.smoothstack.utopia.dao.BookingAgentDAO;
import com.smoothstack.utopia.dao.BookingDAO;
import com.smoothstack.utopia.dao.BookingGuestDAO;
import com.smoothstack.utopia.dao.BookingUserDAO;
import com.smoothstack.utopia.dao.FlightBookingDAO;
import com.smoothstack.utopia.dao.FlightDAO;
import com.smoothstack.utopia.dao.PassengerDAO;
import com.smoothstack.utopia.dao.PaymentDAO;
import com.smoothstack.utopia.dao.RouteDAO;
import com.smoothstack.utopia.dao.UserDAO;
import com.smoothstack.utopia.dao.UserRoleDAO;
import com.smoothstack.utopia.domain.Airplane;
import com.smoothstack.utopia.domain.AirplaneType;
import com.smoothstack.utopia.domain.Airport;
import com.smoothstack.utopia.domain.Booking;
import com.smoothstack.utopia.domain.BookingAgent;
import com.smoothstack.utopia.domain.BookingGuest;
import com.smoothstack.utopia.domain.BookingUser;
import com.smoothstack.utopia.domain.Flight;
import com.smoothstack.utopia.domain.FlightBooking;
import com.smoothstack.utopia.domain.Passenger;
import com.smoothstack.utopia.domain.Payment;
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
			assertArrayEquals(new Airport[] { testAirport }, testDAO.query(sql, id).toArray());

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
	public void airplaneTypeTest() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			AirplaneTypeDAO testDAO = new AirplaneTypeDAO(conn);
			AirplaneType testType = new AirplaneType();
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

			testRoute.setDest("PDX");
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
			testFlight.setRoute(4);
			testFlight.setPlane(8);
			testFlight.setPrice(100f);
			LocalDateTime now = LocalDateTime.now();
			//Need to remove nanoseconds because mySQL does not store nanos
			now = now.minusNanos(now.getNano());
			testFlight.setDeparture(now);
			testFlight.setReserved(0);

			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testFlight);
			assertArrayEquals(List.of(testFlight).toArray(), testDAO.query(sql, id).toArray());

			testFlight.setPrice(110f);
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
			String sql = "SELECT * FROM flight_bookings WHERE flight_id=? AND booking_id=?";
			String[] id = new String[] { "2","10" };

			testBooking.setFlight(2);
			testBooking.setBooking(10);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testBooking);
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
			testBooking.setActive(0);
			testBooking.setCode("ABCDE");
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testBooking);
			assertArrayEquals(List.of(testBooking).toArray(), testDAO.query(sql, id).toArray());

			testBooking.setActive(1);
			testBooking.setCode("FGHIJ");
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
			String[] id = new String[] { "10" };
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());
			
			testPayment.setId(10);
			testPayment.setSid("IDNUMBER");
			testPayment.setRefund(0);
			testDAO.insert(testPayment);
			assertArrayEquals(List.of(testPayment).toArray(), testDAO.query(sql, id).toArray());

			testPayment.setRefund(1);
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
			testPassenger.setBid(6);
			testPassenger.setgName("First");
			testPassenger.setfName("Family");
			LocalDate now = LocalDate.now();
			testPassenger.setDob(now);
			testPassenger.setGender("G");
			testPassenger.setAddress("1234 Extant Way");
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testPassenger);
			assertArrayEquals(List.of(testPassenger).toArray(), testDAO.query(sql, id).toArray());
			
			testPassenger.setgName("New");
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
			String sql = "SELECT * FROM booking_agent WHERE booking_id=? AND agent_id=?";
			String[] id = new String[] { "9", "3" };

			testAgent.setBid(9);
			testAgent.setAid(3);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testAgent);
			assertArrayEquals(List.of(testAgent).toArray(), testDAO.query(sql, id).toArray());

			testAgent.setAid(1);
			id[1] = "1";
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
			String sql = "SELECT * FROM booking_user WHERE booking_id=? AND user_id=?";
			String[] id = new String[] { "1","1" };

			testUser.setBid(1);
			testUser.setUid(1);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testUser);
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
			String[] id = new String[] { "5" };

			testGuest.setId(5);
			testGuest.setEmail("e@mail.com");
			testGuest.setPhone("555-555-5559");
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testGuest);
			assertArrayEquals(List.of(testGuest).toArray(), testDAO.query(sql, id).toArray());
			
			testGuest.setPhone("555-555-5560");
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
			testUser.setRole(2);
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testUser.setgName("First");
			testUser.setfName("Family");
			testUser.setUsername("username");
			testUser.setEmail("e@mail.com");
			testUser.setPassword("password");
			testUser.setPhone("555-555-5559");
			testDAO.insert(testUser);
			assertArrayEquals(List.of(testUser).toArray(), testDAO.query(sql, id).toArray());

			testUser.setUsername("username2");
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
			testRole.setName("TEST");
			assertArrayEquals(List.of().toArray(), testDAO.query(sql, id).toArray());

			testDAO.insert(testRole);
			assertArrayEquals(List.of(testRole).toArray(), testDAO.query(sql, id).toArray());

			testRole.setName("TEST2");
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
