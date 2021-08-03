package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.smoothstack.utopia.dao.FlightDAO;
import com.smoothstack.utopia.domain.Flight;
import com.smoothstack.utopia.utils.ConnectionUtil;

public class FlightServices {
	private FlightDAO dao;
	private Connection conn;

	public FlightServices() {
		try {
			conn = ConnectionUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao = new FlightDAO(conn);
	}

	public void insert(Integer id, int route, int airplane, LocalDateTime departure, int reserved, float price) {
		try {
			try {
				Flight f = new Flight();
				if (id != null) {
					f.setId(id);
				}
				f.setRoute(route);
				f.setPlane(airplane);
				f.setDeparture(departure);
				f.setReserved(reserved);
				f.setPrice(price);
				dao.insert(f);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(int id, int route, int airplane, LocalDateTime departure, int reserved, float price) {
		try {
			try {
				Flight f = new Flight();
				f.setId(id);
				f.setRoute(route);
				f.setPlane(airplane);
				f.setDeparture(departure);
				f.setReserved(reserved);
				f.setPrice(price);
				dao.update(f);
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
				Flight f = new Flight();
				f.setId(id);
				dao.delete(f);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Flight> readAll() {
		String sql = "SELECT * FROM Flight";
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
