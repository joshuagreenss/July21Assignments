package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.smoothstack.utopia.dao.PassengerDAO;
import com.smoothstack.utopia.domain.Passenger;
import com.smoothstack.utopia.utils.ConnectionUtil;

public class PassengerServices {
	private PassengerDAO dao;
	private Connection conn;

	public PassengerServices() {
		try {
			conn = ConnectionUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao = new PassengerDAO(conn);
	}

	public void insert(Integer id, int bid, String gName, String fName, LocalDate dob, String gender, String address) {
		try {
			try {
				Passenger p = new Passenger();
				if (id != null) {
					p.setId(id);
				}
				p.setBid(bid);
				p.setgName(gName);
				p.setfName(fName);
				p.setDob(dob);
				p.setGender(gender);
				p.setAddress(address);
				dao.insert(p);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(int id, int bid, String gName, String fName, LocalDate dob, String gender, String address) {
		try {
			try {
				Passenger p = new Passenger();
				p.setId(id);
				p.setBid(bid);
				p.setgName(gName);
				p.setfName(fName);
				p.setDob(dob);
				p.setGender(gender);
				p.setAddress(address);
				dao.update(p);
			} catch (ClassNotFoundException | SQLException e) {
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
				Passenger a = new Passenger();
				a.setId(id);
				dao.delete(a);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Passenger> readAll() {
		String sql = "SELECT * FROM Passenger";
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
