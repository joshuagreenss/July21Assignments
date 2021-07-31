/**
 * 
 */
package com.smoothstack.utopia.testing;

import static org.junit.Assert.assertArrayEquals;

import java.sql.SQLException;
import java.util.List;

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
			AirportDAO.insert(testAirport);
			assertArrayEquals(List.of("ABC","Test City").toArray(), AirportDAO.queryCity("Test City"));
			testAirport.setCity("Test 2");
			AirportDAO.update(testAirport);
			assertArrayEquals(List.of("ABC","Test 2").toArray(), AirportDAO.queryCode("ABC"));
			AirportDAO.delete(testAirport);
			assertArrayEquals(List.of().toArray(), AirportDAO.queryCode("ABC"));
			
		}catch(SQLException | ClassNotFoundException e) {
			
		}
	}
}
