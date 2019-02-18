package com.revature.DAO;

import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.customExceptions.UserNotFoundException;
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
	public User getUser(String username) throws SQLException {
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "SELECT USERNAME, PASS, EXPERIENCE, HIGH_SCORE FROM TriviaUsers WHERE USERNAME = ?";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setString(1, username);
			
			ResultSet results = ps.executeQuery();
			while (results.next()) {
				return new User(
					results.getString("USERNAME"),
					results.getString("PASS"),
					results.getInt("EXPERIENCE"),
					results.getInt("HIGH_SCORE"));
			}
			conn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
				
		}catch(FileNotFoundException | SQLException e) {
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
	public void viewLeaderboard() throws FileNotFoundException, SQLException {
		try(Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "SELECT * FROM TriviaUsers ORDER BY HIGH_SCORE";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<User> scores = new ArrayList<>();
			while(rs.next()) {
				for (int i=0; i<5; i++) {
					scores.add(new User(
						rs.getString("USERNAME"),
						rs.getInt("HIGH_SCORE")));
				}
			}
		}
	}

	public User authenticateUser(String username, String password) throws UserNotFoundException, FileNotFoundException, SQLException {
		try(Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "SELECT PASS FROM TriviaUsers WHERE USERNAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			String sql2 = "? = CALL GET_USER_HASH(?,?)";
			CallableStatement cs = conn.prepareCall(sql2);
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			cs.setString(2,  username);
			cs.setString(3,  password);
			cs.execute();
			
			String vPass = cs.getString(1);
			String cPass = "";
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()) {
				cPass = rs.getString("PASS");
			}
			
			if(cPass == vPass) {
				return getUser(username);
			}
			else {
				throw new UserNotFoundException("Incorrect username or password");
			}
		}
	}

}
