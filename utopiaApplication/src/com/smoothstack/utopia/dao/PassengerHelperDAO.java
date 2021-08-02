package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.Booking;
import com.smoothstack.utopia.domain.BookingGuest;
import com.smoothstack.utopia.domain.BookingUser;
import com.smoothstack.utopia.domain.Flight;
import com.smoothstack.utopia.domain.Passenger;
import com.smoothstack.utopia.domain.User;

public class PassengerHelperDAO extends UserDAO {

	public PassengerHelperDAO(Connection conn) {
		super(conn);
	}

	public List<List<Object>> getFlightRoutes() throws SQLException {
		List<List<Object>> response = new ArrayList<>();
		String sql = "SELECT origin_id, origin_city, destination_id, destination_city, max_capacity FROM "
				+ "(SELECT max_capacity, Flight.route_id AS id FROM "
				+ "(SELECT max_capacity, airplane.id AS id FROM airplane_type NATURAL JOIN airplane) AS p "
				+ "INNER JOIN Flight ON Flight.airplane_id = p.id) AS p2 "
				+ "INNER JOIN (SELECT origin_city, origin_id, destination_id, city AS destination_city, id "
				+ "FROM (SELECT city AS origin_city, origin_id, destination_id, route.id AS id "
				+ "FROM airport INNER JOIN route ON airport.iata_id=route.origin_id) AS origin "
				+ "INNER JOIN airport ON origin.destination_id=airport.iata_id) AS f2 ON p2.id = f2.id";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeQuery();
		ResultSet rs = pstmt.getResultSet();
		while (rs.next()) {
			List<Object> s = new ArrayList<>();
			s.add(rs.getString(1));
			s.add(rs.getString(2));
			s.add(rs.getString(3));
			s.add(rs.getString(4));
			s.add(rs.getInt(5));
			response.add(s);
		}
		return response;
	}

	public List<Flight> getSelection(String origin, String destination) throws SQLException, ClassNotFoundException {
		String sql = "SELECT Flight.id AS id, route_id, airplane_id, departure_time, reserved_seats, seat_price FROM flight INNER JOIN route ON route_id=route.id WHERE origin_id = ? AND destination_id = ?";
		FlightDAO flight = new FlightDAO(conn);
		return flight.query(sql, new String[] { origin, destination });
	}

	public void insertUserBooking(BookingUser bu, Booking b, Passenger p, Flight f)
			throws SQLException, ClassNotFoundException {
		String buSql = "INSERT INTO booking_user VALUES (?,?)";
		String bSql = "INSERT INTO booking (is_active,confirmation_code) VALUES (?,?)";
		String pSql = "INSERT INTO passenger (booking_id,given_name,family_name,dob,gender,address) VALUES (?,?,?,?,?,?)";
		String fbSql = "INSERT INTO flight_bookings VALUES (?,?)";
		int id;

		BookingDAO bdao = new BookingDAO(conn);
		FlightDAO fdao = new FlightDAO(conn);

		id = bdao.commitGet(bSql, new Object[] { b.getActive(), b.getCode() });
		bu.setBid(id);
		p.setBid(id);
		insertStatement(buSql, new Object[] { bu.getBid(), bu.getUid() });
		insertStatement(pSql,
				new Object[] { p.getBid(), p.getgName(), p.getfName(), p.getDob(), p.getGender(), p.getAddress() });
		insertStatement(fbSql, new Object[] { f.getId(), id });
		fdao.update(f);
	}

	public void insertGuestBooking(BookingGuest bg, Booking b, Passenger p, Flight f)
			throws SQLException, ClassNotFoundException {
		String bgSql = "INSERT INTO booking_guest VALUES (?,?,?)";
		String bSql = "INSERT INTO booking (is_active,confirmation_code) VALUES (?,?)";
		String pSql = "INSERT INTO passenger (booking_id,given_name,family_name,dob,gender,address) VALUES (?,?,?,?,?,?)";
		String fbSql = "INSERT INTO flight_bookings VALUES (?,?)";
		int id;

		BookingDAO bdao = new BookingDAO(conn);
		FlightDAO fdao = new FlightDAO(conn);

		id = bdao.commitGet(bSql, new Object[] { b.getActive(), b.getCode() });
		p.setBid(id);
		bg.setId(id);

		insertStatement(bgSql, new Object[] { bg.getId(), bg.getEmail(), bg.getPhone() });
		insertStatement(pSql,
				new Object[] { p.getBid(), p.getgName(), p.getfName(), p.getDob(), p.getGender(), p.getAddress() });
		insertStatement(fbSql, new Object[] { f.getId(), id });
		fdao.update(f);
	}

	public List<List<Object>> getBookedFlights(User user) throws SQLException {
		List<List<Object>> flights = new ArrayList<>();
		Flight f;
		String sql = "SELECT f.*, origin_id, destination_id FROM " + "(SELECT flight.*, bid FROM "
				+ "(SELECT flight_id, bid FROM " + "(SELECT id, bu.booking_id AS bid FROM "
				+ "(SELECT * FROM booking_user WHERE user_id=?) "
				+ "AS bu INNER JOIN booking ON bu.booking_id = booking.id) "
				+ "AS b INNER JOIN flight_bookings ON booking_id=id) "
				+ "as fb INNER JOIN flight ON flight.id = flight_id) " + "AS f INNER JOIN Route ON Route.id=route_id";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, user.getId());
		pstmt.executeQuery();
		ResultSet rs = pstmt.getResultSet();
		while (rs.next()) {
			List<Object> val = new ArrayList<>();
			f = new Flight();
			f.setId(rs.getInt(1));
			f.setRoute(rs.getInt(2));
			f.setPlane(rs.getInt(3));
			f.setDeparture(rs.getTimestamp(4).toLocalDateTime());
			f.setReserved(rs.getInt(5));
			f.setPrice(rs.getFloat(6));
			val.add(f);
			val.add(rs.getInt(7));
			val.add(rs.getString(8));
			val.add(rs.getString(9));
			flights.add(val);
		}
		return flights;
	}
	
	public List<List<Object>> getGuestFlights(BookingGuest bg) throws SQLException {
		List<List<Object>> flights = new ArrayList<>();
		Flight f;
		String sql = "SELECT f.*, origin_id, destination_id FROM " + "(SELECT flight.*, bid FROM "
				+ "(SELECT flight_id, bid FROM " + "(SELECT id, bg.booking_id AS bid FROM "
				+ "(SELECT * FROM booking_guest WHERE contact_email = ? AND contact_phone = ?) "
				+ "AS bg INNER JOIN booking ON bg.booking_id = booking.id) "
				+ "AS b INNER JOIN flight_bookings ON booking_id=id) "
				+ "as fb INNER JOIN flight ON flight.id = flight_id) " + "AS f INNER JOIN Route ON Route.id=route_id";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, bg.getEmail());
		pstmt.setObject(2,bg.getPhone());
		pstmt.executeQuery();
		ResultSet rs = pstmt.getResultSet();
		while (rs.next()) {
			List<Object> val = new ArrayList<>();
			f = new Flight();
			f.setId(rs.getInt(1));
			f.setRoute(rs.getInt(2));
			f.setPlane(rs.getInt(3));
			f.setDeparture(rs.getTimestamp(4).toLocalDateTime());
			f.setReserved(rs.getInt(5));
			f.setPrice(rs.getFloat(6));
			val.add(f);
			val.add(rs.getInt(7));
			val.add(rs.getString(8));
			val.add(rs.getString(9));
			flights.add(val);
		}
		return flights;
	}
	private void insertStatement(String sql, Object[] vals) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 1;
		for (Object o : vals) {
			pstmt.setObject(i, o);
			i++;
		}
		pstmt.executeUpdate();
	}

	public List<User> tryLogin(String un, String pw) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
		String[] val = new String[] { un, pw };
		return query(sql, val);
	}

	public void cancel(Flight flight, int bid) throws ClassNotFoundException, SQLException {
		FlightDAO fdao = new FlightDAO(conn);
		fdao.update(flight);
		String sql = "UPDATE Booking SET is_active = 0 WHERE id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, bid);
		pstmt.executeUpdate();
	}
}
