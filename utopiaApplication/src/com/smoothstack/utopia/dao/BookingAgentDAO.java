package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.BookingAgent;

public class BookingAgentDAO extends DAO<BookingAgent> {

	public BookingAgentDAO(Connection conn) {
		super(conn);
	}

	public void insert(BookingAgent agent) throws ClassNotFoundException, SQLException {
		this.commit("INSERT INTO booking_agent VALUES (?, ?)", new Object[] { agent.getBid(), agent.getAid() });
	}

	public void update(BookingAgent agent) throws ClassNotFoundException, SQLException {
		this.commit("UPDATE booking_agent SET agent_id = ? WHERE booking_id = ?",
				new Object[] { agent.getAid(), agent.getBid() });
	}

	public void delete(BookingAgent agent) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM booking_agent WHERE booking_id = ?", new Object[] { agent.getBid() });
	}

	@Override
	protected List<BookingAgent> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if (rs != null) {
			List<BookingAgent> results = new ArrayList<>();
			BookingAgent item = new BookingAgent();
			while (rs.next()) {
				item.setBid(rs.getInt(1));
				item.setAid(rs.getInt(2));
				results.add(item);
			}
			return results;
		}
		return null;
	}

}
