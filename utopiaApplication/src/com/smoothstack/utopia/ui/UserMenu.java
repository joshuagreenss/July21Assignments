package com.smoothstack.utopia.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.dao.UserDAO;
import com.smoothstack.utopia.domain.User;

public class UserMenu implements ObjectMenu {
	private Scanner s;

	public UserMenu(Scanner s) {
		this.s = s;
	}

	@Override
	public void add(Connection conn) throws ClassNotFoundException, SQLException {
		User u = new User();
		String newVal;
		System.out.println("Enter new value");

		System.out.println("Role: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			u.setRole(Integer.valueOf(newVal));
		}

		System.out.println("Given Name: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			u.setgName(newVal);
		}

		System.out.println("Family Name: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			u.setfName(newVal);
		}

		System.out.println("Username: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			u.setUsername(newVal);
		}

		System.out.println("Email: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			u.setEmail(newVal);
		}

		System.out.println("Password: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			u.setPassword(newVal);
		}

		System.out.println("Phone: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			u.setPhone(newVal);
		}

		UserDAO dao = new UserDAO(conn);
		dao.insert(u);
	}

	@Override
	public void update(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM User";
		String newVal;
		int i = 1;
		int input = 0;
		UserDAO dao = new UserDAO(conn);
		List<User> us = dao.query(sql, null);
		for (User u : us) {
			System.out.println(i + ") id: " + u.getId() + " Role: " + u.getRole() + " Given Name: " + u.getgName()
					+ " Family Name: " + u.getfName() + " Username: " + u.getUsername() + " Email: " + u.getEmail()
					+ " Password: " + u.getPassword() + " Phone: " + u.getPhone());
			i++;
		}
		try {
			input = s.nextInt();
		}catch(Exception e) {
			System.out.println("Invalid selection");
			input = 0;
		}
		System.out.println("Enter new value or N/A");

		System.out.println("Role: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			us.get(input-1).setRole(Integer.valueOf(newVal));
		}

		System.out.println("Given Name: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			us.get(input-1).setgName(newVal);
		}

		System.out.println("Family Name: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			us.get(input-1).setfName(newVal);
		}

		System.out.println("Username: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			us.get(input-1).setUsername(newVal);
		}

		System.out.println("Email: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			us.get(input-1).setEmail(newVal);
		}

		System.out.println("Password: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			us.get(input-1).setPassword(newVal);
		}

		System.out.println("Phone: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			us.get(input-1).setPhone(newVal);
		}

		dao.insert(us.get(input-1));
	}

	@Override
	public void delete(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM User";
		int i = 1;
		int input = 0;
		UserDAO dao = new UserDAO(conn);
		List<User> us = dao.query(sql, null);
		for (User u : us) {
			System.out.println(i + ") id: " + u.getId() + " Role: " + u.getRole() + " Given Name: " + u.getgName()
					+ " Family Name: " + u.getfName() + " Username: " + u.getUsername() + " Email: " + u.getEmail()
					+ " Password: " + u.getPassword() + " Phone: " + u.getPhone());
			i++;
		}
		try {
			input = s.nextInt();
		}catch(Exception e) {
			System.out.println("Invalid selection");
			input = 0;
		}
		if (input > 0 && input <= us.size()) {
			dao.delete(us.get(input - 1));
		}
	}

	@Override
	public void read(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM User";
		int i = 1;
		UserDAO dao = new UserDAO(conn);
		List<User> us = dao.query(sql, null);
		for (User u : us) {
			System.out.println(i + ") id: " + u.getId() + " Role: " + u.getRole() + " Given Name: " + u.getgName()
					+ " Family Name: " + u.getfName() + " Username: " + u.getUsername() + " Email: " + u.getEmail()
					+ " Password: " + u.getPassword() + " Phone: " + u.getPhone());
			i++;
		}
	}
}
