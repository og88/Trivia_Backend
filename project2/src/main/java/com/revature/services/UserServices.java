package com.revature.services;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import com.revature.DAO.UserDAOImplementation;
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
	
	public boolean registerUser(User user) throws FileNotFoundException {
		return UserDAOImplementation.getUserDAO().registerUser(user);
	}
	
	public boolean updateUser(String username, User user) throws FileNotFoundException {
		return UserDAOImplementation.getUserDAO().updateUser(username, user);
	}
	
	
	public User getUser(String username) throws SQLException {
		return UserDAOImplementation.getUserDAO().getUser(username);
	}
	
	public List<User> getAllUsers() {
		return UserDAOImplementation.getUserDAO().getAllUsers();
	}
	
	public int calculateRank(int highScore) {
		return UserDAOImplementation.getUserDAO().calculateRank(highScore);
	}
	
	public Object viewLeaderboard() {
		return UserDAOImplementation.getUserDAO().viewLeaderboard();
	}
	
}
