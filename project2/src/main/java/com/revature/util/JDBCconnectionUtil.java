<<<<<<< HEAD
<<<<<<< HEAD
package com.revature.util;

import java.io.FileNotFoundException;
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
	public  static Connection getConnection() {
		try {
			Properties props = new Properties();
			
			try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbConnInfo.properties"));
			} catch (IOException e) {
				log.fatal("Failed to obtain JDBC Connection");
				throw new RuntimeException(e);
			}
			
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
=======
=======
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
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
<<<<<<< HEAD
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
=======
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
