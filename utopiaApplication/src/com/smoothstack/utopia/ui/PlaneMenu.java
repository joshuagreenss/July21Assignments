package com.smoothstack.utopia.ui;

import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.domain.Airplane;
import com.smoothstack.utopia.services.AirplaneServices;

public class PlaneMenu implements ObjectMenu {
	private Scanner s;
	private AirplaneServices service;

	public PlaneMenu(Scanner s) {
		this.s = s;
		this.service = new AirplaneServices();
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
		int input;
		System.out.println("Enter new value");

		System.out.println("Type: ");
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			input = 0;
		}
		service.insert(null, input);
	}

	@Override
	public void update() {
		String newVal;
		int i = 1;
		int input = 0;

		List<Airplane> as = service.readAll();
		for (Airplane a : as) {
			System.out.println(i + ") id: " + a.getId() + " type: " + a.getType());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			input = 0;
		}
		System.out.println("Enter new value or N/A");

		System.out.println("Type: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			service.update(as.get(input - 1).getId(), Integer.valueOf(newVal));
		}
	}

	@Override
	public void delete() {
		int i = 1;
		int input = 0;
		List<Airplane> as = service.readAll();
		for (Airplane a : as) {
			System.out.println(i + ") id: " + a.getId() + " type: " + a.getType());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			input = 0;
		}
		if (input > 0 && input <= as.size()) {
			service.delete(as.get(input - 1).getId());
		}
	}

	@Override
	public void read() {
		int i = 1;
		List<Airplane> as = service.readAll();
		for (Airplane a : as) {
			System.out.println(i + ") id: " + a.getId() + " type: " + a.getType());
			i++;
		}
	}

	
}