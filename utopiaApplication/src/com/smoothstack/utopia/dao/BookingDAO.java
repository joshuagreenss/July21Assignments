package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.Booking;

public class BookingDAO extends DAO<Booking> {

	public BookingDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void insert(Booking booking) throws ClassNotFoundException, SQLException {
		if (booking.getId() != null) {
			this.commit("INSERT INTO Booking VALUES (?, ?, ?)",
					new Object[] { booking.getId(), booking.getActive(), booking.getCode() });
		} else {
			this.commit("INSERT INTO Booking(is_active,confirmation_code) VALUES (?,?)",
					new Object[] { booking.getActive(), booking.getCode() });
		}
	}

	public void update(Booking booking) throws ClassNotFoundException, SQLException {
		this.commit("UPDATE Booking SET is_active = ?,confirmation_code = ? WHERE id = ?",
				new Object[] { booking.getActive(), booking.getCode(), booking.getId() });
	}

	public void delete(Booking booking) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM Booking WHERE id = ?", new Object[] { booking.getId() });
	}

	@Override
	protected List<Booking> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if(rs != null) {
			List<Booking> results = new ArrayList<>();
			Booking item = new Booking();
			while(rs.next()) {
				item.setId(rs.getInt(1));
				item.setActive(rs.getInt(2));
				item.setCode(rs.getString(3));
				results.add(item);
			}
			return results;
		}
		return null;
	}

}
