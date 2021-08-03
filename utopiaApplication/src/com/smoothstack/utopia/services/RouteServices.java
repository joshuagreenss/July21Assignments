package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.smoothstack.utopia.dao.RouteDAO;
import com.smoothstack.utopia.domain.Route;
import com.smoothstack.utopia.utils.ConnectionUtil;

public class RouteServices {
	private RouteDAO dao;
	private Connection conn;

	public RouteServices() {
		try {
			conn = ConnectionUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao = new RouteDAO(conn);
	}

	public void insert(Integer id, String orig, String dest) {
		try {
			try {
				Route r = new Route();
				if (id != null) {
					r.setId(id);
				}
				r.setOrig(orig);
				r.setDest(dest);
				dao.insert(r);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(int id, String orig, String dest) {
		try {
			try {
				Route r = new Route();
				r.setId(id);
				r.setOrig(orig);
				r.setDest(dest);
				dao.insert(r);
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
				Route r = new Route();
				r.setId(id);
				dao.delete(r);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Route> readAll() {
		String sql = "SELECT * FROM Route";
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
