package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class JDBCconnectionUtil {

	private static final Logger log = LogManager.getLogger(ConnectionUtil.class);
	
	//JDBC  - java database connectivity
	public  static Connection getConnection() throws SQLException {
		try {
			Properties props = new Properties();
			
			try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbConnInfo.properties"));
			} catch (IOException e) {
				log.fatal("Failed to obtain JDBC Connection");
				throw new RuntimeException(e);
			}
			//FileInputStream in = new FileInputStream("C:\\SpringToolSuite\\project2\\src\\main\\resources\\dbConnInfo.properties");
			/*InputStream in = new FileInputStream("dbConnInfo.properties");
			props.load(in);
			in.close();*/
			
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
		catch (ClassNotFoundException | SQLException e) {
			e.getStackTrace();
			e.printStackTrace();		
		}
		return null;	
	}
}
