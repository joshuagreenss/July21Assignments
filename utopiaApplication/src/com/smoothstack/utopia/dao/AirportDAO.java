/**
 * 
 */
package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.Airport;

/**
 * @author joshu
 *
 */
public class AirportDAO extends DAO<Airport> {
	public AirportDAO(Connection conn) {
		super(conn);
	}

	public void insert(Airport airport) throws ClassNotFoundException, SQLException {
		this.commit("INSERT INTO Airport VALUES (?, ?)", new String[] {airport.getCode(), airport.getCity()});
	}
	
	public void update(Airport airport) throws ClassNotFoundException, SQLException {
		this.commit("UPDATE Airport SET city = ? WHERE iata_id = ?", new String[] {airport.getCity(),airport.getCode()});
	}

	public void delete(Airport airport) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM Airport WHERE iata_id = ? AND city = ?", new String[] {airport.getCode(),airport.getCity()});
	}
	
	@Override
	protected List<Airport> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		if(rs != null) {
			List<Airport> results = new ArrayList<>();
			Airport item = new Airport();
			while(rs.next()) {
				item.setCode(rs.getString(1));
				item.setCity(rs.getString(2));
				results.add(item);
			}
			return results;
		}
		return null;
	}
}
