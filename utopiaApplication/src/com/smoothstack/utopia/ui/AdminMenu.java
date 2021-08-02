package com.smoothstack.utopia.ui;

import java.util.Scanner;

public class AdminMenu {
	public static void mainMenu(Scanner s) {
		int input = 0;
		ObjectMenu m;
		do {
			System.out.println("1) Add/Update/Delete/Read Flights");
			System.out.println("2) Add/Update/Delete/Read Planes");
			System.out.println("3) Add/Update/Delete/Read Passengers");
			System.out.println("4) Add/Update/Delete/Read Airports");
			System.out.println("5) Add/Update/Delete/Read Routes");
			System.out.println("6) Add/Update/Delete/Read Users");
			System.out.println("7) Override Cancellation");
			System.out.println("8) Return to main menu");
			try {
			input = s.nextInt();
			}catch(Exception e) {
				System.out.println("Invalid input");
			}
			switch (input) {
			case (1):
				m = new FlightMenu(s);
				m.mainMenu(s);
				break;
			case (2):
				m = new PlaneMenu(s);
				m.mainMenu(s);
				break;
			case (3):
				m = new PassengerMenu(s);
				m.mainMenu(s);
				break;
			case (4):
				m = new AirportMenu(s);
				m.mainMenu(s);
				break;
			case (5):
				m = new RouteMenu(s);
				m.mainMenu(s);
				break;
			case (6):
				m = new UserMenu(s);
				m.mainMenu(s);
				break;
			case (7):
				break;
			case (8):
				break;
			default:
				System.out.println("Invalid input");
			}
		} while (input != 8);
	}
}
