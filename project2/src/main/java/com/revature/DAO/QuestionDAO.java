package com.revature.DAO;

import java.util.List;

import com.revature.models.Question;

public interface QuestionDAO {

	boolean insertQuestion(Question question);
	boolean updateQuestion(int questionID, Question question);
	boolean updateCounters(int questionID, int correctCount, int incorrectCount);
	Question getQuestion(int questionID);
	List<Question> getQuestionsByCategory(String questionCategory);
	void viewStatistics(int questionID);
	
}
