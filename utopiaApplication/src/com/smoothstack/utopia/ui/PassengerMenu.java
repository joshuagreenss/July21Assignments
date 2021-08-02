package com.smoothstack.utopia.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.dao.PassengerDAO;
import com.smoothstack.utopia.domain.Passenger;

public class PassengerMenu implements ObjectMenu {
	private Scanner s;

	public PassengerMenu(Scanner s) {
		this.s = s;
	}

	@Override
	public void add(Connection conn) throws ClassNotFoundException, SQLException {
		Passenger p = new Passenger();
		String newVal;
		System.out.println("Enter new value");

		System.out.println("Booking: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			p.setBid(Integer.valueOf(newVal));
		}

		System.out.println("Given Name: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			p.setgName(newVal);
		}

		System.out.println("Family Name: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			p.setfName(newVal);
		}

		System.out.println("Date of Birth: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			p.setDob(LocalDate.parse(newVal));
		}

		System.out.println("Gender: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			p.setGender(newVal);
		}

		System.out.println("Address: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			p.setAddress(newVal);
		}

		PassengerDAO dao = new PassengerDAO(conn);
		dao.insert(p);
	}

	@Override
	public void update(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Passenger";
		String newVal;
		int i = 1;
		PassengerDAO dao = new PassengerDAO(conn);
		List<Passenger> fs = dao.query(sql, null);
		for (Passenger f : fs) {
			System.out.println(i + ") id: " + f.getId() + " Booking: " + f.getBid() + " Given Name: " + f.getgName()
					+ " Family Name: " + f.getfName() + " Date of Birth: " + f.getDob() + " Gender: " + f.getGender()
					+ " Address: " + f.getAddress());
			i++;
		}
		int input = s.nextInt();
		System.out.println("Enter new value or N/A");

		System.out.println("Booking: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			fs.get(input - 1).setBid(Integer.valueOf(newVal));
		}

		System.out.println("Given Name: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			fs.get(input - 1).setgName(newVal);
		}

		System.out.println("Family Name: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			fs.get(input - 1).setfName(newVal);
		}

		System.out.println("Date of Birth (yyyy-mm-dd): ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			fs.get(input - 1).setDob(LocalDate.parse(newVal));
		}

		System.out.println("Gender: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			fs.get(input - 1).setGender(newVal);
		}

		System.out.println("Address: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			fs.get(input - 1).setAddress(newVal);
		}
		dao.update(fs.get(input - 1));
	}

	@Override
	public void delete(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Passenger";
		int i = 1;
		int input = 0;
		PassengerDAO dao = new PassengerDAO(conn);
		List<Passenger> ps = dao.query(sql, null);
		for (Passenger p : ps) {
			System.out.println(i + ") id: " + p.getId() + " Booking: " + p.getBid() + " Given Name: " + p.getgName()
					+ " Family Name: " + p.getfName() + " Date of Birth: " + p.getDob() + " Gender: " + p.getGender()
					+ " Address: " + p.getAddress());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid Selection");
		}
		if (input > 0 && input <= ps.size()) {
			dao.delete(ps.get(input - 1));
		}
	}

	@Override
	public void read(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Passenger";
		int i = 1;
		PassengerDAO dao = new PassengerDAO(conn);
		List<Passenger> ps = dao.query(sql, null);
		for (Passenger p : ps) {
			System.out.println(i + ") id: " + p.getId() + " Booking: " + p.getBid() + " Given Name: " + p.getgName()
					+ " Family Name: " + p.getfName() + " Date of Birth: " + p.getDob() + " Gender: " + p.getGender()
					+ " Address: " + p.getAddress());
			i++;
		}
	}
}
