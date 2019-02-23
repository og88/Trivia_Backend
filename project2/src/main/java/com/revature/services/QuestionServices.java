package com.revature.services;

import java.io.FileNotFoundException;
import java.sql.SQLException;
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
<<<<<<< HEAD
		return QuestionDAOImplementation.getQuestionDAO().insertQuestion(question);
=======
		try {
			return QuestionDAOImplementation.getQuestionDAO().insertQuestion(question);
		} catch (FileNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
>>>>>>> c7ae387a280f8a0f350e5e2cede4006b7baf3241
	}
	
	public boolean updateQuestion(int questionID, Question question) {
		return QuestionDAOImplementation.getQuestionDAO().updateQuestion(questionID, question);
	}
	

	public boolean updateCounters(Question[] questions) throws FileNotFoundException {
		boolean resp = false;
		for(Question question : questions) {
			resp = QuestionDAOImplementation.getQuestionDAO().updateCounters(question);
		}
		return resp;
	}
	
	public Question getQuestion(int questionID) {
		return QuestionDAOImplementation.getQuestionDAO().getQuestion(questionID);
	}
	
	public List<Question> getQuestionsByCategory(String questionCategory) {
		return QuestionDAOImplementation.getQuestionDAO().getQuestionsByCategory(questionCategory);
	}
	
	public Object viewStatistics(int questionID) {
		return QuestionDAOImplementation.getQuestionDAO().viewStatistics(questionID);
	}
	
	public boolean verifyAnswer(String givenAnswer, String correctAnswer) {
		return QuestionDAOImplementation.getQuestionDAO().verifyAnswer(givenAnswer, correctAnswer);
	}
	
	public List<Question> getQuestions() {
		return QuestionDAOImplementation.getQuestionDAO().getQuestions();
	}
	
}
