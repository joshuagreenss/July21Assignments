/**
 * 
 */
package com.smoothstack.utopia.testing;

import java.sql.SQLException;

import org.junit.Test;

import com.smoothstack.utopia.dao.AirportDAO;
import com.smoothstack.utopia.domain.Airport;

/**
 * @author joshu
 *
 */

public class DAOTesting {
	@Test
	public void airportTest() {
		AirportDAO testDAO = new AirportDAO(null);
		Airport testAirport = new Airport();
		testAirport.setCode("ABC");
		testAirport.setCity("Test City");
		try {
			AirportDAO.add
		}catch(SQLException | ClassNotFoundException e) {
			
		}
	}
}
