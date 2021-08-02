package com.smoothstack.utopia.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.smoothstack.utopia.utils.ConnectionUtil;

public interface ObjectMenu {
	public default void mainMenu(Scanner s) {

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
			}
			try (Connection conn = ConnectionUtil.getConnection()) {
				try {
					switch (input) {
					case (1):
						add(conn);
						break;
					case (2):
						update(conn);
						break;
					case (3):
						delete(conn);
						break;
					case (4):
						read(conn);
						break;
					case (5):
						break;
					default:
						System.out.println("Invalid input");
					}
					conn.commit();
				} catch (SQLException | ClassNotFoundException e) {

				} finally {
					conn.close();
				}
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (input != 5);
	}

	public void add(Connection conn)throws ClassNotFoundException, SQLException;

	public void update(Connection conn)throws ClassNotFoundException, SQLException;

	public void delete(Connection conn)throws ClassNotFoundException, SQLException;

	public void read(Connection conn)throws ClassNotFoundException, SQLException;
}
