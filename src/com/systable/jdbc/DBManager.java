package com.systable.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
	private static Connection connection = null;
	
	public static Connection getConnection () throws Exception {
		try {
			// Chargement du driver jdbc mysql
			Class.forName ("com.mysql.cj.jdbc.Driver");
			
			String sgbd = "mysql";
			String db = "systable";
			String user = "root";
			String password = "";
			
			// Ouverture de la connexion
			connection = DriverManager.getConnection ("jdbc:" + sgbd + "://localhost/" + db, user, password);
			return connection;
		} catch (ClassNotFoundException e ) {
			throw new Exception ("Driver Class not found : '" + e .getMessage () + "' ");
		} catch (SQLException e ) {
			throw new Exception ("Error : Unable to open connection with database !");
		}
	}
}
