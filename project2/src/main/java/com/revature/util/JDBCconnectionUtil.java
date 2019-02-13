package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCconnectionUtil {

	
	//JDBC  - java database connectivity
	public  static Connection getConnection() throws SQLException, FileNotFoundException {
		try {
			Properties props = new Properties();
			FileInputStream in = new FileInputStream("C:\\SpringToolSuite\\project2\\src\\main\\resources\\dbConnInfo.properties");
			props.load(in);
			in.close();
			
			//Need Database info for Database we are using
			//Properties file is in src/main/resources
			
			String driver = props.getProperty("jdbc.driver");
				if(driver!=null) {
					Class.forName(driver);	
				}
					String url = props.getProperty("jdbc.url");
					String username = props.getProperty("jdbc.username");
					String password = props.getProperty("jdbc.password");
			return DriverManager.getConnection(url, username, password);
			
			
		}
		catch ( IOException | ClassNotFoundException | SQLException e) {
			e.getStackTrace();
			e.printStackTrace();		
		}
		return null;	
	}
}
