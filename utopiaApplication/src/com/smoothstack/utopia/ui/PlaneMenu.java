package com.smoothstack.utopia.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.dao.AirplaneDAO;
import com.smoothstack.utopia.dao.AirportDAO;
import com.smoothstack.utopia.domain.Airplane;
import com.smoothstack.utopia.domain.Airport;

public class PlaneMenu implements ObjectMenu {
	private Scanner s;
	public PlaneMenu(Scanner s) {
		this.s = s;
	}

	@Override
	public void add(Connection conn) throws ClassNotFoundException, SQLException {
		Airplane a = new Airplane();
		String newVal;
		System.out.println("Enter new value");

		System.out.println("Type: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			a.setType(Integer.valueOf(newVal));
		}

		AirplaneDAO dao = new AirplaneDAO(conn);
		dao.insert(a);
	}

	@Override
	public void update(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Airplane";
		String newVal;
		int i = 1;
		AirplaneDAO dao = new AirplaneDAO(conn);
		List<Airplane> as = dao.query(sql, null);
		for (Airplane a : as) {
			System.out.println(i + ") id: " + a.getId() + " type: " + a.getType());
			i++;
		}
		int input = s.nextInt();
		System.out.println("Enter new value or N/A");

		System.out.println("Type: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			as.get(input - 1).setType(Integer.valueOf(newVal));
		}
		dao.update(as.get(input - 1));
	}

	@Override
	public void delete(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Airplane";
		int i = 1;
		int input = 0;
		AirplaneDAO dao = new AirplaneDAO(conn);
		List<Airplane> as = dao.query(sql, null);
		for (Airplane a : as) {
			System.out.println(i + ") id: " + a.getId() + " type: " + a.getType());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid Selection");
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
