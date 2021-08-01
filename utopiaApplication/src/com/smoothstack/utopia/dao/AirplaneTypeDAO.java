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
		this.commit("INSERT INTO AirplaneType VALUES (?, ?)", new Object[] {type.getId(), type.getCapacity()});
	}
	
	public void update(AirplaneType type) throws ClassNotFoundException, SQLException {
		this.commit("UPDATE AirplaneType SET city = ? WHERE id = ?", new Object[] {type.getCapacity(),type.getId()});
	}

	public void delete(AirplaneType type) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM AirplaneType WHERE iata_id = ? AND city = ?", new Object[] {type.getId(),type.getCapacity()});
	}
	
	@Override
	protected List<AirplaneType> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if(rs != null) {
			List<AirplaneType> results = new ArrayList<>();
			AirplaneType item = new AirplaneType();
			while(rs.next()) {
				item.setId(rs.getInt(1));
				item.setCapacity(rs.getInt(2));
				results.add(item);
			}
			return results;
		}
		return null;
	}
}
