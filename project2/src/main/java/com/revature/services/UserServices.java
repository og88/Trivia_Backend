package com.revature.services;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import com.revature.DAO.UserDAOImplementation;
import com.revature.customExceptions.UserNotFoundException;
import com.revature.models.User;

public class UserServices {

	private static UserServices userServices;
	private UserServices() {
	}

	public static UserServices getUserServices() {
		if (userServices == null) {
			userServices = new UserServices();
		}
		return userServices;
	}
	
	User authenticateUser(String username, String password) throws FileNotFoundException, SQLException, UserNotFoundException {
		return UserDAOImplementation.getUserDAO().authenticateUser(username, password);
	}
	
	boolean registerUser(User user) throws FileNotFoundException {
		return UserDAOImplementation.getUserDAO().registerUser(user);
	}
	
	boolean updateUser(String username, User user) throws FileNotFoundException {
		return UserDAOImplementation.getUserDAO().updateUser(username, user);
	}
	
	
	User getUser(String username) throws SQLException {
		return UserDAOImplementation.getUserDAO().getUser(username);
	}
	
	List<User> getAllUsers() {
		return UserDAOImplementation.getUserDAO().getAllUsers();
	}
	
	int calculateRank(int highScore) {
		return UserDAOImplementation.getUserDAO().calculateRank(highScore);
	}
	
	void viewLeaderboard() throws FileNotFoundException, SQLException {
		UserDAOImplementation.getUserDAO().viewLeaderboard();
	}
	
}
