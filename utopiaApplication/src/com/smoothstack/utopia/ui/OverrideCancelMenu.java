package com.smoothstack.utopia.ui;

import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.domain.Booking;
import com.smoothstack.utopia.services.BookingServices;

public class OverrideCancelMenu {
	public static void mainMenu(Scanner s) {
		BookingServices service = new BookingServices();
		int input = 0;
		int i = 0;
		List<Booking> bs;
		i = 1;
		do {
			bs = service.readAll();
			i = 1;
			for (Booking b : bs) {
				System.out.println(
						i + ") id: " + b.getId() + " Active: " + b.getActive() + " Confirmation Code: " + b.getCode());
				i++;
			}
			System.out.println(i + ") Return to Previous Menu");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input");
				input = 0;
			}
			if (input > 0 && input < bs.size() + 1) {
				service.update(bs.get(input - 1).getId(), 1, bs.get(input - 1).getCode());
			}
		} while (input != bs.size() + 1);
		service.commit();
		service.close();
	}
}
