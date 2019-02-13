package com.revature.DAO;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	boolean registerUser(User user);
	boolean updateUser(String username, User user);
	boolean updateScore(String username, int highScore) throws SQLException;
	User getUser(String username) throws SQLException;
	List<User> getAllUsers();
	int calculateRank(int highScore);
	void viewLeaderboard();
	
}