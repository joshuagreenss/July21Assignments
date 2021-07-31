/**
 * 
 */
package com.smoothstack.utopia.testing;

import java.sql.SQLException;

import org.junit.Test;

import com.smoothstack.utopia.dao.AirportDAO;

/**
 * @author joshu
 *
 */

public class DAOTesting {
	@Test
	public void airportTest() {
		AirportDAO test = new AirportDAO(null);
		try {
			
		}catch(SQLException | ClassNotFoundException e) {
			
		}
	}
}
