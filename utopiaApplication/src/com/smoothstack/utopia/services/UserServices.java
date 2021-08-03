package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.smoothstack.utopia.dao.UserDAO;
import com.smoothstack.utopia.domain.User;
import com.smoothstack.utopia.utils.ConnectionUtil;

public class UserServices {
	private UserDAO dao;
	private Connection conn;

	public UserServices() {
		try {
			conn = ConnectionUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao = new UserDAO(conn);
	}

	public void insert(Integer id, int role, String gName, String fName, String username, String email, String password, String phone) {
		try {
			try {
				User u = new User();
				if (id != null) {
					u.setId(id);
				}
				u.setRole(role);
				u.setgName(gName);
				u.setfName(fName);
				u.setUsername(username);
				u.setEmail(email);
				u.setPassword(password);
				u.setPhone(phone);
				dao.update(u);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Integer id, int role, String gName, String fName, String username, String email, String password, String phone) {
		try {
			try {
				User u = new User();
				u.setId(id);
				u.setRole(role);
				u.setgName(gName);
				u.setfName(fName);
				u.setUsername(username);
				u.setEmail(email);
				u.setPassword(password);
				u.setPhone(phone);
				dao.update(u);
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
				User u = new User();
				u.setId(id);
				dao.delete(u);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> readAll() {
		String sql = "SELECT * FROM User";
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
