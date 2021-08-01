package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.Flight;

public class FlightDAO extends DAO<Flight> {

	public FlightDAO(Connection conn) {
		super(conn);
	}

	public void insert(Flight flight) throws ClassNotFoundException, SQLException {
		if (flight.getId() != null) {
			this.commit("INSERT INTO Flight VALUES (?, ?, ?, ?, ?, ?)",
					new Object[] { flight.getId(), flight.getRoute(), flight.getPlane(), flight.getDeparture(),
							flight.getReserved(), flight.getPrice() });
		} else {
			commit("INSERT INTO Flight (route_id, airplane_id, departure_time, reserved_seats, seat_price) VALUES (?,?,?,?,?)",
					new Object[] { flight.getRoute(), flight.getPlane(), flight.getDeparture(), flight.getReserved(),
							flight.getPrice() });
		}
	}

	public void update(Flight flight) throws ClassNotFoundException, SQLException {
		this.commit(
				"UPDATE Flight SET route_id = ?, airplane_id = ?, departure_time = ?, reserved_seats = ?, seat_price = ? WHERE id = ?",
				new Object[] { flight.getRoute(), flight.getPlane(), flight.getDeparture(), flight.getReserved(),
						flight.getPrice(), flight.getId() });
	}

	public void delete(Flight flight) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM Flight WHERE id = ?", new Object[] { flight.getId() });
	}

	@Override
	protected List<Flight> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if (rs != null) {
			List<Flight> results = new ArrayList<>();
			Flight item = new Flight();
			while (rs.next()) {
				item.setId(rs.getInt(1));
				item.setRoute(rs.getInt(2));
				item.setPlane(rs.getInt(3));
				item.setDeparture(rs.getTimestamp(4).toLocalDateTime());
				item.setReserved(rs.getInt(5));
				item.setPrice(rs.getFloat(6));
				results.add(item);
			}
			return results;
		}
		return null;
	}

}
