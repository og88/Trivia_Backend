package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.DAO.UserDAOImplementation;
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
	
	public User updateScore(User user) {
		return UserDAOImplementation.getUserDAO().updateScore(user);
		
	}
	
	public User registerUser(User user) {
		return UserDAOImplementation.getUserDAO().registerUser(user);
	}
	
	public boolean updateUser(User user) {
		return UserDAOImplementation.getUserDAO().updateUser(user);
	}
	
	
	public User getUser(User user) throws SQLException {
		return UserDAOImplementation.getUserDAO().getUser(user);
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
