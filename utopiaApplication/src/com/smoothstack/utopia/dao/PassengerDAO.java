package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.Passenger;

public class PassengerDAO extends DAO<Passenger> {

	public PassengerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void insert(Passenger passenger) throws ClassNotFoundException, SQLException {
		if (passenger.getId() != null) {
			this.commit("INSERT INTO Passenger VALUES (?, ?, ?, ?, ?, ?, ?)",
					new Object[] { passenger.getId(), passenger.getBid(), passenger.getgName(), passenger.getfName(),
							passenger.getDob(), passenger.getGender(), passenger.getAddress() });
		} else {
			this.commit(
					"INSERT INTO Passenger (booking_id, given_name, family_name, dob, gender, address) VALUES (?,?,?,?,?,?)",
					new Object[] { passenger.getBid(), passenger.getgName(), passenger.getfName(), passenger.getDob(),
							passenger.getGender(), passenger.getAddress() });
		}
	}

	public void update(Passenger passenger) throws ClassNotFoundException, SQLException {
		this.commit(
				"UPDATE Passenger SET booking_id = ?, given_name = ?, family_name = ?, dob = ?, gender= ?, address = ? WHERE id = ?",
				new Object[] { passenger.getBid(), passenger.getgName(), passenger.getfName(), passenger.getDob(),
						passenger.getGender(), passenger.getAddress(), passenger.getId() });
	}

	public void delete(Passenger passenger) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM Passenger WHERE id = ?", new Object[] { passenger.getId() });
	}

	@Override
	protected List<Passenger> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if (rs != null) {
			List<Passenger> results = new ArrayList<>();
			Passenger item;
			while (rs.next()) {
				item = new Passenger();
				item.setId(rs.getInt(1));
				item.setBid(rs.getInt(2));
				item.setgName(rs.getString(3));
				item.setfName(rs.getString(4));
				item.setDob(rs.getDate(5).toLocalDate());
				item.setGender(rs.getString(6));
				item.setAddress(rs.getString(7));
				results.add(item);
			}
			return results;
		}
		return null;
	}

}
