package com.smoothstack.utopia.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.domain.Passenger;
import com.smoothstack.utopia.services.PassengerServices;

public class PassengerMenu implements ObjectMenu {
	private Scanner s;
	private PassengerServices service;
	public PassengerMenu(Scanner s) {
		this.s = s;
		this.service = new PassengerServices();
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
	public void add(){
		int booking;
		String gName;
		String fName;
		LocalDate dob;
		String gender;
		String address;
		System.out.println("Enter new value");

		System.out.println("Booking: ");
		try {
			booking = s.nextInt();
		}catch(Exception e){
			System.out.println("Invalid input");
			return;
		}

		System.out.println("Given Name: ");
		gName = s.next();

		System.out.println("Family Name: ");
		fName = s.next();
		
		System.out.println("Date of Birth (yyyy-mm-dd) : ");
		dob = LocalDate.parse(s.next());

		System.out.println("Gender: ");
		gender = s.next();
		
		System.out.println("Address: ");
		address = s.next();
		
		service.insert(null, booking, gName, fName, dob, gender, address);
	}
	
	@Override
	public void update(){
		String newVal;
		int i = 1;
		int input = 0;
		int id;
		int booking;
		String gName;
		String fName;
		LocalDate dob;
		String gender;
		String address;
		
		List<Passenger> fs = service.readAll();
		for (Passenger f : fs) {
			System.out.println(i + ") id: " + f.getId() + " Booking: " + f.getBid() + " Given Name: " + f.getgName()
					+ " Family Name: " + f.getfName() + " Date of Birth: " + f.getDob() + " Gender: " + f.getGender()
					+ " Address: " + f.getAddress());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			input = 0;
		}
		id = fs.get(input-1).getId();
		System.out.println("Enter new value or N/A");

		System.out.println("Booking: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			booking = Integer.valueOf(newVal);
		}
		else {
			booking = fs.get(input-1).getBid();
		}

		System.out.println("Given Name: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			gName = newVal;
		}
		else {
			gName = fs.get(input-1).getgName();
		}

		System.out.println("Family Name: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			fName = newVal;
		}
		else {
			fName = fs.get(input-1).getfName();
		}

		System.out.println("Date of Birth (yyyy-mm-dd): ");
		if (!newVal.equals("N/A")) {
			dob = LocalDate.parse(newVal);
		}
		else {
			dob = fs.get(input-1).getDob();
		}

		System.out.println("Gender: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			gender = newVal;
		}
		else {
			gender = fs.get(input-1).getGender();
		}

		System.out.println("Address: ");
		newVal = s.next();
		if (!newVal.equals("N/A")) {
			address = newVal;
		}
		else {
			address = fs.get(input-1).getAddress();
		}
		service.update(id, booking, gName, fName, dob, gender, address);
	}

	@Override
	public void delete(){
		int i = 1;
		int input = 0;
		List<Passenger> ps = service.readAll();
		for (Passenger p : ps) {
			System.out.println(i + ") id: " + p.getId() + " Booking: " + p.getBid() + " Given Name: " + p.getgName()
					+ " Family Name: " + p.getfName() + " Date of Birth: " + p.getDob() + " Gender: " + p.getGender()
					+ " Address: " + p.getAddress());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input");
			input = 0;
		}
		if (input > 0 && input <= ps.size()) {
			service.delete(ps.get(input - 1).getId());
		}
	}

	@Override
	public void read(){
		int i = 1;
		List<Passenger> ps = service.readAll();
		for (Passenger p : ps) {
			System.out.println(i + ") id: " + p.getId() + " Booking: " + p.getBid() + " Given Name: " + p.getgName()
					+ " Family Name: " + p.getfName() + " Date of Birth: " + p.getDob() + " Gender: " + p.getGender()
					+ " Address: " + p.getAddress());
			i++;
		}
	}
}
