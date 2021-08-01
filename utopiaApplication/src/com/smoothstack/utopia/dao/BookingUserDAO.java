package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.BookingUser;

public class BookingUserDAO extends DAO<BookingUser> {

	public BookingUserDAO(Connection conn) {
		super(conn);
	}

	public void insert(BookingUser user) throws ClassNotFoundException, SQLException {
		this.commit("INSERT INTO booking_user VALUES (?, ?)", new Object[] { user.getBid(), user.getUid() });
	}

	public void update(BookingUser user) throws ClassNotFoundException, SQLException {
		this.commit("UPDATE booking_user SET user_id = ? WHERE booking_id = ?",
				new Object[] { user.getUid(), user.getBid() });
	}

	public void delete(BookingUser user) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM booking_agent WHERE booking_id = ?", new Object[] { user.getBid() });
	}

	@Override
	protected List<BookingUser> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if (rs != null) {
			List<BookingUser> results = new ArrayList<>();
			BookingUser item = new BookingUser();
			while (rs.next()) {
				item.setBid(rs.getInt(1));
				item.setUid(rs.getInt(2));
				results.add(item);
			}
			return results;
		}
		return null;
	}

}