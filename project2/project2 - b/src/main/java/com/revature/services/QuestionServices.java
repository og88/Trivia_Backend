package com.revature.services;

import java.util.List;

import com.revature.DAO.QuestionDAOImplementation;
import com.revature.models.Question;

public class QuestionServices {

	private static QuestionServices questionServices;
	private QuestionServices() {
	}

	public static QuestionServices getQuestionServices() {
		if (questionServices == null) {
			questionServices = new QuestionServices();
		}
		return questionServices;
	}
	
	public boolean insertQuestion(Question question) {
		return QuestionDAOImplementation.getQuestionDAO().insertQuestion(question);
	}
	
	public boolean updateQuestion(int questionID, Question question) {
		return QuestionDAOImplementation.getQuestionDAO().updateQuestion(questionID, question);
	}
	
	public boolean updateCounters(int questionID, int correctCount, int incorrectCount) {
		return QuestionDAOImplementation.getQuestionDAO().updateCounters(questionID, correctCount, incorrectCount);
	}
	
	public Question getQuestion(int questionID) {
		return QuestionDAOImplementation.getQuestionDAO().getQuestion(questionID);
	}
	
	public List<Question> getQuestionsByCategory(String questionCategory) {
		return QuestionDAOImplementation.getQuestionDAO().getQuestionsByCategory(questionCategory);
	}
	
	public void viewStatistics(int questionID) {
		QuestionDAOImplementation.getQuestionDAO().viewStatistics(questionID);
	}
	
	public boolean verifyAnswer(String givenAnswer, String correctAnswer) {
		return QuestionDAOImplementation.getQuestionDAO().verifyAnswer(givenAnswer, correctAnswer);
	}
	
	
}
