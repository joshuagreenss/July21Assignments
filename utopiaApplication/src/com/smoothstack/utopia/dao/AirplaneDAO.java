package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.Airplane;

public class AirplaneDAO extends DAO<Airplane> {

	public AirplaneDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void insert(Airplane airplane) throws ClassNotFoundException, SQLException {
		if (airplane.getId() != null) {
			this.commit("INSERT INTO Airplane VALUES (?, ?)", new Object[] { airplane.getId(), airplane.getType() });
		} else {
			this.commit("INSERT INTO Airplane (type) VALUES(?)", new Object[] { airplane.getType() });
		}
	}

	public void update(Airplane airplane) throws ClassNotFoundException, SQLException {
		this.commit("UPDATE Airplane SET type_id = ? WHERE id = ?",
				new Object[] { airplane.getType(), airplane.getId() });
	}

	public void delete(Airplane airplane) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM Airplane WHERE id = ?", new Object[] { airplane.getId()});
	}

	@Override
	protected List<Airplane> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if (rs != null) {
			List<Airplane> results = new ArrayList<>();
			Airplane item;
			while (rs.next()) {
				item = new Airplane();
				item.setId(rs.getInt(1));
				item.setType(rs.getInt(2));
				results.add(item);
			}
			return results;
		}
		return null;
	}
}
