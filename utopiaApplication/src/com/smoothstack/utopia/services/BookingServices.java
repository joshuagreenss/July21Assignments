package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.smoothstack.utopia.dao.BookingDAO;
import com.smoothstack.utopia.domain.Booking;
import com.smoothstack.utopia.utils.ConnectionUtil;

public class BookingServices {
	private BookingDAO dao;
	private Connection conn;

	public BookingServices() {
		try {
			conn = ConnectionUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao = new BookingDAO(conn);
	}

	public void insert(Integer id, int active, String confirmation_code) {
		try {
			try {
				Booking b = new Booking();
				if (id != null) {
					b.setId(id);
				}
				b.setActive(active);
				b.setCode(confirmation_code);
				dao.insert(b);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(int id, int active, String confirmation_code) {
		try {
			try {
				Booking b = new Booking();
				b.setActive(active);
				b.setCode(confirmation_code);
				dao.update(b);
			}catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try {
			try {
				Booking b = new Booking();
				b.setId(id);
				dao.delete(b);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Booking> readAll() {
		String sql = "SELECT * FROM Booking";
		try {
			try {
				return dao.query(sql, null);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
