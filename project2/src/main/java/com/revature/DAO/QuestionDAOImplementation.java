package com.revature.DAO;

import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.models.Question;
import com.revature.util.JDBCconnectionUtil;

public class QuestionDAOImplementation implements QuestionDAO {

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
	public boolean insertQuestion(Question question) throws FileNotFoundException, SQLException {
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "CALL INSERT_QUESTION(?,?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, question.getQuestionID());
			cs.setString(2, question.getQuestion());
			cs.setString(3, question.getQuestionCategory());
			cs.setInt(4, question.getCorrectCount());
			cs.setInt(5, question.getIncorrectCount());
			cs.setInt(6, question.getDifficulty());
			cs.registerOutParameter(7, Types.NUMERIC);
			cs.execute();
			int result = cs.getInt(7);
			if (result > 0) {
				log.info("Question inserted successfuly");
				return true;
			} else {
				return false;
			}
		}

	}

	@Override
	public boolean updateQuestion(int questionID, Question question) {
		// TODO Auto-generated method stub
		// what are we updating??
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
			ps.setInt(1, questionID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				correct = rs.getInt("CORRECT_COUNT");
				incorrect = rs.getInt("INCORRECT_COUNT");
			}

			if (correctCount > 0) { // answered correctly
				update = "UPDATE Questions SET CORRECT_COUNT = (?) WHERE QUESTION_ID = (?)";
				ps = conn.prepareStatement(update);
				correct += 1;
				ps.setInt(1, correct);
				ps.setInt(2, questionID);

				if (ps.executeUpdate() > 0) {
					conn.close();
					return true;
				} else {
					conn.close();
					throw new SQLException();
				}
			} else if (incorrectCount > 0) { // answered incorrectly
				update = "UPDATE Questions SET INCORRECT_COUNT = (?) WHERE QUESTION_ID = (?)";
				ps = conn.prepareStatement(update);
				ps.close();
				incorrect += 1;
				ps.setInt(1, incorrect);
				ps.setInt(2, questionID);

				if (ps.executeUpdate() > 0) {
					conn.close();
					return true;
				} else {
					conn.close();
					throw new SQLException();
				}
			} else {
				return true; // Question skipped, no correct or incorrect updates
			}

		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public Question getQuestion(int questionID) {
		// Attempt to get a connection:

		try (Connection conn = JDBCconnectionUtil.getConnection()) {

			// Get question from database:
			
			String sql = "SELECT * FROM Questions WHERE QUESTION_ID = (?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, questionID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				// Return question:
				
				return new Question(
						rs.getInt("QUESTION_ID"),
						rs.getString("QUESTION"),
						rs.getString("QUESTION_CATEGORY"),
						rs.getInt("CORRECT_COUNT"),
						rs.getInt("INCORRECT_COUNT"),
						rs.getInt("DIFFICULTY"));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return null;
	}

	@Override
	public List<Question> getQuestions() {

		// Attempt to get a connection:

		try (Connection conn = JDBCconnectionUtil.getConnection()) {

			// Send query to database:

			Statement stmt = conn.createStatement();
			String sql = "select * from Questions";
			ResultSet rs = stmt.executeQuery(sql);

			// Generate a list of questions:

			List<Question> questions = new ArrayList<>();
			while (rs.next()) {
				questions.add(new Question(
						rs.getInt("QUESTION_ID"),
						rs.getString("QUESTION"),
						rs.getString("QUESTION_CATEGORY"),
						rs.getInt("CORRECT_COUNT"),
						rs.getInt("INCORRECT_COUNT"),
						rs.getInt("DIFFICULTY"))
						);
			}
			return questions;
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return null;
	}

	@Override
	public Object viewStatistics(int questionID) {
		return questionID;
		// TODO Auto-generated method stub
		// ???

	}

	@Override
	public boolean verifyAnswer(String givenAnswer, String correctAnswer) {
		if (givenAnswer == "") {
			return false;
		}

		givenAnswer = givenAnswer.toLowerCase().trim();
		correctAnswer = correctAnswer.toLowerCase().trim();

		givenAnswer = givenAnswer.replace("the ", "");
		givenAnswer = givenAnswer.replace("a ", "");
		givenAnswer = givenAnswer.replace("an ", "");
		givenAnswer = givenAnswer.replaceAll(" ", "");
		givenAnswer = givenAnswer.replaceAll("'", "");

		correctAnswer = correctAnswer.replace("the ", "");
		correctAnswer = correctAnswer.replace("a ", "");
		correctAnswer = correctAnswer.replace("an ", "");
		correctAnswer = correctAnswer.replaceAll(" ", "");
		correctAnswer = correctAnswer.replaceAll("'", "");

		if (givenAnswer.equals(correctAnswer)) {
			return true;
		}
		return false;
	}

}
