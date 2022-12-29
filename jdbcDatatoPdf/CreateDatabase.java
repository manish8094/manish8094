package com.jdbcDatatoPdf;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDatabase {

	void CreatDataBase(Connection con, BufferedReader buf, CreateDatabase a, Statement stmt) throws Exception {

		System.out.println("*  ENTER YOUR DATABASE NAME ");
		String database = buf.readLine();
		String s = "CREATE database " + database;
		stmt.execute(s);
		System.out.println("*  SUCCESFULY ADD YOUR DATABASE  " + database);
		System.out.println("*  CREATE TABLES   Y/N      ");
		String yn = buf.readLine();
		if (yn.equals("y")) {
			a.selectdatabase(con, buf, a, stmt, database);
		} else {
			System.out.println("thanks");
		}

	}

	void selectdatabase(Connection con, BufferedReader buf, CreateDatabase a, Statement stmt, String database)
			throws Exception {

		String add = "jdbc:mysql://localhost:3306/" + database + "";
		String add2 = "root";
		String add3 = "MANISH80";

		con = DriverManager.getConnection(add, add2, add3);
		System.out.println("*  connect succesfully to databse..........");
		InsertAndDelete in = new InsertAndDelete();
		System.out.println("*  DO YOU WANT TO CREATE TABLE  y/n ");
		String tab = buf.readLine();
		if (tab.equals("y")) {
			a.CreateTable(con, buf, a, stmt, database, in);
		} else {
			System.out.println("*  DO YOU WANT TO DELETE OPERATION 'Y'");
			System.out.println("*  DO YOU WANT TO INSERT OPERATION 'N'");
			String Y= buf.readLine();
			if(Y.startsWith("y")){
			in.Delete(stmt, buf);	
			}
			else if(Y.startsWith("n")) {
		 
			in.Insert(con, buf, stmt, database, null, in,a);
		}
	}
	}
	void CreateTable(Connection con, BufferedReader buf, CreateDatabase a, Statement stmt, String database,
			InsertAndDelete in) throws Exception {

		System.out.println("*  ENTER YOUR TABLE ");
		String table = buf.readLine();
		stmt.execute("use " + database + "");
		System.out.println(" 1.ID, 2.NAME, 3.surname, 4.mobile number, 5.address");

		String id = buf.readLine();
		String name = buf.readLine();
		String surname = buf.readLine();
		String mobile = buf.readLine();
		String address = buf.readLine();

		String sql = "CREATE TABLE " + table + "(" + id + " int(21)," + name + " varchar(67)," + surname
				+ " varchar(45)," + mobile + " int(12)," + address + " varchar(45))";
		stmt.execute(sql);
		System.out.println("*  SUCCESSFULLY CREATE THE TABLE");

		in.Insert(con, buf, stmt, database, table, in,a);
	}
}
