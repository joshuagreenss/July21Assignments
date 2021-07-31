/**
 * 
 */
package com.smoothstack.utopia.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author joshu
 *
 */
public class ConnectionUtil {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(ConnectionUtil.getProperty("driver"));
		Connection conn = DriverManager.getConnection(ConnectionUtil.getProperty("url"), ConnectionUtil.getProperty("user"),
				ConnectionUtil.getProperty("password"));
		conn.setAutoCommit(false);
		return conn;
	}
	public static String getProperty(String name) {
		try(InputStream is = new FileInputStream("resources/utopia.properties")){
			Properties prop = new Properties();
			prop.load(is);
			return prop.getProperty(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
