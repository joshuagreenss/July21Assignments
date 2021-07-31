package com.smoothstack.utopia.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.smoothstack.utopia.utils.ConnectionUtil;

public class ConnectionTesting {
	@Test
	public void connectionTest() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			assertNotNull(conn);
		} catch (ClassNotFoundException | SQLException e) {
			fail();
		}
	}
}
