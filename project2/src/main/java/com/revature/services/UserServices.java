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
	
	
	public User getUser(User user) throws SQLException {
		return UserDAOImplementation.getUserDAO().getUser(user);
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
	
	public Object viewLeaderboard() {
		return UserDAOImplementation.getUserDAO().viewLeaderboard();
	}
	
}
