package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.UserRole;

public class UserRoleDAO extends DAO<UserRole> {

	public UserRoleDAO(Connection conn) {
		super(conn);
	}

	public void insert(UserRole role) throws ClassNotFoundException, SQLException {
		if (role.getId() != null) {
			this.commit("INSERT INTO user_role VALUES (?, ?)", new Object[] { role.getId(), role.getName() });
		} else {
			this.commit("INSERT INTO user_role(name) VALUES (?)", new Object[] { role.getName() });
		}
	}

	public void update(UserRole role) throws ClassNotFoundException, SQLException {
		this.commit("UPDATE user_role SET name = ? WHERE id = ?", new Object[] { role.getName(), role.getId() });
	}

	public void delete(UserRole role) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM user_role WHERE id = ?", new Object[] { role.getId() });
	}

	@Override
	protected List<UserRole> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if (rs != null) {
			List<UserRole> results = new ArrayList<>();
			UserRole item;
			while (rs.next()) {
				item = new UserRole();
				item.setId(rs.getInt(1));
				item.setName(rs.getString(2));
				results.add(item);
			}
			return results;
		}
		return null;
	}
}
