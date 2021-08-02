package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.Flight;

public class AgentHelperDAO extends FlightDAO {

	public AgentHelperDAO(Connection conn) {
		super(conn);
	}

	public List<List<Object>> getFlightRoutes() throws SQLException {
		List<List<Object>> response = new ArrayList<>();
		String sql = "SELECT origin_id, origin_city, destination_id, destination_city, max_capacity FROM "
				+ "(SELECT max_capacity, Flight.route_id AS id FROM "
				+ "(SELECT max_capacity, airplane.id AS id FROM airplane_type NATURAL JOIN airplane) AS p "
				+ "INNER JOIN Flight ON Flight.airplane_id = p.id) AS p2 "
				+ "INNER JOIN (SELECT origin_city, origin_id, destination_id, city AS destination_city, id "
				+ "FROM (SELECT city AS origin_city, origin_id, destination_id, route.id AS id "
				+ "FROM airport INNER JOIN route ON airport.iata_id=route.origin_id) AS origin "
				+ "INNER JOIN airport ON origin.destination_id=airport.iata_id) AS f2 ON p2.id = f2.id";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeQuery();
		ResultSet rs = pstmt.getResultSet();
		while (rs.next()) {
			List<Object> s = new ArrayList<>();
			s.add(rs.getString(1));
			s.add(rs.getString(2));
			s.add(rs.getString(3));
			s.add(rs.getString(4));
			s.add(rs.getInt(5));
			response.add(s);
		}
		return response;
	}

	public List<Flight> getSelection(String origin, String destination) throws SQLException, ClassNotFoundException {
		String sql = "SELECT Flight.id AS id, route_id, airplane_id, departure_time, reserved_seats, seat_price FROM flight INNER JOIN route ON route_id=route.id WHERE origin_id = ? AND destination_id = ?";
		return query(sql, new String[] { origin, destination });
	}
}
