package com.kevin_leader.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class to grab a database connection for the app
 * @author Kevin Leader
 */
public class JDBCConnection {
		
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		/*
		 * To establish a connection we need 3 credentials:
		 * url (endpoint), username, password
		 */
		if (conn == null) {
			
			// Establish Connection
			String endpoint = "revature-projects.c3w8xexsymza.us-east-2.rds.amazonaws.com";
			
			//URL Format (postgres JDBC):
			//jdbc:postgresql://[endpoint]/[database]
			String url = "jdbc:postgresql://" + endpoint + "/postgres";
			String username = "kevin";
			String password = "password"; // Don't do this commercially
			
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
