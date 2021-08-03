package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.smoothstack.utopia.dao.AirportDAO;
import com.smoothstack.utopia.domain.Airport;
import com.smoothstack.utopia.utils.ConnectionUtil;

public class AirportServices {
	private AirportDAO dao;
	private Connection conn;

	public AirportServices() {
		try {
			conn = ConnectionUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao = new AirportDAO(conn);
	}

	public void insert(String code, String city) {
		try {
			try {
				Airport a = new Airport();
				a.setCode(code);
				a.setCity(city);
				dao.insert(a);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(String code, String city) {
		try {
			try {
				Airport a = new Airport();
				a.setCode(code);
				a.setCity(city);
				dao.update(a);
			}catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String code) {
		try {
			try {
				Airport a = new Airport();
				a.setCode(code);
				dao.delete(a);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Airport> readAll() {
		String sql = "SELECT * FROM Airport";
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
