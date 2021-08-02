package com.smoothstack.utopia.ui;

import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		menu(s);
		s.close();
	}
	public static void menu(Scanner s) {
		int choice = 0;
		while (choice != 4) {
			System.out.println("Welcome to Utopia! What kind of user are you?");
			System.out.println("1) Employee/Agent");
			System.out.println("2) Administrator");
			System.out.println("3) Traveler");
			System.out.println("4) Quit");
			choice = s.nextInt();
			switch (choice) {
			case (1):
				AgentMenu.mainMenu(s);
				break;
			case (2):
				System.out.println("Not yet implemented");
				break;
			case (3):
				System.out.println("Not yet implemented");
				break;
			case (4):
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("I'm sorry, that's not a valid input");
			}
		}
	}
}
