package com.jdbcDatatoPdf;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertAndDelete {

	void Insert(Connection con, BufferedReader buf, Statement stmt, String database, String table, InsertAndDelete in,
			CreateDatabase a) throws Exception {
		System.out.println("*  DO YOU HAVE A TABLE Y/N");
		String tab = buf.readLine();
		if (tab.startsWith("y")) {
			System.out.println("*  ENTER YOUR EXIST TABLE NAME");
			String table1 = buf.readLine();
			table = table1;
		} else {
			System.out.println("*  PLEASE CREATE THE TABLE ");
			a.CreateTable(con, buf, a, stmt, database, in);
		}

		stmt.execute("use " + database + "");
		System.out.println("*  FILL YOUR TABLE ");
		PreparedStatement ps = con.prepareStatement("insert into " + table + " values(?,?,?,?,?)");

		do {
			System.out.println("*  ENTER COLUMN 1/ID VALUE :");
			int id = Integer.parseInt(buf.readLine());
			System.out.println("*  ENTER COLUMN 2/NAME VALUE :");
			String name = buf.readLine();
			System.out.println("*  ENTER COLUMN 3/SURNAME VALUE :");
			String surname = buf.readLine();
			System.out.println("*  ENTER COLUMN 3/MOBILE VALUE :");
			int mobile = Integer.parseInt(buf.readLine());
			System.out.println("*  ENTER COLUMN 3/ADRESS VALUE :");
			String address = buf.readLine();

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, surname);
			ps.setInt(4, mobile);
			ps.setString(5, address);
			int i = ps.executeUpdate();

			System.out.println("*  ADD MORE Y/N");
			String yn = buf.readLine();

			if (yn.startsWith("n")) {
				System.out.println("*  DO YOU WANT TO DELETE SOMETHING");
				in.Delete(stmt, buf);
				break;

			}
		} while (true);

	}

	void Delete(Statement stmt, BufferedReader buf) throws Exception {
		do {
			System.out.println("*  ENTER YOUR DATA BASE TO DELETE OPERATION");
			System.out.println("*  CHOOSE A OPTION 1.DELETE DATABASE , 2.DELETE TABLE 3.DELETE COLUMN ");
			int INPUT = Integer.parseInt(buf.readLine());
			switch (INPUT) {
			case 1: {
				do {
					System.out.println("*  ENTER YOUR DATA BASE TO DELETE");
					String database = buf.readLine();
					String database1 = "use " + database + "";
					stmt.execute(database1);
					String delete = "drop database " + database + "";
					stmt.execute(delete);
					System.out.println("*  Succesfull delete");
					System.out.println("*  DO YOU WANT TO DELETE MORE Y/N");
					String Y = buf.readLine();
					if (Y.startsWith("n")) {
						break;
					}
				} while (true);
			}
			case 2: {
				do {
					System.out.println("*  ENTER YOUR DATABASE TO PERFOM DELETE TABLE OPERATION");
					String database = buf.readLine();
					String usedatabase = "use " + database + "";
					stmt.execute(usedatabase);
					System.out.println("*  ENTER YOUR TABLE TO DELETE ");
					String table = buf.readLine();
					String table1 = "drop table " + table + "";
					stmt.execute(table1);
					System.out.println("*  Succesfull delete");
					System.out.println("*  DO YOU WANT TO DELETE MORE Y/N");
					String Y = buf.readLine();
					if (Y.startsWith("n")) {
						break;
					}
				} while (true);
			}
			case 3: {
				do {
					System.out.println("*  ENTER YOUR DATABASE TO PERFORM TABLE DELETE OPERATION");
					String database = buf.readLine();
					String usedatabase = "use " + database + "";
					stmt.execute(usedatabase);
					System.out.println("*  ENTER YOUR TABLE TO DELETE ");
					String table = buf.readLine();
					System.out.println("*  ENTER YOUR COLUMN TO DELETE ");
					String column = buf.readLine();
					String table1 = "alter table "+table+" drop "+column+"";
					stmt.execute(table1);
					System.out.println("*  Succesfull delete");
					System.out.println("*  DO YOU WANT TO DELETE MORE COLUMN Y/N");
					String Y = buf.readLine();
					if (Y.startsWith("n")) {
						break;
					}
				} while (true);
				
			}
				System.out.println("*  DO YOU WANT TO DELETE MORE Y/N");
				String Y = buf.readLine();
				if (Y.startsWith("n")) {
					break;
				}
			}
		} while (true);
	}
}
