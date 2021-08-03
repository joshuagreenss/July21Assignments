package com.smoothstack.utopia.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.smoothstack.utopia.domain.Booking;
import com.smoothstack.utopia.domain.BookingGuest;
import com.smoothstack.utopia.domain.Flight;
import com.smoothstack.utopia.domain.User;
import com.smoothstack.utopia.services.TravelerServices;
import com.smoothstack.utopia.utils.ConnectionUtil;

public class TravelerMenu {
	private TravelerServices service;

	public TravelerMenu() {
		service = new TravelerServices();
	}

	public void mainMenu(Scanner s) {
		int choice = 0;
		do {
			System.out.println("1) Login");
			System.out.println("2) Guest");
			System.out.println("3) Quit");
			choice = s.nextInt();
			switch (choice) {
			case (1):
				loginMenu(s);
				break;
			case (2):
				guestLogin(s);
				break;
			case (3):
				break;
			default:
				System.out.println("Sorry, that's not a valid choice");
			}
		} while (choice != 3);
		service.close();
	}

	private void loginMenu(Scanner s) {
		String input = "Y";
		String username;
		String password;
		List<User> resp;
		User user;
		do {
			System.out.println("Username:");
			username = s.next();
			System.out.println("Password:");
			password = s.next();
			resp = service.tryLogin(username, password);
			if (resp.size() != 0) {
				user = resp.get(0);
				System.out.println("Login successful!");
				loginSuccess(s, user);
				input = "N";
			} else {
				System.out.println("Login failed. Try again? (Y/N)");
				input = s.next();
			}
		} while (!input.equals("N"));
		service.commit();
	}

	private void loginSuccess(Scanner s, User user) {
		int input = 0;
		do {
			System.out.println("1) Book a flight");
			System.out.println("2) Cancel a flight");
			System.out.println("3) Quit to previous");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				input = 0;
			}
			switch (input) {
			case (1):
				userBook(s, user);
				break;
			case (2):
				userCancel(s, user);
				break;
			case (3):
				break;
			default:
				System.out.println("Sorry, that's not a valid response");
			}
		} while (input != 3);
	}

	private void userBook(Scanner s, User user) {
		int input = 0;
		int i = 1;
		String orig;
		String dest;
		List<List<Object>> flights;
		List<Flight> sel;
		do {
			System.out.println("Which flight would you like to book?");
			flights = service.readAll();
			i = 1;
			for (List<Object> f : flights) {
				System.out.println(i + ") " + f.get(0) + " " + f.get(1) + " -> " + f.get(2) + " " + f.get(3));
				i++;
			}
			System.out.println(i + ") Return to last menu");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				input = 0;
			}
			if (input > flights.size() + 1) {
				System.out.println("Sorry, that's not a valid response");
			} else if (input < flights.size() + 1) {
				orig = flights.get(input - 1).get(0).toString();
				dest = flights.get(input - 1).get(2).toString();
				sel = service.getSelection(orig, dest);
				confirmUserBook(s, user, orig, dest, sel.get(0),
						Integer.valueOf(flights.get(input - 1).get(4).toString()));
			}
		} while (input != flights.size() + 1);
	}

	private void confirmUserBook(Scanner s, User user, String orig, String dest, Flight flight, int maxSeats) {
		if (maxSeats - flight.getReserved() == 0) {
			System.out.println("Sorry, that flight is already full");
			return;
		}
		int input = 0;
		String gName;
		String fName;
		LocalDate dob;
		String gender;
		String address;
		do {
			System.out.println("1) View Flight Details");
			System.out.println("2) Confirm Booking");
			System.out.println("3) Return to Previous Menu");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				input = 0;
			}
			switch (input) {
			case (1):
				System.out.println("Flight from: " + orig + " to " + dest);
				System.out.println("Departure Time: " + flight.getDeparture());
				System.out.println("Seats Available: " + (maxSeats - flight.getReserved()));
				System.out.println("Seat Price: " + flight.getPrice());
				break;
			case (2):
				System.out.println("Given Name of Passenger:");
				gName = s.next();
				System.out.println("Family Name of Passenger:");
				fName = s.next();
				System.out.println("Birth Date of Passenger (yyyy-mm-dd):");
				dob = LocalDate.parse(s.next());
				System.out.println("Gender of Passenger (M/F): ");
				gender = s.next();
				System.out.println("Address of Passenger:");
				address = s.next();
				flight.setReserved(flight.getReserved() + 1);
				service.insertUserBooking(genCode(), user.getId(), gName, fName, dob, gender, address,
						flight.getReserved(), flight);
				break;
			case (3):
				break;
			}
		} while (input != 3);
	}

	private void userCancel(Scanner s, User user) {
		int input = 0;
		int i;
		List<List<Object>> fbs;
		do {
			System.out.println("Which booking would you like to cancel?");
			i = 1;
			fbs = service.getBookedFlights(user);
			for (List<Object> o : fbs) {
				System.out.println(i + ") " + o.get(2) + " -> " + o.get(3));
				i++;
			}
			System.out.println(i + ") Quit to previous menu");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				input = 0;
			}
			if (input <= fbs.size()) {
				cancelConfirm(s, user, fbs.get(input - 1).get(2).toString(), fbs.get(input - 1).get(3).toString(),
						(Flight) fbs.get(input - 1).get(0), (Integer) fbs.get(input - 1).get(1));
			}
		} while (input != fbs.size() + 1);
	}

	private void cancelConfirm(Scanner s, User user, String orig, String dest, Flight flight, int bid) {
		int input = 0;
		do {
			System.out.println("1) View Flight Details");
			System.out.println("2) Confirm Cancellation");
			System.out.println("3) Return to Previous Menu");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				input = 0;
			}
			switch (input) {
			case (1):
				System.out.println("Flight from: " + orig + " to " + dest);
				System.out.println("Departure Time: " + flight.getDeparture());
				System.out.println("Seat Price: " + flight.getPrice());
				break;
			case (2):
				flight.setReserved(flight.getReserved() - 1);
				service.cancel(flight, bid);
				input = 3;
				break;
			case (3):
				break;
			default:
				System.out.println("Sorry, that's not a valid input");
			}
		} while (input != 3);
	}

	private String genCode() {
		Random r = new Random();
		int val;
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < 25; i++) {
			val = r.nextInt(36);
			if (val < 10) {
				code.append(val);
			} else {
				code.append((char) (val + 55));
			}
		}
		return code.toString();
	}

	private void guestLogin(Scanner s) {
		BookingGuest bg = new BookingGuest();
		try (Connection conn = ConnectionUtil.getConnection()) {
			try {
				System.out.println("Please enter your email");
				bg.setEmail(s.next());
				System.out.println("Please enter your phone number");
				bg.setPhone(s.next());
				guestMenu(s, conn, bg);
			} catch (SQLException e) {
				conn.rollback();
				System.out.println("Error occurred, rolling back");
				e.printStackTrace();
			}
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private void guestMenu(Scanner s, Connection conn, BookingGuest bg) throws SQLException, ClassNotFoundException {
		int input = 0;
		do {
			System.out.println("What would you like to do?");
			System.out.println("1) Book Flight");
			System.out.println("2) Cancel Flight");
			System.out.println("3) Return to Previous Menu");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				input = 0;
			}
			switch (input) {
			case (1):
				guestBook(s, conn, bg);
				break;
			case (2):
				guestCancel(s, conn, bg);
				break;
			case (3):
				break;
			default:
				System.out.println("Sorry, that's not a valid input");
			}
		} while (input != 3);
	}

	private void guestBook(Scanner s, Connection conn, BookingGuest bg) throws SQLException, ClassNotFoundException {
		List<List<Object>> fs;
		int input = 0;
		int i = 1;
		String orig;
		String dest;
		int cap;
		do {
			fs = service.readAll();
			i = 1;
			for (List<Object> o : fs) {
				System.out.println(i + ") " + o.get(0) + " -> " + o.get(2));
				i++;
			}
			System.out.println(i + ") Return to previous menu");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				input = 0;
			}
			if (input > fs.size() + 1) {
				System.out.println("Sorry, that's not a valid input");
			} else if (input <= fs.size()) {
				orig = fs.get(input - 1).get(0).toString();
				dest = fs.get(input - 1).get(2).toString();
				cap = (Integer) fs.get(input - 1).get(4);
				Flight sel = service.getSelection(orig, dest).get(0);
				guestConfirmBook(s, orig, dest, bg, cap, sel);
			}

		} while (input != fs.size() + 1);
	}

	private void guestConfirmBook(Scanner s, String orig, String dest, BookingGuest bg, int cap, Flight f)
			throws ClassNotFoundException, SQLException {
		if (cap - f.getReserved() <= 0) {
			System.out.println("Sorry, this flight is fully booked");
		}
		int input = 0;
		String gName;
		String fName;
		LocalDate dob;
		String gender;
		String address;
		do {
			System.out.println("1) List Flight Details");
			System.out.println("2) Confirm Booking");
			System.out.println("3) Return to Previous Menu");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				input = 0;
			}
			switch (input) {
			case (1):
				System.out.println("Flight from: " + orig + " to " + dest);
				System.out.println("Departure Time: " + f.getDeparture());
				System.out.println("Seat Price: " + f.getPrice());
				break;
			case (2):
				Booking b = new Booking();
				b.setActive(1);
				b.setCode(genCode());

				System.out.println("Given Name of Passenger:");
				gName = s.next();
				System.out.println("Family Name of Passenger:");
				fName = s.next();
				System.out.println("Birth Date of Passenger (yyyy-mm-dd):");
				dob = LocalDate.parse(s.next());
				System.out.println("Gender of Passenger (M/F): ");
				gender = s.next();
				System.out.println("Address of Passenger:");
				address = s.next();

				service.insertGuestBooking(bg, genCode(), gName, fName, dob, gender, address, f.getReserved() + 1);
				input = 3;
				break;
			case (3):
				break;
			default:
				System.out.println("Sorry, that's not a valid input");
			}
		} while (input != 3);
	}

	private void guestCancel(Scanner s, Connection conn, BookingGuest bg) throws SQLException, ClassNotFoundException {
		List<List<Object>> fbs;
		int input = 0;
		int i;
		do {
			System.out.println("Which booking would you like to cancel?");
			i = 1;
			fbs = service.getGuestFlights(bg);
			for (List<Object> o : fbs) {
				System.out.println(i + ") " + o.get(2) + " -> " + o.get(3));
				i++;
			}
			System.out.println(i + ") Quit to previous menu");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				input = 0;
			}
			if (input <= fbs.size()) {
				guestCancelConfirm(s, bg, fbs.get(input - 1).get(2).toString(), fbs.get(input - 1).get(3).toString(),
						(Flight) fbs.get(input - 1).get(0), (Integer) fbs.get(input - 1).get(1));
			}
		} while (input != fbs.size() + 1);
	}

	private void guestCancelConfirm(Scanner s, BookingGuest bg, String orig, String dest, Flight flight, int bid)
			throws ClassNotFoundException, SQLException {
		int input = 0;
		do {
			System.out.println("1) View Flight Details");
			System.out.println("2) Confirm Cancellation");
			System.out.println("3) Return to Previous Menu");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				input = 0;
			}
			switch (input) {
			case (1):
				System.out.println("Flight from: " + orig + " to " + dest);
				System.out.println("Departure Time: " + flight.getDeparture());
				System.out.println("Seat Price: " + flight.getPrice());
				break;
			case (2):
				flight.setReserved(flight.getReserved() - 1);
				service.cancel(flight, bid);
				input = 3;
				break;
			case (3):
				break;
			default:
				System.out.println("Sorry, that's not a valid input");
			}
		} while (input != 3);
	}
}
