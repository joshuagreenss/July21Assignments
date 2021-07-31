/**
 * 
 */
package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author joshu
 *
 */
public abstract class DAO<T> {
	public static Connection conn = null;
	
	public DAO(Connection conn) {
		this.conn = conn;
	}
	
	public void query(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 1;
		if(vals != null) {
			for(Object o : vals) {
				pstmt.setObject(i, o);
				i++;
			}
		}
		pstmt.executeUpdate();
	}
}
