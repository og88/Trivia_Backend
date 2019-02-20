package com.revature.DAO;

import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.util.JDBCconnectionUtil;

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
	public boolean registerUser(User user) throws FileNotFoundException {
		User newUser = user;
		try ( Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "call INSERT_USER (?,?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1, newUser.getUsername());
			ps.setString(2, newUser.getPassword());
			ps.setString(3, newUser.getEmail());
			ps.registerOutParameter(4, Types.NUMERIC);
			ps.executeUpdate();
			int result = ps.getInt(4);
			if(result > 0) {
				log.info("Registration Success");
				conn.close();
				return true;
			} 
			else {
				log.info("Registration Failure");
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
	public boolean updateUser(String username, User user) throws FileNotFoundException {
		User updateUser = user;
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			//need name of procedure if there is one
			String sql = "UPDATE TriviaUsers SET USERNAME = (?), PASS = (?), EMAIL = (?), HIGH_SCORE = (?) WHERE USERNAME = (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, updateUser.getUsername());
			ps.setString(2, updateUser.getPassword());
			ps.setString(3, updateUser.getEmail());
			ps.setInt(4, updateUser.getHighScore());
			ps.setString(5, username);
			
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
	public User getUser(User user) throws SQLException {
		//Is there a method to validate users? (Username/Password)
		//Or are we just getting them by username?
		log.info("Login attempt");
		System.out.println(user.toString());
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "SELECT USERNAME, EXPERIENCE, HIGH_SCORE, EMAIL FROM TriviaUsers WHERE USERNAME = ? AND PASS = GET_USER_HASH(?,?)";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			
			
			ResultSet results = ps.executeQuery();
			while (results.next()) {
				System.out.println("found");
				return new User(
					results.getString("USERNAME"), 
					results.getInt("EXPERIENCE"),
					results.getInt("HIGH_SCORE"),
					results.getString("EMAIL"));
			}
			conn.close();
		}
		return new User();
	}

	@Override
	public List<User> getAllUsers() {
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "SELECT USERNAME, EXPERIENCE, HIGH_SCORE, EMAIL FROM TriviaUsers";
			PreparedStatement ps = conn.prepareCall(sql);
			
			List<User> allEmployees = new ArrayList<>();
			
			ResultSet results = ps.executeQuery();
			
			while (results.next()) {
				allEmployees.add(new User(
					results.getString("USERNAME"), 
					results.getInt("EXPERIENCE"),
					results.getInt("HIGH_SCORE"),
					results.getString("EMAIL")));	
			}
				conn.close();
				return allEmployees;
				
		}catch( SQLException e) {
			e.getStackTrace();
		}
		return null;
	}

	@Override
	public int calculateRank(int highScore) throws FileNotFoundException, SQLException {
		//Need to know how rank is calculated
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
		String sql = "SELECT COUNT(*)+1 FROM TriviaUsers WHERE high_score > ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, highScore);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			return rs.getInt(1);
		}
		}
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object viewLeaderboard() {
		return null;
		// TODO Auto-generated method stub
		
	}

}
