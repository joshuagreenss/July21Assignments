package com.smoothstack.utopia.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.smoothstack.utopia.dao.AgentHelperDAO;
import com.smoothstack.utopia.dao.FlightDAO;
import com.smoothstack.utopia.domain.Flight;
import com.smoothstack.utopia.utils.ConnectionUtil;

public class AgentServices {
	private FlightDAO fdao;
	private AgentHelperDAO ahdao;
	private Connection conn;

	public AgentServices() {
		try {
			conn = ConnectionUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fdao = new FlightDAO(conn);
		ahdao = new AgentHelperDAO(conn);
	}

	public List<List<Object>> readAll() {
		try {
			return ahdao.getFlightRoutes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Flight> getSelection(String origin, String destination) {
		try {
			return ahdao.getSelection(origin, destination);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void update(int id, int route, int airplane, LocalDateTime departure, int reserved, float price) {
		try {
			try {
				Flight f = new Flight();
				f.setId(id);
				f.setRoute(route);
				f.setPlane(airplane);
				f.setDeparture(departure);
				f.setReserved(reserved);
				f.setPrice(price);
				fdao.update(f);
			} catch (ClassNotFoundException | SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
