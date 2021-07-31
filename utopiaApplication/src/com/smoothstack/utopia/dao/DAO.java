/**
 * 
 */
package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author joshu
 *
 */
public abstract class DAO<T> {
	public static Connection conn = null;

	public DAO(Connection conn) {
		this.conn = conn;
	}

	protected void commit(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 1;
		if (vals != null) {
			for (Object o : vals) {
				pstmt.setObject(i, o);
				i++;
			}
		}
		pstmt.executeUpdate();
	}

	protected Integer commitGet(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 1;
		if (vals != null) {
			for (Object o : vals) {
				pstmt.setObject(i, o);
				i++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		while (rs.next()) {
			return rs.getInt(1);
		}
		return null;
	}

	protected List<T> query(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 1;
		if (vals != null) {
			for (Object o : vals) {
				pstmt.setObject(i, 0);
				;
				i++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return listData(rs);
	}

	abstract protected List<T> listData(ResultSet rs) throws ClassNotFoundException, SQLException;
}
