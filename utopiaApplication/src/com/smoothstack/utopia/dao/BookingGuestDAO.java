package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.BookingGuest;

public class BookingGuestDAO extends DAO<BookingGuest> {

	public BookingGuestDAO(Connection conn) {
		super(conn);
	}

	public void insert(BookingGuest guest) throws ClassNotFoundException, SQLException {
		this.commit("INSERT INTO booking_guest VALUES (?, ?, ?)",
				new Object[] { guest.getId(), guest.getEmail(), guest.getPhone() });
	}

	public void update(BookingGuest guest) throws ClassNotFoundException, SQLException {
		this.commit("UPDATE booking_guest SET contact_email = ?, contact_phone = ? WHERE booking_id = ?",
				new Object[] { guest.getEmail(), guest.getPhone(), guest.getId() });
	}

	public void delete(BookingGuest agent) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM booking_guest WHERE booking_id = ?", new Object[] { agent.getId() });
	}

	@Override
	protected List<BookingGuest> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		if (rs != null) {
			List<BookingGuest> results = new ArrayList<>();
			BookingGuest item;
			while (rs.next()) {
				item = new BookingGuest();
				item.setId(rs.getInt(1));
				item.setEmail(rs.getString(2));
				item.setPhone(rs.getString(3));
				results.add(item);
			}
			return results;
		}
		return null;
	}

}
