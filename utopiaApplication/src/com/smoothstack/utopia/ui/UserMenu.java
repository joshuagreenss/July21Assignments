package com.smoothstack.utopia.ui;

import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.domain.User;
import com.smoothstack.utopia.services.UserServices;

public class UserMenu implements ObjectMenu {
	private Scanner s;
	private UserServices service;

	public UserMenu(Scanner s) {
		this.s = s;
		service = new UserServices();
	}

	@Override
	public void mainMenu(Scanner s) {
		int input = 0;
		do {
			System.out.println("1) Add");
			System.out.println("2) Update");
			System.out.println("3) Delete");
			System.out.println("4) Read");
			System.out.println("5) Return");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				input = 0;
			}
			switch (input) {
			case (1):
				add();
				break;
			case (2):
				update();
				break;
			case (3):
				delete();
				break;
			case (4):
				read();
				break;
			case (5):
				break;
			default:
				System.out.println("Invalid input");
			}
		} while (input != 5);
		service.commit();
		service.close();
	}

	@Override
	public void add() {
		int role;
		String gName;
		String fName;
		String username;
		String email;
		String password;
		String phone;
		System.out.println("Enter new value");

		System.out.println("Role: ");
		role = Integer.valueOf(s.next());

		System.out.println("Given Name: ");
		gName = s.next();

		System.out.println("Family Name: ");
		fName = s.next();

		System.out.println("Username: ");
		username = s.next();

		System.out.println("Email: ");
		email = s.next();

		System.out.println("Password: ");
		password = s.next();

		System.out.println("Phone: ");
		phone = s.next();

		service.insert(null, role, gName, fName, username, email, password, phone);
	}

	@Override
	public void update() {
		String newVal;
		int i = 1;
		int input = 0;
		int id;
		int role;
		String gName;
		String fName;
		String username;
		String email;
		String password;
		String phone;

		List<User> us = service.readAll();
		for (User u : us) {
			System.out.println(i + ") id: " + u.getId() + " Role: " + u.getRole() + " Given Name: " + u.getgName()
					+ " Family Name: " + u.getfName() + " Username: " + u.getUsername() + " Email: " + u.getEmail()
					+ " Password: " + u.getPassword() + " Phone: " + u.getPhone());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid selection");
			return;
		}
		System.out.println("Enter new value or N/A");
		id = us.get(input - 1).getId();

		System.out.println("Role: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			role = Integer.valueOf(newVal);
		} else {
			role = us.get(input - 1).getRole();
		}

		System.out.println("Given Name: ");
		gName = s.next();
		if (gName.equals("N/A")) {
			gName = us.get(input - 1).getgName();
		}

		System.out.println("Family Name: ");
		fName = s.next();
		if (fName.equals("N/A")) {
			fName = us.get(input - 1).getfName();
		}

		System.out.println("Username: ");
		username = s.next();
		if (username.equals("N/A")) {
			username = us.get(input - 1).getUsername();
		}

		System.out.println("Email: ");
		email = s.next();
		if (email.equals("N/A")) {
			email = us.get(input - 1).getEmail();
		}

		System.out.println("Password: ");
		password = s.next();
		if (password.equals("N/A")) {
			password = us.get(input - 1).getPassword();
		}

		System.out.println("Phone: ");
		phone = s.next();
		if (phone.equals("N/A")) {
			phone = us.get(input - 1).getPhone();
		}

		service.update(id, role, gName, fName, username, email, password, phone);
	}

	@Override
	public void delete() {
		int i = 1;
		int input = 0;
		List<User> us = service.readAll();
		for (User u : us) {
			System.out.println(i + ") id: " + u.getId() + " Role: " + u.getRole() + " Given Name: " + u.getgName()
					+ " Family Name: " + u.getfName() + " Username: " + u.getUsername() + " Email: " + u.getEmail()
					+ " Password: " + u.getPassword() + " Phone: " + u.getPhone());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid selection");
			input = 0;
		}
		if (input > 0 && input <= us.size()) {
			service.delete(us.get(input - 1).getId());
		}
	}

	@Override
	public void read() {
		int i = 1;
		List<User> us = service.readAll();
		for (User u : us) {
			System.out.println(i + ") id: " + u.getId() + " Role: " + u.getRole() + " Given Name: " + u.getgName()
					+ " Family Name: " + u.getfName() + " Username: " + u.getUsername() + " Email: " + u.getEmail()
					+ " Password: " + u.getPassword() + " Phone: " + u.getPhone());
			i++;
		}
	}
}
