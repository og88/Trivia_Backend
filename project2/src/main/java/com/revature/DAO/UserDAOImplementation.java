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

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.revature.customExceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.UserServices;
import com.revature.util.JDBCconnectionUtil;

public class UserDAOImplementation implements UserDAO {

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
<<<<<<< HEAD
	public User registerUser(User user) {
=======
	public User registerUser(User user) throws FileNotFoundException {
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
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
<<<<<<< HEAD
				log.info("Registration Success");	
				return UserServices.getUserServices().getUser(user);
			} 
			else {
				log.info("Registration Failure");	
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			log.info(e);
=======
				log.info("Registration Success");
				conn.close();
				return UserServices.getUserServices().getUser(user);
			} 
			else {
				log.info("Registration Failure");
				conn.close();
				throw new SQLException();
			}
		}
		catch (SQLException e) {
			e.getStackTrace();
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
		}
		
		return new User();
	}

	@Override  
<<<<<<< HEAD
	public boolean updateUser(User user) { 
=======
	public boolean updateUser(User user) {
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
		User updatedUser = user;
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			log.info("in update");
			System.out.println(updatedUser);
			System.out.println(updatedUser.getUsername());
			System.out.println(updatedUser.getPassword());
			//hashing password again to match new username/password combo
			String hash = "{ ? = call GET_USER_HASH(?,?) }";
			CallableStatement cs = conn.prepareCall(hash);
			cs.setString(2,  updatedUser.getUsername());
			cs.setString(3,  updatedUser.getPassword());
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
				log.info("before execute");
			cs.execute();
				log.info("after execute");
			updatedUser.setPassword(cs.getString(1));
<<<<<<< HEAD
=======
			System.out.println("updatedUser.getPassword()");
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
			
			String sql = "UPDATE TriviaUsers SET USERNAME = (?), PASS = (?), EMAIL = (?) WHERE USERNAME = (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, updatedUser.getUsername());
			ps.setString(2, updatedUser.getPassword());
			ps.setString(3, updatedUser.getEmail());
			ps.setString(4, updatedUser.getTempUsername());
			
				if(ps.executeUpdate() > 0) {
<<<<<<< HEAD
					return true;
				} 
				else {
=======
					conn.close();
					return true;
				} 
				else {
					conn.close();
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
					throw new SQLException();
				}
		}
		catch (SQLException e) {
<<<<<<< HEAD
			e.printStackTrace();
			log.info(e);
=======
			e.getStackTrace();
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
		}
		return false;
	}

	
	@Override
<<<<<<< HEAD
	public User getUser(User user) {
		log.info("Login attempt");
		System.out.println(user.toString());
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
=======
	public User getUser(User user) throws SQLException, UserNotFoundException {
		//Is there a method to validate users? (Username/Password)
		//Or are we just getting them by username?
		log.info("Login attempt");
		System.out.println(user.toString());
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
		//try(Connection conn = dataSource.getConnection()){	
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
		String sql = "SELECT USERNAME, EXPERIENCE, HIGH_SCORE, EMAIL FROM TriviaUsers WHERE USERNAME = ? AND PASS = GET_USER_HASH(?,?)";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			
			
			ResultSet results = ps.executeQuery();
			while (results.next()) {
				log.info("found");
				return new User(
					results.getString("USERNAME"),
					results.getInt("EXPERIENCE"),
					results.getInt("HIGH_SCORE"),
					results.getString("EMAIL"));
			}
			log.warn("User not Found");
<<<<<<< HEAD
			
		}catch (SQLException e) {
			e.printStackTrace();
			log.info(e);
		}
		return null;
=======
			throw new UserNotFoundException("Not found");
		}
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
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
<<<<<<< HEAD
			e.printStackTrace();
			log.info(e);
=======
			e.getStackTrace();
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
		}
		return null;
	}

	@Override
<<<<<<< HEAD
	public int calculateRank(int highScore) {
		
=======
	public int calculateRank(int highScore) throws FileNotFoundException, SQLException {
		//Need to know how rank is calculated
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
		String sql = "SELECT COUNT(*)+1 FROM TriviaUsers WHERE high_score > ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, highScore);
		ResultSet rs = ps.executeQuery();
<<<<<<< HEAD
		
			while(rs.next()) {
				return rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			log.info(e);
=======
		while(rs.next()) {
			return rs.getInt(1);
		}
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
		}
		return 0;
	}

	@Override
<<<<<<< HEAD
	public Object viewLeaderboard() {
=======
	public Object viewLeaderboard() throws FileNotFoundException, SQLException {
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
		try(Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "SELECT * from TriviaUsers ORDER BY high_score DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<User> scores = new ArrayList<>();
			while(rs.next()) {
					scores.add(new User(
						rs.getString("USERNAME"),
						rs.getInt("EXPERIENCE"),
						rs.getInt("HIGH_SCORE"),
						rs.getString("EMAIL")));
			}
			return scores;
<<<<<<< HEAD
		}catch (SQLException e) {
			e.printStackTrace();
			log.info(e);
		}
		return null;
	}

	@Override
	public User updateScore(User user) {
=======
		}
	}

	@Override
	public User updateScore(User user) throws SQLException, UserNotFoundException {
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
		try(Connection conn = JDBCconnectionUtil.getConnection()) {
		String sql = "CALL UPDATE_SCORE(?,?,?)";
		CallableStatement cs = conn.prepareCall(sql);
		cs.setString(1, user.getUsername());
		cs.setInt(2, user.getExperience());
		cs.setInt(3, user.getHighScore());
		cs.execute();
<<<<<<< HEAD
		return new User();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
=======
		return UserDAOImplementation.getUserDAO().getUser(user);
		}
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
	}
}
