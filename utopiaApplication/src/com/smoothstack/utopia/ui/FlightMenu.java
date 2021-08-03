package com.smoothstack.utopia.ui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.domain.Flight;
import com.smoothstack.utopia.services.FlightServices;

public class FlightMenu implements ObjectMenu {
	private Scanner s;
	private FlightServices service;

	public FlightMenu(Scanner s) {
		this.s = s;
		this.service = new FlightServices();
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
		int route;
		int airplane;
		LocalDateTime departure;
		int reserved;
		float price;

		System.out.println("Enter new value");

		System.out.println("Route: ");
		try {
			route = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			return;
		}

		System.out.println("Airplane: ");
		try {
			airplane = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			return;
		}

		System.out.println("Departure (yyyy-mm-dd hh:mm:ss) : ");
		try {
			departure = LocalDateTime.parse(s.next() + "T" + s.next());
		} catch (Exception e) {
			System.out.println("Invalid date format");
			return;
		}

		System.out.println("Reserved Seats: ");
		try {
			reserved = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			return;
		}

		System.out.println("Seat Price: ");
		price = Float.valueOf(s.next());

		service.insert(null, route, airplane, departure, reserved, price);
	}

	@Override
	public void update() {
		String newVal;
		int i = 1;
		int input = 0;
		int id;
		int route;
		int airplane;
		LocalDateTime departure;
		int reserved;
		float price;

		List<Flight> fs = service.readAll();
		for (Flight f : fs) {
			System.out.println(i + ") id: " + f.getId() + " Route: " + f.getRoute() + " Airplane: " + f.getPlane()
					+ " Departure Time: " + f.getDeparture() + " Reserved Seats: " + f.getReserved() + " Seat Price: "
					+ f.getPrice());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			input = 0;
		}
		id = fs.get(input - 1).getId();
		System.out.println("Enter new value or N/A");

		System.out.println("Route: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			try {
				route = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				return;
			}
		} else {
			route = fs.get(input - 1).getRoute();
		}

		System.out.println("Airplane: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			try {
				airplane = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				return;
			}
		} else {
			airplane = fs.get(input - 1).getPlane();
		}

		System.out.println("Departure (yyyy-mm-dd hh:mm:ss) : ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			try {
				departure = LocalDateTime.parse(newVal + "T" + s.next());
			} catch (Exception e) {
				System.out.println("Invalid date format");
				return;
			}
		} else {
			departure = fs.get(input - 1).getDeparture();
		}

		System.out.println("Reserved Seats: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			try {
				reserved = Integer.valueOf(newVal);
			} catch (Exception e) {
				System.out.println("Invalid input");
				return;
			}
		} else {
			reserved = fs.get(input - 1).getReserved();
		}
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			try {
				price = Float.valueOf(newVal);
			} catch (Exception e) {
				System.out.println("Invalid input");
				return;
			}
		} else {
			price = fs.get(input - 1).getPrice();
		}

		service.update(id, route, airplane, departure, reserved, price);
	}

	@Override
	public void delete() {
		int i = 1;
		int input = 0;
		List<Flight> fs = service.readAll();
		for (Flight f : fs) {
			System.out.println(i + ") id: " + f.getId() + " Route: " + f.getRoute() + " Airplane: " + f.getPlane()
					+ " Departure Time: " + f.getDeparture() + " Reserved Seats: " + f.getReserved() + " Seat Price: "
					+ f.getPrice());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			input = 0;
		}
		if (input > 0 && input <= fs.size()) {
			service.delete(fs.get(input - 1).getId());
		}
	}

	@Override
	public void read() {
		int i = 1;
		List<Flight> fs = service.readAll();
		for (Flight f : fs) {
			System.out.println(i + ") id: " + f.getId() + " Route: " + f.getRoute() + " Airplane: " + f.getPlane()
					+ " Departure Time: " + f.getDeparture() + " Reserved Seats: " + f.getReserved() + " Seat Price: "
					+ f.getPrice());
			i++;
		}
	}
}
