package com.jdbcDatatoPdf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class ConnectionToDriver {

	void ConnectionToDrivertodriver(BufferedReader buf, CreateDatabase a, ConnectionToDriver as, Connection con,
			Statement stmt) throws Exception {
		System.out.println("*  DO YOU WANT TO CREATE NEW DATA BASE :");
		System.out.println("   Y/N");
		String CHAR = buf.readLine();

		if (CHAR.equals("y")) {
			a.CreatDataBase(con, buf, a, stmt);
		} else {
			System.out.println("*  ENTER ALREADY EXIST DATABASE NAME :");
			String database = buf.readLine();
			a.selectdatabase(con, buf, a, stmt, database);
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		CreateDatabase a = new CreateDatabase();
		ConnectionToDriver as = new ConnectionToDriver();

		try {
			System.out.println("*  LOADING......");
			System.out.println("*  CONNECTION STARTING ......");

			String ad1 = "jdbc:mysql://localhost:3306/";
			String ad2 = "root";
			String ad3 = "MANISH80";
			System.out.println("*  CONNECTION SUCCESFULL...");

			Connection con = DriverManager.getConnection(ad1, ad2, ad3);
			Statement stmt = con.createStatement();
			as.ConnectionToDrivertodriver(buf, a, as, con, stmt);
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

}
