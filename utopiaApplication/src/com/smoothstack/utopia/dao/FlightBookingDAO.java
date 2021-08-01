package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.FlightBooking;

public class FlightBookingDAO extends DAO<FlightBooking> {

	public FlightBookingDAO(Connection conn) {
		super(conn);
	}

	public void insert(FlightBooking booking) throws ClassNotFoundException, SQLException {
		this.commit("INSERT INTO flight_booking VALUES (?, ?)",
				new Object[] { booking.getFlight(), booking.getBooking() });
	}

	public void delete(FlightBooking booking) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM flight_booking WHERE flight_id = ? AND booking_id = ?",
				new Object[] { booking.getFlight(), booking.getBooking() });
	}

	@Override
	protected List<FlightBooking> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if (rs != null) {
			List<FlightBooking> results = new ArrayList<>();
			FlightBooking item = new FlightBooking();
			while (rs.next()) {
				item.setFlight(rs.getInt(1));
				item.setBooking(rs.getInt(2));
				results.add(item);
			}
			return results;
		}
		return null;
	}

}
