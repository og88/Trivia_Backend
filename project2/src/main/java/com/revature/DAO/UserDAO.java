package com.revature.DAO;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import com.revature.customExceptions.UserNotFoundException;
import com.revature.models.User;

public interface UserDAO {
	
	User registerUser(User user) throws FileNotFoundException;
	boolean updateUser(User user) throws FileNotFoundException;
	User getUser(User user) throws SQLException, UserNotFoundException;
	List<User> getAllUsers();
	int calculateRank(int highScore) throws FileNotFoundException, SQLException;
	Object viewLeaderboard() throws FileNotFoundException, SQLException;
	User updateScore(User user) throws SQLException, UserNotFoundException;

	
}