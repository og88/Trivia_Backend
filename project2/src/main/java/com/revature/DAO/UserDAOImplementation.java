package com.revature.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.utils.JDBCconnectionUtil;

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
		User newUser = user;
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "{call INSERT_USER (?,?,?)}";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1, newUser.getUsername());
			ps.setString(2, newUser.getPassword());
			ps.setString(3, newUser.getEmail());
			
			if(ps.executeUpdate() > 0) {
				conn.close();
				return true;
			} 
			else {
				conn.close();
				throw new SQLException();
			}
		}
		catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}

	@Override  
	public boolean updateUser(String username, User user) {
		//What is the username string for?  old username to reference row in the DB? 
		//Or new username to be inserted? How is this username being saved and passed?
		User updateUser = user;
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			//Need name of procedure dor updating user info if one exists.
			//This method is assuming username string is old username for referencing row in DB.
			//Can change this method as needed.
			String sql = "UPDATE TriviaUsers SET USERNAME = (?), PASS = (?), EMAIL = (?) WHERE USERNAME = (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, updateUser.getUsername());
			ps.setString(2, updateUser.getPassword());
			ps.setString(3, updateUser.getEmail());
			ps.setString(4, username);
			
				if(ps.executeUpdate() > 0) {
					conn.close();
					return true;
				} 
				else {
					conn.close();
					throw new SQLException();
				}
		}
		catch (SQLException e) {
			e.getStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateScore(String username, int highScore) throws SQLException {
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "UPDATE TriviaUsers SET HIGH_SCORE = (?) WHERE USERNAME = (?)";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setInt(1, highScore);
			ps.setString(2, username);
			
				if(ps.executeUpdate() > 0) {
					conn.close();
					return true;
				} 
				else {
					conn.close();
					throw new SQLException();
				}
		}
	}

	@Override
	public User getUser(String username) throws SQLException {
		//Is there a method to validate users? (Username/Password)
		//Or are we just getting them by username?
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "SELECT USERNAME, EXPERIENCE, HIGH_SCORE FROM TriviaUsers WHERE USERNAME = ?";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setString(1, username);
			
			ResultSet results = ps.executeQuery();
			while (results.next()) {
				return new User(
					results.getString("USERNAME"), 
					results.getInt("EXPERIENCE"),
					results.getInt("HIGH_SCORE"));
			}
			conn.close();
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "SELECT USERNAME, EXPERIENCE, HIGH_SCORE FROM TriviaUsers";
			PreparedStatement ps = conn.prepareCall(sql);
			
			List<User> allEmployees = new ArrayList<>();
			
			ResultSet results = ps.executeQuery();
			
			while (results.next()) {
				allEmployees.add(new User(
					results.getString("USERNAME"), 
					results.getInt("EXPERIENCE"),
					results.getInt("HIGH_SCORE")));	
			}
				conn.close();
				return allEmployees;
				
		}catch(SQLException e) {
			e.getStackTrace();
		}
		return null;
	}

	@Override
	public int calculateRank(int highScore) {
		//Need to know how rank is calculated
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void viewLeaderboard() {
		// TODO Auto-generated method stub
		
	}

}
