package com.revature.DAO;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Question;
import com.revature.util.JDBCconnectionUtil;

public class QuestionDAOImplementation implements QuestionDAO{

	private static QuestionDAOImplementation questionDAO;
	final static Logger log = Logger.getLogger(UserDAOImplementation.class);
	private QuestionDAOImplementation() {
	}
	
	public static QuestionDAOImplementation getQuestionDAO() {
		if (questionDAO == null) {
			questionDAO = new QuestionDAOImplementation();
		}
		return questionDAO;
	}
	
	@Override
	public boolean insertQuestion(Question question) {
		// TODO Auto-generated method stub
		//For inserting questions as they used.  new questions to the DB only.
		return false;
	}
	
	@Override
	public boolean updateQuestion(int questionID, Question question) {
		// TODO Auto-generated method stub
		//what are we updating??
		return false;
	}

	@Override
	public boolean updateCounters(int questionID, int correctCount, int incorrectCount) throws FileNotFoundException {
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "SELECT CORRECT_COUNT, INCORRECT_COUNT FROM Questions WHERE QUESTION_ID = (?)";
			String update;
			int correct = 0;
			int incorrect = 0;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  questionID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				correct = rs.getInt("CORRECT_COUNT");
				incorrect = rs.getInt("INCORRECT_COUNT");
			}
			
			if(correctCount>0) {  //answered correctly
				update = "UPDATE Questions SET CORRECT_COUNT = (?) WHERE QUESTION_ID = (?)";
				ps = conn.prepareStatement(update);
				correct+=1;
				ps.setInt(1, correct);
				ps.setInt(2, questionID);
				
					if(ps.executeUpdate() > 0) {
						conn.close();
						return true;
					} 
					else {
						conn.close();
						throw new SQLException();
					}
			}else if (incorrectCount>0) {  //answered incorrectly
				update = "UPDATE Questions SET INCORRECT_COUNT = (?) WHERE QUESTION_ID = (?)";
				ps = conn.prepareStatement(update);
				ps.close();
				incorrect+=1;
				ps.setInt(1, incorrect);
				ps.setInt(2, questionID);
				
					if(ps.executeUpdate() > 0) {
						conn.close();
						return true;
					} 
					else {
						conn.close();
						throw new SQLException();
					}
			}else {
				return true; //Question skipped, no correct or incorrect updates
			}
			
		}catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public Question getQuestion(int questionID) {
		// TODO Auto-generated method stub
		//what exactly is is this used for?
		return null;
	}
	
	@Override
	public List<Question> getQuestionsByCategory(String questionCategory) {
		// TODO Auto-generated method stub
		//what exactly is this used for?
		return null;
	}
	
	@Override
	public void viewStatistics(int questionID) {
		// TODO Auto-generated method stub
		//???
		
	}

}
