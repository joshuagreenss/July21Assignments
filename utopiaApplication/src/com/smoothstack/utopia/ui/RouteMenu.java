package com.smoothstack.utopia.ui;

import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.domain.Route;
import com.smoothstack.utopia.services.RouteServices;

public class RouteMenu implements ObjectMenu {
	private Scanner s;
	private RouteServices service;

	public RouteMenu(Scanner s) {
		this.s = s;
		this.service = new RouteServices();
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
		String orig;
		String dest;
		System.out.println("Enter new value");

		System.out.println("Origin Code: ");
		orig = s.next();

		System.out.println("Destination Code: ");
		dest = s.next();
		
		service.insert(null,orig,dest);
	}

	@Override
	public void update(){
		int i = 1;
		int id;
		int input = 0;
		String orig;
		String dest;
		List<Route> rs = service.readAll();
		for (Route r : rs) {
			System.out.println(i + ") Origin: " + r.getOrig() + " Destination: " + r.getDest());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			input = 0;
		}
		if (input > 0 && input <= rs.size()) {
			id = rs.get(input-1).getId();
			System.out.println("Enter new value or N/A");

			System.out.println("Origin: ");
			orig = s.next();
			if (orig.equals("N/A")) {
				orig = rs.get(input-1).getOrig();
			}

			System.out.println("Destination: ");
			dest = s.next();
			if (dest.equals("N/A")) {
				dest = rs.get(input - 1).getDest();
			}
			
			service.update(id, orig, dest);
		}
	}

	@Override
	public void delete(){
		int i = 1;
		int input = 0;
		List<Route> rs = service.readAll();
		for (Route r : rs) {
			System.out.println(i + ") Origin: " + r.getOrig() + " Destination: " + r.getDest());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			input = 0;
		}
		if (input > 0 && input <= rs.size()) {
			service.delete(rs.get(input - 1).getId());
		}
	}

	@Override
	public void read(){
		int i = 1;
		List<Route> rs = service.readAll();
		for (Route r : rs) {
			System.out.println(i + ") Origin: " + r.getOrig() + " Dest: " + r.getDest());
			i++;
		}
	}
}
