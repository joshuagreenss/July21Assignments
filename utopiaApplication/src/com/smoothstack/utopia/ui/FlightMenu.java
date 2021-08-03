package com.smoothstack.utopia.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.dao.FlightDAO;
import com.smoothstack.utopia.domain.Flight;

public class FlightMenu implements ObjectMenu {
	private Scanner s;

	public FlightMenu(Scanner s) {
		this.s = s;
	}

	@Override
	public void add(Connection conn) throws ClassNotFoundException, SQLException {
		Flight f = new Flight();
		String newVal;
		System.out.println("Enter new value");

		System.out.println("Route: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			f.setRoute(Integer.valueOf(newVal));
		}

		System.out.println("Plane: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			f.setPlane(Integer.valueOf(newVal));
		}

		System.out.println("Departure: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			f.setDeparture(LocalDateTime.parse(newVal));
		}

		System.out.println("Reserved Seats: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			f.setReserved(Integer.valueOf(newVal));
		}

		System.out.println("Price: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			f.setPrice(Float.valueOf(newVal));
		}
		FlightDAO dao = new FlightDAO(conn);
		dao.insert(f);
	}

	@Override
	public void update(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Flight";
		String newVal;
		int i = 1;
		int input = 0;
		FlightDAO dao = new FlightDAO(conn);
		List<Flight> fs = dao.query(sql, null);
		for (Flight f : fs) {
			System.out.println(i + ") id: " + f.getId() + " route: " + f.getRoute() + " plane: " + f.getPlane()
					+ " departure: " + f.getDeparture() + " reserved: " + f.getReserved() + " price: " + f.getPrice());
			i++;
		}
		try {
			input = s.nextInt();
		}catch(Exception e) {
			System.out.println("Invalid selection");
			input = 0;
		}
		System.out.println("Enter new value or N/A");

		System.out.println("Route: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			fs.get(input - 1).setRoute(Integer.valueOf(newVal));
		}

		System.out.println("Plane: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			fs.get(input - 1).setPlane(Integer.valueOf(newVal));
		}

		System.out.println("Departure (yyyy-mm-dd hh:mm:ss): ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			fs.get(input - 1).setDeparture(LocalDateTime.parse(newVal + "T" +s.next()));
		}

		System.out.println("Reserved Seats: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			fs.get(input - 1).setReserved(Integer.valueOf(newVal));
		}

		System.out.println("Price: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			fs.get(input - 1).setPrice(Float.valueOf(newVal));
		}
		dao.update(fs.get(input - 1));
	}

	@Override
	public void delete(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Flight";
		int i = 1;
		int input = 0;
		FlightDAO dao = new FlightDAO(conn);
		List<Flight> fs = dao.query(sql, null);
		for (Flight f : fs) {
			System.out.println(i + ") id: " + f.getId() + " route: " + f.getRoute() + " plane: " + f.getPlane()
					+ " departure: " + f.getDeparture() + " reserved: " + f.getReserved() + " price: " + f.getPrice());
			i++;
		}
		try {
			input = s.nextInt();
		}catch(Exception e) {
			System.out.println("Invalid selection");
			input = 0;
		}
		if (input > 0 && input <= fs.size()) {
			dao.delete(fs.get(input - 1));
		}
	}

	@Override
	public void read(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Flight";
		int i = 1;
		FlightDAO dao = new FlightDAO(conn);
		List<Flight> fs = dao.query(sql, null);
		for (Flight f : fs) {
			System.out.println(i + ") id: " + f.getId() + " route: " + f.getRoute() + " plane: " + f.getPlane()
					+ " departure: " + f.getDeparture() + " reserved: " + f.getReserved() + " price: " + f.getPrice());
			i++;
		}
	}
}
