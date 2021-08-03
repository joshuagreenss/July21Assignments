package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.smoothstack.utopia.dao.AirplaneDAO;
import com.smoothstack.utopia.domain.Airplane;
import com.smoothstack.utopia.utils.ConnectionUtil;

public class AirplaneServices {
	private AirplaneDAO dao;
	private Connection conn;

	public AirplaneServices() {
		try {
			conn = ConnectionUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao = new AirplaneDAO(conn);
	}

	public void insert(Integer id, int type) {
		try {
			try {
				Airplane a = new Airplane();
				if (id != null) {
					a.setId(id);
				}
				a.setType(type);
				dao.insert(a);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(int id, int type) {
		try {
			try {
				Airplane a = new Airplane();
				a.setId(id);
				a.setType(type);
				dao.update(a);
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
				Airplane a = new Airplane();
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

	public List<Airplane> readAll() {
		String sql = "SELECT * FROM Airplane";
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
