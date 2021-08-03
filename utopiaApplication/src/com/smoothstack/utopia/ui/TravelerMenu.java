package com.smoothstack.utopia.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.smoothstack.utopia.dao.PassengerHelperDAO;
import com.smoothstack.utopia.domain.Booking;
import com.smoothstack.utopia.domain.BookingGuest;
import com.smoothstack.utopia.domain.BookingUser;
import com.smoothstack.utopia.domain.Flight;
import com.smoothstack.utopia.domain.Passenger;
import com.smoothstack.utopia.domain.User;
import com.smoothstack.utopia.utils.ConnectionUtil;

public class TravelerMenu {

	public static void mainMenu(Scanner s) {
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
	}

	private static void loginMenu(Scanner s) {
		String input = "Y";
		String username;
		String password;
		List<User> resp;
		User user;
		try (Connection conn = ConnectionUtil.getConnection()) {
			try {
				PassengerHelperDAO dao = new PassengerHelperDAO(conn);
				do {
					System.out.println("Username:");
					username = s.next();
					System.out.println("Password:");
					password = s.next();
					resp = dao.tryLogin(username, password);
					if (resp.size() != 0) {
						user = resp.get(0);
						System.out.println("Login successful!");
						loginSuccess(s, user, dao);
						input = "N";
					} else {
						System.out.println("Login failed. Try again? (Y/N)");
						input = s.next();
					}
				} while (!input.equals("N"));
				conn.commit();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				System.out.println("Error occurred, rolling back");
				conn.rollback();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private static void loginSuccess(Scanner s, User user, PassengerHelperDAO dao)
			throws SQLException, ClassNotFoundException {
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
				userBook(s, user, dao);
				break;
			case (2):
				userCancel(s, user, dao);
				break;
			case (3):
				break;
			default:
				System.out.println("Sorry, that's not a valid response");
			}
		}while (input != 3);
	}

	private static void userBook(Scanner s, User user, PassengerHelperDAO dao)
			throws SQLException, ClassNotFoundException {
		int input = 0;
		int i = 1;
		String orig;
		String dest;
		List<List<Object>> flights;
		List<Flight> sel;
		do {
			System.out.println("Which flight would you like to book?");
			flights = dao.getFlightRoutes();
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
				sel = dao.getSelection(orig, dest);
				confirmUserBook(s, user, orig, dest, dao, sel.get(0),
						Integer.valueOf(flights.get(input - 1).get(4).toString()));
			}
		} while (input != flights.size() + 1);
	}

	private static void confirmUserBook(Scanner s, User user, String orig, String dest, PassengerHelperDAO dao,
			Flight flight, int maxSeats) throws ClassNotFoundException, SQLException {
		if (maxSeats - flight.getReserved() == 0) {
			System.out.println("Sorry, that flight is already full");
			return;
		}
		int input = 0;
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
				Booking b = new Booking();
				b.setActive(1);
				b.setCode(genCode());
				BookingUser bu = new BookingUser();
				bu.setUid(user.getId());
				Passenger p = new Passenger();
				System.out.println("Given Name of Passenger:");
				p.setgName(s.next());
				System.out.println("Family Name of Passenger:");
				p.setfName(s.next());
				System.out.println("Birth Date of Passenger (yyyy-mm-dd):");
				p.setDob(LocalDate.parse(s.next()));
				System.out.println("Gender of Passenger (M/F): ");
				p.setGender(s.next());
				System.out.println("Address of Passenger:");
				p.setAddress(s.next());
				flight.setReserved(flight.getReserved() + 1);
				dao.insertUserBooking(bu, b, p, flight);
				break;
			case (3):
				break;
			}
		}while (input != 3);
	}

	private static void userCancel(Scanner s, User user, PassengerHelperDAO dao)
			throws SQLException, ClassNotFoundException {
		int input = 0;
		int i;
		List<List<Object>> fbs;
		do {
			System.out.println("Which booking would you like to cancel?");
			i = 1;
			fbs = dao.getBookedFlights(user);
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
				cancelConfirm(s, user, fbs.get(input - 1).get(2).toString(), fbs.get(input - 1).get(3).toString(), dao,
						(Flight) fbs.get(input - 1).get(0), (Integer) fbs.get(input - 1).get(1));
			}
		} while (input != fbs.size() + 1);
	}

	private static void cancelConfirm(Scanner s, User user, String orig, String dest, PassengerHelperDAO dao,
			Flight flight, int bid) throws ClassNotFoundException, SQLException {
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
				dao.cancel(flight, bid);
				input = 3;
				break;
			case (3):
				break;
			default:
				System.out.println("Sorry, that's not a valid input");
			}
		} while (input != 3);
	}

	private static String genCode() {
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

	private static void guestLogin(Scanner s) {
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

	private static void guestMenu(Scanner s, Connection conn, BookingGuest bg)
			throws SQLException, ClassNotFoundException {
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

	private static void guestBook(Scanner s, Connection conn, BookingGuest bg)
			throws SQLException, ClassNotFoundException {
		PassengerHelperDAO phdao = new PassengerHelperDAO(conn);
		List<List<Object>> fs;
		int input = 0;
		int i = 1;
		String orig;
		String dest;
		int cap;
		do {
			fs = phdao.getFlightRoutes();
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
				Flight sel = phdao.getSelection(orig, dest).get(0);
				guestConfirmBook(s, phdao, orig, dest, bg, cap, sel);
			}

		} while (input != fs.size() + 1);
	}

	private static void guestConfirmBook(Scanner s, PassengerHelperDAO phdao, String orig, String dest, BookingGuest bg,
			int cap, Flight f) throws ClassNotFoundException, SQLException {
		if (cap - f.getReserved() <= 0) {
			System.out.println("Sorry, this flight is fully booked");
		}
		int input = 0;
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

				Passenger p = new Passenger();
				System.out.println("Given Name of Passenger:");
				p.setgName(s.next());
				System.out.println("Family Name of Passenger:");
				p.setfName(s.next());
				System.out.println("Birth Date of Passenger (yyyy-mm-dd):");
				p.setDob(LocalDate.parse(s.next()));
				System.out.println("Gender of Passenger (M/F): ");
				p.setGender(s.next());
				System.out.println("Address of Passenger:");
				p.setAddress(s.next());

				f.setReserved(f.getReserved() + 1);
				phdao.insertGuestBooking(bg, b, p, f);
				input = 3;
				break;
			case (3):
				break;
			default:
				System.out.println("Sorry, that's not a valid input");
			}
		}while (input != 3);
	}

	private static void guestCancel(Scanner s, Connection conn, BookingGuest bg)
			throws SQLException, ClassNotFoundException {
		PassengerHelperDAO dao = new PassengerHelperDAO(conn);
		List<List<Object>> fbs;
		int input = 0;
		int i;
		do {
			System.out.println("Which booking would you like to cancel?");
			i = 1;
			fbs = dao.getGuestFlights(bg);
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
						dao, (Flight) fbs.get(input - 1).get(0), (Integer) fbs.get(input - 1).get(1));
			}
		} while (input != fbs.size() + 1);
	}

	private static void guestCancelConfirm(Scanner s, BookingGuest bg, String orig, String dest, PassengerHelperDAO dao,
			Flight flight, int bid) throws ClassNotFoundException, SQLException {
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
				dao.cancel(flight, bid);
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
