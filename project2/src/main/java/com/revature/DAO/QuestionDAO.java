package com.revature.DAO;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Question;

public interface QuestionDAO {

	boolean insertQuestion(Question question) throws FileNotFoundException, SQLException;
	boolean updateQuestion(int questionID, Question question);
	boolean updateCounters(int questionID, int correctCount, int incorrectCount) throws FileNotFoundException;
	Question getQuestion(int questionID);
	List<Question> getQuestions();
	Object viewStatistics(int questionID);
	boolean verifyAnswer(String givenAnswer, String correctAnswer);
	
}
