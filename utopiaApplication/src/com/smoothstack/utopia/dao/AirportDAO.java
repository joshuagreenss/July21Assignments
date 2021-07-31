/**
 * 
 */
package com.smoothstack.utopia.dao;

import java.sql.Connection;

import com.smoothstack.utopia.domain.Airport;

/**
 * @author joshu
 *
 */
public class AirportDAO extends DAO<Airport> {
	public AirportDAO(Connection conn) {
		super(conn);
	}
}
