package com.revature.services;

<<<<<<< HEAD
=======
import java.io.FileNotFoundException;
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.DAO.UserDAOImplementation;
<<<<<<< HEAD
=======
import com.revature.customExceptions.UserNotFoundException;
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
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
<<<<<<< HEAD
		return UserDAOImplementation.getUserDAO().updateScore(user);
		
	}
	
	public User registerUser(User user) {
		return UserDAOImplementation.getUserDAO().registerUser(user);
	}
	
	public boolean updateUser(User user) {
=======
		try {
			return UserDAOImplementation.getUserDAO().updateScore(user);
		} catch (SQLException e) {
//			e.printStackTrace();
		} catch (UserNotFoundException e) {
			//e.printStackTrace();
		}
		return new User();
		
	}
	
	public User registerUser(User user) throws FileNotFoundException {
		return UserDAOImplementation.getUserDAO().registerUser(user);
	}
	
	public boolean updateUser(User user) throws FileNotFoundException {
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
		return UserDAOImplementation.getUserDAO().updateUser(user);
	}
	
	
	public User getUser(User user) throws SQLException {
<<<<<<< HEAD
		return UserDAOImplementation.getUserDAO().getUser(user);
=======
		try {
			return UserDAOImplementation.getUserDAO().getUser(user);
		} catch (UserNotFoundException e) {
			log.error("User not found");
		}
		return null;
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
	}
	
	public List<User> getAllUsers() {
		return UserDAOImplementation.getUserDAO().getAllUsers();
	}
	
	public int calculateRank(int highScore) {
<<<<<<< HEAD
		return UserDAOImplementation.getUserDAO().calculateRank(highScore);
	}
	
	public Object viewLeaderboard() {
		return UserDAOImplementation.getUserDAO().viewLeaderboard();
=======
		try {
			return UserDAOImplementation.getUserDAO().calculateRank(highScore);
		} catch (FileNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Object viewLeaderboard() throws FileNotFoundException, SQLException {
		return UserDAOImplementation.getUserDAO().viewLeaderboard();

>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
	}
	
}
