package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.User;

public class UserDAO extends DAO<User> {

	public UserDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void insert(User user) throws ClassNotFoundException, SQLException {
		if (user.getId() != null) {
			this.commit("INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
					new Object[] { user.getId(), user.getRole(), user.getgName(), user.getfName(), user.getUsername(),
							user.getEmail(), user.getPassword(), user.getPhone() });
		} else {
			this.commit(
					"INSERT INTO user(role_id, given_name, family_name, username, email, password, phone) VALUES (?,?,?,?,?,?,?)",
					new Object[] { user.getRole(), user.getgName(), user.getfName(), user.getUsername(),
							user.getEmail(), user.getPassword(), user.getPhone() });
		}
	}

	public void update(User user) throws ClassNotFoundException, SQLException {
		this.commit(
				"UPDATE user SET role_id = ?, given_name = ?, family_name = ?, username = ?, email = ?, password = ?, phone = ? WHERE id = ?",
				new Object[] { user.getRole(), user.getgName(), user.getfName(), user.getUsername(), user.getEmail(),
						user.getPassword(), user.getPhone(), user.getId() });
	}

	public void delete(User user) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM user WHERE id = ?", new Object[] { user.getId() });
	}

	@Override
	protected List<User> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if (rs != null) {
			List<User> results = new ArrayList<>();
			User item;
			while (rs.next()) {
				item = new User();
				item.setId(rs.getInt(1));
				item.setRole(rs.getInt(2));
				item.setgName(rs.getString(3));
				item.setfName(rs.getString(4));
				item.setUsername(rs.getString(5));
				item.setEmail(rs.getString(6));
				item.setPassword(rs.getString(7));
				item.setPhone(rs.getString(8));
				results.add(item);
			}
			return results;
		}
		return null;
	}
}
