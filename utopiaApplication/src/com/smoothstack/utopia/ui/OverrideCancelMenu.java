package com.smoothstack.utopia.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.dao.BookingDAO;
import com.smoothstack.utopia.domain.Booking;
import com.smoothstack.utopia.utils.ConnectionUtil;

public class OverrideCancelMenu {
	public static void mainMenu(Scanner s) {
		int choice = 0;
		int i = 0;
		String sql = "SELECT * FROM Booking WHERE is_active = 0";
		String ovr = "UPDATE Booking SET is_active = 1 WHERE id = ?";
		List<Booking> bs;
		try (Connection conn = ConnectionUtil.getConnection()) {
			try {
				i = 1;
				BookingDAO dao = new BookingDAO(conn);
				do {
					bs = dao.query(sql,null);
					for(Booking b : bs) {
						System.out.println(i+") id: "+b.getId()+" Active: "+b.getActive()+" Confirmation Code: "+b.getCode());
						i++;
					}
					try {
						choice = s.nextInt();
					}catch(Exception e){
						System.out.println("Invalid input");
					}
					if(choice > 0 && choice < bs.size()+1) {
						dao.query(ovr, new Object[]{bs.get(choice-1).getId()});
					}
				} while (choice != bs.size() + 1);
				conn.commit();
			} catch (SQLException | ClassNotFoundException e) {

			} finally {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
