package com.revature.DAO;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	User registerUser(User user);
	boolean updateUser(User user);
	User getUser(User user);
	List<User> getAllUsers();
	int calculateRank(int highScore);
	Object viewLeaderboard();
	User updateScore(User user);

	
}