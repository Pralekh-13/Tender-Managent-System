package com.tendersystem.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
 private static String url = "jdbc:mysql://localhost:3306/ tender_management_system";
	 
	 private static String user = "root";
	 
	 private static String password = "root";
	 
	 
	 private static Connection con;
	 
	 
	 
	 
	 static{
		  
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 try {
			 con =DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	 }
	 
	 
	 public static Connection getConnection() {
		  	
		 return con;
		 
	 }
}
