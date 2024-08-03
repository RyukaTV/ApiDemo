package com.ryuk.ApiDemo.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
	private static final String url= "jdbc:mysql://localhost:3306/ApiDemo?useSSL=false";
	private static final String username= "your-database-username";
	private static final String psw= "your-database-password";
	private static Connection connex;
	
	public static Connection getConnection() {
		try {
			if (connex == null) {
				connex= DriverManager.getConnection(url, username, psw);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connex;
	}
}
