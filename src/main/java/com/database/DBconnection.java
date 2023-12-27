package com.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

	private static Connection conn;
	public static Connection getConn() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "MYSQL#976#aadil");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

}
