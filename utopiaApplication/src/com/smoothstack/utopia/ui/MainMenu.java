package com.smoothstack.utopia.ui;

import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		menu(s);
		s.close();
	}

	public static void menu(Scanner s) {
		int input = 0;
		do {
			System.out.println("Welcome to Utopia! What kind of user are you?");
			System.out.println("1) Employee/Agent");
			System.out.println("2) Traveler");
			System.out.println("3) Administrator");
			System.out.println("4) Quit");
			try {
				input = s.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid selection");
				input = 0;
			}
			switch (input) {
			case (1):
				AgentMenu.mainMenu(s);
				break;
			case (2):
				TravelerMenu.mainMenu(s);
				break;
			case (3):
				AdminMenu.mainMenu(s);
				break;
			case (4):
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("I'm sorry, that's not a valid input");
			}
		} while (input != 4);
	}
}
