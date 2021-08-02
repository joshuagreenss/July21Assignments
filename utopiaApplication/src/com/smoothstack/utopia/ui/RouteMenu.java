package com.smoothstack.utopia.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.smoothstack.utopia.dao.RouteDAO;
import com.smoothstack.utopia.domain.Route;

public class RouteMenu implements ObjectMenu {
	private Scanner s;

	public RouteMenu(Scanner s) {
		this.s = s;
	}

	@Override
	public void add(Connection conn) throws ClassNotFoundException, SQLException {
		Route r = new Route();
		String newVal;
		System.out.println("Enter new value");

		System.out.println("Origin Code: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			r.setOrig(newVal);
		}

		System.out.println("Destination Code: ");
		newVal = s.next();
		if (newVal.length() > 0) {
			r.setDest(newVal);
		}
		RouteDAO dao = new RouteDAO(conn);
		dao.insert(r);
	}

	@Override
	public void update(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Route";
		String newVal;
		int i = 1;
		int input = 0;
		RouteDAO dao = new RouteDAO(conn);
		List<Route> rs = dao.query(sql, null);
		for (Route r : rs) {
			System.out.println(i + ") Origin: " + r.getOrig() + " Destination: " + r.getDest());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid Selection");
		}
		if (input > 0 && input <= rs.size()) {
			System.out.println("Enter new value or N/A");

			System.out.println("Origin: ");
			newVal = s.next();
			if (!newVal.equals("N/A")) {
				rs.get(input - 1).setOrig(newVal);
			}

			System.out.println("Destination: ");
			newVal = s.next();
			if (!newVal.equals("N/A")) {
				rs.get(input - 1).setDest(newVal);
			}

			dao.update(rs.get(input - 1));
		}
	}

	@Override
	public void delete(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Route";
		int i = 1;
		int input = 0;
		RouteDAO dao = new RouteDAO(conn);
		List<Route> rs = dao.query(sql, null);
		for (Route r : rs) {
			System.out.println(i + ") Origin: " + r.getOrig() + " Destination: " + r.getDest());
			i++;
		}
		try {
			input = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid Selection");
		}
		if (input > 0 && input <= rs.size()) {
			dao.delete(rs.get(input - 1));
		}
	}

	@Override
	public void read(Connection conn) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Route";
		int i = 1;
		RouteDAO dao = new RouteDAO(conn);
		List<Route> rs = dao.query(sql, null);
		for (Route r : rs) {
			System.out.println(i + ") Origin: " + r.getOrig() + " Dest: " + r.getDest());
			i++;
		}
	}
}
