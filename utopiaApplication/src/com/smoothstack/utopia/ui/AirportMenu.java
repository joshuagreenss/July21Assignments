package com.smoothstack.utopia.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.dao.AirportDAO;
import com.smoothstack.utopia.domain.Airport;

public class AirportMenu implements ObjectMenu {
	private Scanner s;

	public AirportMenu(Scanner s) {
		this.s = s;
	}

	@Override
	public void add(Connection conn) throws ClassNotFoundException, SQLException {
		Airport a = new Airport();
		String newVal;
		System.out.println("Enter new value");

		System.out.println("Code: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			a.setCode(newVal);
		}

		System.out.println("City: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			a.setCity(newVal);
		}
		AirportDAO dao = new AirportDAO(conn);
		dao.insert(a);
	}

	@Override
	public void update(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Airport";
		String newVal;
		int i = 1;
		AirportDAO dao = new AirportDAO(conn);
		List<Airport> as = dao.query(sql, null);
		for (Airport a : as) {
			System.out.println(i + ") id: " + a.getCode() + " city: " + a.getCity());
			i++;
		}
		int input = 0;
		try {
			input = s.nextInt();
		}catch(Exception e) {
			System.out.println("Invalid selection");
			input = 0;
		}
		System.out.println("Enter new value or N/A");

		System.out.println("City: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			as.get(input - 1).setCity(newVal);
		}
		dao.update(as.get(input - 1));
	}

	@Override
	public void delete(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Airport";
		int i = 1;
		int input = 0;
		AirportDAO dao = new AirportDAO(conn);
		List<Airport> as = dao.query(sql, null);
		for (Airport a : as) {
			System.out.println(i + ") id: " + a.getCode() + " city: " + a.getCity());
			i++;
		}
		try {
			input = s.nextInt();
		}catch(Exception e) {
			System.out.println("Invalid selection");
			input = 0;
		}
		if (input > 0 && input <= as.size()) {
			dao.delete(as.get(input - 1));
		}
	}

	@Override
	public void read(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Airport";
		int i = 1;
		AirportDAO dao = new AirportDAO(conn);
		List<Airport> as = dao.query(sql, null);
		for (Airport a : as) {
			System.out.println(i + ") id: " + a.getCode() + " city: " + a.getCity());
			i++;
		}
	}

}
