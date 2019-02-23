package com.revature.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.services.QuestionServices;

class project2Tests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	// Test answer verification
	
	@Test
	void testANormalAnswer() {
		assertTrue(QuestionServices.getQuestionServices().verifyAnswer("answer","answer"));
	}
	
	@Test
	void testAWrongAnswer() {
		assertFalse(QuestionServices.getQuestionServices().verifyAnswer("wrongAnswer","answer"));
	}
	
	@Test
	void testAnswerMissingThe() {
		assertTrue(QuestionServices.getQuestionServices().verifyAnswer("answer","the answer"));
	}
	
	@Test
	void testAnswerMissingAn() {
		assertTrue(QuestionServices.getQuestionServices().verifyAnswer("answer","an answer"));
	}
	
	@Test
	void testAnswerMissingA() {
		assertTrue(QuestionServices.getQuestionServices().verifyAnswer("good answer","A good answer"));
	}
	
	@Test
	void testAnswerWithDifferentCapitalization() {
		assertTrue(QuestionServices.getQuestionServices().verifyAnswer("aNsWeR","AnSwEr"));
	}
	
	@Test
	void testLongAnswer() {
		assertTrue(QuestionServices.getQuestionServices().verifyAnswer("this is a very long answer with a lot of words","this is a very long answer with a lot of words"));
	}
	
	@Test
	void testBlankAnswer() {
		assertFalse(QuestionServices.getQuestionServices().verifyAnswer("","answer"));
	}
	
	@Test
	void testOnlyArticles() {
		assertTrue(QuestionServices.getQuestionServices().verifyAnswer("a an the an the a a a a","a an the an the a a a a"));
	}
	
	@Test
	void testArticlesInTheMiddleOfTheWordsDoNotGetRemoved() {
		assertFalse(QuestionServices.getQuestionServices().verifyAnswer("ether annode axe","eer node xe"));
	}
	
	@Test
	void testAnswerWithSpacesAtBeginningAndEnd() {
		assertTrue(QuestionServices.getQuestionServices().verifyAnswer(" good answer, a ","good answer, a"));
	}
	
	@Test
	void testAnswerWithExtraSpaces() {
		assertTrue(QuestionServices.getQuestionServices().verifyAnswer("good            answer","good answer"));
	}
	
	@Test
	void testAnswerWithApostrophes() {
		assertTrue(QuestionServices.getQuestionServices().verifyAnswer("answer's","answers"));
	}
	
	@Test
	void testWhatAreWeSavages() {
		assertFalse(QuestionServices.getQuestionServices().verifyAnswer("there","their"));
	}
	
}
