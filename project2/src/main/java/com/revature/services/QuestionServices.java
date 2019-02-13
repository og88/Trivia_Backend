package com.revature.services;

import java.io.FileNotFoundException;
import java.util.List;

import com.revature.DAO.QuestionDAOImplementation;
import com.revature.models.Question;

public class QuestionServices {

	private static QuestionServices questionServices;
	private QuestionServices() {
	}

	public static QuestionServices getUserServices() {
		if (questionServices == null) {
			questionServices = new QuestionServices();
		}
		return questionServices;
	}
	
	boolean insertQuestion(Question question) {
		return QuestionDAOImplementation.getQuestionDAO().insertQuestion(question);
	}
	
	boolean updateQuestion(int questionID, Question question) {
		return QuestionDAOImplementation.getQuestionDAO().updateQuestion(questionID, question);
	}
	
	boolean updateCounters(int questionID, int correctCount, int incorrectCount) throws FileNotFoundException {
		return QuestionDAOImplementation.getQuestionDAO().updateCounters(questionID, correctCount, incorrectCount);
	}
	
	Question getQuestion(int questionID) {
		return QuestionDAOImplementation.getQuestionDAO().getQuestion(questionID);
	}
	
	List<Question> getQuestionsByCategory(String questionCategory) {
		return QuestionDAOImplementation.getQuestionDAO().getQuestionsByCategory(questionCategory);
	}
	
	void viewStatistics(int questionID) {
		QuestionDAOImplementation.getQuestionDAO().viewStatistics(questionID);
	}
}
