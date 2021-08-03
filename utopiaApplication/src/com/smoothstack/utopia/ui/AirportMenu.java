package com.smoothstack.utopia.ui;

import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.domain.Airport;
import com.smoothstack.utopia.services.AirportServices;

public class AirportMenu implements ObjectMenu {
	private Scanner s;
	private AirportServices service;

	public AirportMenu(Scanner s) {
		this.s = s;
		this.service = new AirportServices();
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
		String code;
		String city;
		System.out.println("Enter new value");

		System.out.println("Iata Code: ");
		code = s.next();

		System.out.println("City: ");
		city = s.next();
		
		service.insert(code,city);
	}

	@Override
	public void update(){
		int i = 1;
		int input = 0;
		String code;
		String city;
		List<Airport> as = service.readAll();
		for (Airport a : as) {
			System.out.println(i + ") Code: " + a.getCode() + " City: " + a.getCity());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			input = 0;
		}
		if (input > 0 && input <= as.size()) {
			code = as.get(input-1).getCode();
			System.out.println("Enter new value or N/A");

			System.out.println("City: ");
			city = s.next();
			if (city.equals("N/A")) {
				city = as.get(input-1).getCity();
			}

			service.update(code, city);
		}
	}

	@Override
	public void delete(){
		int i = 1;
		int input = 0;
		List<Airport> rs = service.readAll();
		for (Airport r : rs) {
			System.out.println(i + ") Code: " + r.getCode() + " City: " + r.getCity());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			input = 0;
		}
		if (input > 0 && input <= rs.size()) {
			service.delete(rs.get(input - 1).getCode());
		}
	}

	@Override
	public void read(){
		int i = 1;
		List<Airport> as = service.readAll();
		for (Airport a : as) {
			System.out.println(i + ") Code: " + a.getCode() + " City: " + a.getCity());
			i++;
		}
	}
}
