package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.Payment;

public class PaymentDAO extends DAO<Payment> {

	public PaymentDAO(Connection conn) {
		super(conn);
	}

	public void insert(Payment payment) throws ClassNotFoundException, SQLException {
		this.commit("INSERT INTO booking_payment VALUES (?, ?, ?)",
				new Object[] { payment.getId(), payment.getSid(), payment.getRefund() });
	}

	public void update(Payment payment) throws ClassNotFoundException, SQLException {
		this.commit("UPDATE booking_payment SET stripe_id = ?, refunded = ? WHERE booking_id = ?",
				new Object[] { payment.getSid(), payment.getRefund(), payment.getId() });
	}

	public void delete(Payment payment) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM booking_payment WHERE booking_id = ?", new Object[] { payment.getId() });
	}

	@Override
	protected List<Payment> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if (rs != null) {
			List<Payment> results = new ArrayList<>();
			Payment item = new Payment();
			while (rs.next()) {
				item.setId(rs.getInt(1));
				item.setSid(rs.getString(2));
				item.setRefund(rs.getInt(3));
				results.add(item);
			}
			return results;
		}
		return null;
	}

}
