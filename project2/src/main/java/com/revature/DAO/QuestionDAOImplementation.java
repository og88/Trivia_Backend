package com.revature.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

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
	public boolean insertQuestion(Question question) {
		System.out.println(question.toString());
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
			}
		} catch (SQLException e) {
			log.info(e);
		}
		return false;
	}

	@Override
	public boolean updateQuestion(int questionID, Question question) {
		// TODO Auto-generated method stub
		// what are we updating??
		return false;
	}

	@Override
	public boolean updateCounters(Question question) {
		System.out.println(question.toString());
		try (Connection conn = JDBCconnectionUtil.getConnection()) {
			String sql = "CALL UPDATE_COUNT(?, ?,?,?,?,?)";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setInt(1, question.getQuestionID());
			ps.setString(2, question.getQuestion());
			ps.setString(3, question.getQuestionCategory());
			ps.setInt(4, question.getCorrectCount());
			ps.setInt(5,question.getIncorrectCount());
			ps.setInt(6, question.getDifficulty());
			ps.execute();


		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public Question getQuestion(int questionID) {
		// TODO Auto-generated method stub
		// what exactly is is this used for?
		return null;
	}

	@Override
	public List<Question> getQuestionsByCategory(String questionCategory) {
		// TODO Auto-generated method stub
		// what exactly is this used for?
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
