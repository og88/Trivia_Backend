package com.revature.DAO;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;

public class UserDAOImplementation implements UserDAO{

	private static UserDAOImplementation userDAO;
	final static Logger log = Logger.getLogger(UserDAOImplementation.class);
	private UserDAOImplementation() {
	}
	
	public static UserDAOImplementation getUserDAO() {
		if (userDAO == null) {
			userDAO = new UserDAOImplementation();
		}
		return userDAO;
	}
	
	
	@Override
	public boolean registerUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(String username, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateScore(String username, int highScore) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculateRank(int highScore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void viewLeaderboard() {
		// TODO Auto-generated method stub
		
	}

}
