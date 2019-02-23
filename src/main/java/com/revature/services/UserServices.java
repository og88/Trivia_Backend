package com.revature.services;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.DAO.UserDAOImplementation;
import com.revature.customExceptions.UserNotFoundException;
import com.revature.models.User;

public class UserServices {
	
	final static Logger log = Logger.getLogger(UserServices.class);


	private static UserServices userServices;
	private UserServices() {
	}

	public static UserServices getUserServices() {
		if (userServices == null) {
			userServices = new UserServices();
		}
		return userServices;
	}
	
/*	User authenticateUser(String username, String password) throws FileNotFoundException, SQLException, UserNotFoundException {
		return UserDAOImplementation.getUserDAO().authenticateUser(username, password);
	}*/
	
	public User registerUser(User user) throws FileNotFoundException {
		return UserDAOImplementation.getUserDAO().registerUser(user);
	}
	
	public boolean updateUser(String username, User user) throws FileNotFoundException {
		return UserDAOImplementation.getUserDAO().updateUser(username, user);
	}
	
	
	public User getUser(User user) throws SQLException {
		try {
			return UserDAOImplementation.getUserDAO().getUser(user);
		} catch (UserNotFoundException e) {
			log.error("User not found");
		}
		return null;
	}
	
	public List<User> getAllUsers() {
		return UserDAOImplementation.getUserDAO().getAllUsers();
	}
	
	public int calculateRank(int highScore) {
		try {
			return UserDAOImplementation.getUserDAO().calculateRank(highScore);
		} catch (FileNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public Object viewLeaderboard() throws FileNotFoundException, SQLException {
		return UserDAOImplementation.getUserDAO().viewLeaderboard();

	}
	
}
