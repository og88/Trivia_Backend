package com.revature.DAO;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Question;

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
		return false;
	}
	
	@Override
	public boolean updateQuestion(int questionID, Question question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCounters(int questionID, int correctCount, int incorrectCount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Question getQuestion(int questionID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Question> getQuestionsByCategory(String questionCategory) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void viewStatistics(int questionID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verifyAnswer(String givenAnswer, String correctAnswer) {
		if (givenAnswer == ""){
			return false;
		}
		
		
		
		givenAnswer = givenAnswer.toLowerCase().trim();
		correctAnswer = correctAnswer.toLowerCase().trim();
		
		givenAnswer = givenAnswer.replace("the ","");
		givenAnswer = givenAnswer.replace("a ","");
		givenAnswer = givenAnswer.replace("an ","");
		givenAnswer = givenAnswer.replaceAll(" ", "");
		givenAnswer = givenAnswer.replaceAll("'", "");
		
		correctAnswer = correctAnswer.replace("the ","");
		correctAnswer = correctAnswer.replace("a ","");
		correctAnswer = correctAnswer.replace("an ","");
		correctAnswer = correctAnswer.replaceAll(" ", "");
		correctAnswer = correctAnswer.replaceAll("'", "");
		
		if (givenAnswer.equals(correctAnswer)) {
			return true;
		}
		return false;
	}

}
