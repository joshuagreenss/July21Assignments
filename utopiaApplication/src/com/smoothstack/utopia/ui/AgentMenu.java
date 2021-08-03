package com.smoothstack.utopia.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.domain.Flight;
import com.smoothstack.utopia.services.AgentServices;

public class AgentMenu {
	AgentServices service;

	public AgentMenu() {
		this.service = new AgentServices();
	}

	public void mainMenu(Scanner s) {
		int input = 0;
		while (input != 2) {
			System.out.println("1) Enter flights you manage");
			System.out.println("2) Quit to main");
			input = s.nextInt();
			switch (input) {
			case (1):
				flightSelectMenu(s);
				break;
			case (2):
				break;
			default:
				System.out.println("I'm sorry, that's not a valid input");
			}
		}
		service.commit();
		service.close();
	}

	private void flightSelectMenu(Scanner s) {
		int i = 1;
		int input = 0;
		List<List<Object>> frs = new ArrayList<>();
		List<Flight> selections;
		String orig, dest;
		int cap;
		System.out.println("Choose a flight");
		frs = service.readAll();
		while (input != frs.size() + 1) {
			i = 1;
			for (List<Object> fs : frs) {
				System.out.println(i + ") " + fs.get(0) + ", " + fs.get(1) + " -> " + fs.get(2) + ", " + fs.get(3));
				i++;
			}
			System.out.println(i + ") Return to previous menu");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid selection");
				input = 0;
			}
			if (input > frs.size() + 1) {
				System.out.println("Sorry, that's not a valid option");
			} else if (input == frs.size() + 1) {
				return;
			} else {
				orig = frs.get(input - 1).get(0).toString();
				dest = frs.get(input - 1).get(2).toString();
				cap = Integer.valueOf(frs.get(input - 1).get(4).toString());
				selections = service.getSelection(orig, dest);
				if (selections.size() != 0) {
					flightOptionMenu(s, selections.get(0), orig, dest, cap);
				} else {
					System.out.println("Sorry, there is no flight on that route");
				}
			}
		}
	}

	private void flightOptionMenu(Scanner s, Flight f, String orig, String dest, int maxSeats){
		int input = 0;
		while (input != 4) {
			System.out.println("1) View more details");
			System.out.println("2) Update details");
			System.out.println("3) Add Seats");
			System.out.println("4) Return to previous menu");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid selection");
				input = 0;
			}
			switch (input) {
			case (1):
				System.out.println("Flight ID: " + f.getId());
				System.out.println("Plane ID: " + f.getPlane());
				System.out.println("Departure Airport: " + orig);
				System.out.println("Arrival Airport: " + dest);
				System.out.println("Departure: " + f.getDeparture());
				System.out.println("Available Seats: " + (maxSeats - f.getReserved()));
				System.out.println("Seat Price: " + f.getPrice());
				break;
			case (2):
				updateFlight(s, f);
				break;
			case (3):
				System.out.println("Maximum number of seats: " + maxSeats);
				System.out.println("Reserved Seats: " + f.getReserved());
				System.out.println("How many seats to free?");
				f.setReserved(f.getReserved() - s.nextInt());
				service.update(f.getId(),f.getRoute(),f.getPlane(),f.getDeparture(),f.getReserved(),f.getPrice());
				break;
			case (4):
				return;
			default:
				System.out.println("Sorry, that's not a valid response");
			}
		}
	}

	private void updateFlight(Scanner s, Flight f){
		System.out.println("Enter quit at any prompt to cancel");
		String input;
		System.out.println("Enter new departure time (yyyy-mm-dd hh:mm:ss) or N/A for no change");
		input = s.next();
		if (input.equals("quit")) {
			return;
		} else if (!input.equals("N/A")) {
			try {
				f.setDeparture(LocalDateTime.parse(input));
			} catch (Exception e) {
				System.out.println("Invalid format");
			}
		}
		System.out.println("Enter new number of reserved seats or N/A for no change");
		input = s.next();
		if (input.equals("quit")) {
			return;
		} else if (!input.equals("N/A")) {
			try {
				f.setReserved(Integer.valueOf(input));
			} catch (Exception e) {
				System.out.println("Invalid format");
			}
		}
		System.out.println("Enter new seat price or N/A for no change");
		input = s.next();
		if (input.equals("quit")) {
			return;
		} else if (!input.equals("N/A")) {
			try {
				f.setPrice(Float.valueOf(input));
			} catch (Exception e) {
				System.out.println("Invalid format");
			}
		}
		service.update(f.getId(),f.getRoute(),f.getPlane(),f.getDeparture(),f.getReserved(),f.getPrice());
	}
}
