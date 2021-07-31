/**
 * 
 */
package com.smoothstack.utopia.testing;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.smoothstack.utopia.dao.AirportDAO;
import com.smoothstack.utopia.domain.Airport;
import com.smoothstack.utopia.utils.ConnectionUtil;

/**
 * @author joshu
 *
 */

public class DAOTesting {
	@Test
	public void airportTest() {
		try (Connection conn = ConnectionUtil.getConnection()){
			AirportDAO testDAO = new AirportDAO(conn);
			Airport testAirport = new Airport();
			List<Airport> hold;
			List<String> check = new ArrayList<>();
			testAirport.setCode("ABC");
			testAirport.setCity("Test City");
			testDAO.insert(testAirport);
			hold = testDAO.query("SELECT * FROM Airport WHERE iata_id=?", new String[]{"ABC"});
			for(Airport a : hold) {
				check.add(a.getCode());
				check.add(a.getCity());
			}
			assertArrayEquals(new String[] {"ABC","Test City"}, check.toArray());
			testAirport.setCity("Test 2");
			testDAO.update(testAirport);
			hold = testDAO.query("SELECT * FROM Airport WHERE iata_id=?", new String[]{"ABC"});
			check.clear();
			for(Airport a : hold) {
				check.add(a.getCode());
				check.add(a.getCity());
			}
			assertArrayEquals(List.of("ABC","Test 2").toArray(), check.toArray());
			testDAO.delete(testAirport);
			hold = testDAO.query("SELECT * FROM Airport WHERE iata_id=?", new String[]{"ABC"});
			check.clear();
			for(Airport a : hold) {
				check.add(a.getCode());
				check.add(a.getCity());
			}
			assertArrayEquals(List.of().toArray(), check.toArray());
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
	}
}
