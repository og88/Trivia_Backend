package com.revature.DAO;

<<<<<<< HEAD
import java.util.List;

=======
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import com.revature.customExceptions.UserNotFoundException;
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
import com.revature.models.User;

public interface UserDAO {
	
<<<<<<< HEAD
	User registerUser(User user);
	boolean updateUser(User user);
	User getUser(User user);
	List<User> getAllUsers();
	int calculateRank(int highScore);
	Object viewLeaderboard();
	User updateScore(User user);
=======
	User registerUser(User user) throws FileNotFoundException;
	boolean updateUser(User user) throws FileNotFoundException;
	User getUser(User user) throws SQLException, UserNotFoundException;
	List<User> getAllUsers();
	int calculateRank(int highScore) throws FileNotFoundException, SQLException;
	Object viewLeaderboard() throws FileNotFoundException, SQLException;
	User updateScore(User user) throws SQLException, UserNotFoundException;
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241

	
}