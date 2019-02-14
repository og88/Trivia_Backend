package com.revature.DAO;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	boolean registerUser(User user) throws FileNotFoundException;
	boolean updateUser(String username, User user) throws FileNotFoundException;
	User getUser(String username) throws SQLException;
	List<User> getAllUsers();
	int calculateRank(int highScore);
	void viewLeaderboard();
	
}