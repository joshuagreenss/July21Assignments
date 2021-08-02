package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.AirplaneType;

public class AirplaneTypeDAO extends DAO<AirplaneType> {
	public AirplaneTypeDAO(Connection conn) {
		super(conn);
	}

	public void insert(AirplaneType type) throws ClassNotFoundException, SQLException {
		if(type.getId() != null) {
		this.commit("INSERT INTO airplane_type VALUES (?, ?)", new Object[] {type.getId(), type.getCapacity()});
		}
		else {
			this.commit("INSERT INTO airplane_type(max_capacity) VALUES (?)", new Object[] {type.getCapacity()});
		}
	}
	
	public void update(AirplaneType type) throws ClassNotFoundException, SQLException {
		this.commit("UPDATE airplane_type SET max_capacity = ? WHERE id = ?", new Object[] {type.getCapacity(),type.getId()});
	}

	public void delete(AirplaneType type) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM airplane_type WHERE id = ?", new Object[] {type.getId()});
	}
	
	@Override
	protected List<AirplaneType> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if(rs != null) {
			List<AirplaneType> results = new ArrayList<>();
			AirplaneType item;
			while(rs.next()) {
				item = new AirplaneType();
				item.setId(rs.getInt(1));
				item.setCapacity(rs.getInt(2));
				results.add(item);
			}
			return results;
		}
		return null;
	}
}
