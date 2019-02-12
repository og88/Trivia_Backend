package com.revature.models;

public class Question {
	
	private int questionID;
	private String question;
	private String questionCategory;
	private int correctCount;
	private int incorrectCount;
	private int difficulty;
	
	public Question() {
		super();
	}

	public Question(int questionID, String question, String questionCategory, int correctCount, int incorrectCount,
			int difficulty) {
		super();
		this.questionID = questionID;
		this.question = question;
		this.questionCategory = questionCategory;
		this.correctCount = correctCount;
		this.incorrectCount = incorrectCount;
		this.difficulty = difficulty;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}

	public int getCorrectCount() {
		return correctCount;
	}

	public void setCorrectCount(int correctCount) {
		this.correctCount = correctCount;
	}

	public int getIncorrectCount() {
		return incorrectCount;
	}

	public void setIncorrectCount(int incorrectCount) {
		this.incorrectCount = incorrectCount;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public String toString() {
		return "Question [questionID=" + questionID + ", question=" + question + ", questionCategory="
				+ questionCategory + ", correctCount=" + correctCount + ", incorrectCount=" + incorrectCount
				+ ", difficulty=" + difficulty + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + correctCount;
		result = prime * result + difficulty;
		result = prime * result + incorrectCount;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((questionCategory == null) ? 0 : questionCategory.hashCode());
		result = prime * result + questionID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (correctCount != other.correctCount)
			return false;
		if (difficulty != other.difficulty)
			return false;
		if (incorrectCount != other.incorrectCount)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (questionCategory == null) {
			if (other.questionCategory != null)
				return false;
		} else if (!questionCategory.equals(other.questionCategory))
			return false;
		if (questionID != other.questionID)
			return false;
		return true;
	}

}
