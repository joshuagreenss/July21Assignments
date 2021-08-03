package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.smoothstack.utopia.dao.TravelerHelperDAO;
import com.smoothstack.utopia.domain.Booking;
import com.smoothstack.utopia.domain.BookingGuest;
import com.smoothstack.utopia.domain.BookingUser;
import com.smoothstack.utopia.domain.Flight;
import com.smoothstack.utopia.domain.Passenger;
import com.smoothstack.utopia.domain.User;
import com.smoothstack.utopia.utils.ConnectionUtil;

public class TravelerServices {
	private Connection conn;
	private TravelerHelperDAO thdao;

	public TravelerServices() {
		try {
			conn = ConnectionUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		thdao = new TravelerHelperDAO(conn);
	}

	public void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> tryLogin(String username, String password) {
		try {
			return thdao.tryLogin(username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<List<Object>> readAll() {
		try {
			return thdao.getFlightRoutes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Flight> getSelection(String origin, String destination) {
		try {
			return thdao.getSelection(origin, destination);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertUserBooking(String code, int uid, String gName, String fName, LocalDate dob, String gender,
			String address, int reserved, Flight f) {
		BookingUser bu = new BookingUser();
		Booking b = new Booking();
		Passenger p = new Passenger();
		bu.setUid(uid);
		b.setActive(1);
		b.setCode(code);
		p.setgName(gName);
		p.setfName(fName);
		p.setDob(dob);
		p.setGender(gender);
		p.setAddress(address);
		f.setReserved(reserved);
		try {
			try {
				thdao.insertUserBooking(bu, b, p, f);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertGuestBooking(BookingGuest bg, String code, String gName, String fName, LocalDate dob,
			String gender, String address, int reserved) {
		
	}

	public List<List<Object>> getBookedFlights(User user) {
		try {
			return thdao.getBookedFlights(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void cancel(Flight flight, int bid) {
		try {
			try {
				thdao.cancel(flight, bid);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {

		}
	}

	public List<List<Object>> getGuestFlights(BookingGuest bg) {
		try {
			return thdao.getGuestFlights(bg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
