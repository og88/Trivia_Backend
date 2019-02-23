package com.revature.Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Question;
import com.revature.models.User;
import com.revature.services.QuestionServices;
import com.revature.services.UserServices;

public class MasterDispatcher {

	private final static ObjectMapper mapper = new ObjectMapper();
	final static Logger log = Logger.getLogger(MasterServlet.class);

	private MasterDispatcher() {
	}

	public static Object process(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		System.out.println("in dispatch " + req.getRequestURI().toString());
		if (req.getRequestURI().contains("login")) {
			User user = mapper.readValue(req.getReader(), User.class);
			return UserServices.getUserServices().getUser(user);
		} else if (req.getRequestURI().contains("rank/calculate")) {// WORKS
																	// ************************************************************
			User user = mapper.readValue(req.getReader(), User.class);
			return UserServices.getUserServices().calculateRank(user.getHighScore());
		} else if (req.getRequestURI().contains("leader")) { ///////////////////////////////////////////////////////////
			return UserServices.getUserServices().viewLeaderboard();
		}  else if (req.getRequestURI().contains("questions")) { ///////////////////////////////////////////////////////////
			return QuestionServices.getQuestionServices().getQuestions();
		} else if (req.getRequestURI().contains("user")) {
			return Users(req, resp);
		} else if (req.getRequestURI().contains("question")) {
			return Questions(req, resp);
		}

		return null;
	}

	public static Object Users(HttpServletRequest req, HttpServletResponse resp)
			throws JsonParseException, JsonMappingException, IOException, SQLException {
		if (req.getRequestURI().contains("user/register")) {// WORKS ***********************************************
			log.info("Registering new user");
			User user = mapper.readValue(req.getReader(), User.class);
			return UserServices.getUserServices().registerUser(user);
		} else if (req.getRequestURI().contains("user/update")) {////////////////////////////////
			User user = mapper.readValue(req.getReader(), User.class);
			return UserServices.getUserServices().updateUser(user); 
		} else if (req.getRequestURI().contains("user/score/update")) {
			User user = mapper.readValue(req.getReader(), User.class);
			return UserServices.getUserServices().updateScore(user);
		} else if (req.getRequestURI().contains("user/get")) {
			User user = mapper.readValue(req.getReader(), User.class);
			return UserServices.getUserServices().getUser(user);
		} else if (req.getRequestURI().contains("user/all")) { // WORKS
																// **************************************************************
			log.info("Getting all users");
			return UserServices.getUserServices().getAllUsers();
		}
		return null;
	}

	public static Object Questions(HttpServletRequest req, HttpServletResponse resp)
			throws JsonParseException, JsonMappingException, IOException {
		if (req.getRequestURI().contains("question/insert")) { ///////////////////////////////////////////////////
			Question question = mapper.readValue(req.getReader(), Question.class);
			return QuestionServices.getQuestionServices().insertQuestion(question);
		} else if (req.getRequestURI().contains("question/update")) { ///////////////////////////////////
			Question question = mapper.readValue(req.getReader(), Question.class);
			return new StringBuffer("Updating a question");
		} else if (req.getRequestURI().contains("question/counter")) { ////////////////////////////////////////
			Question[] question = mapper.readValue(req.getReader(), Question[].class);
			return QuestionServices.getQuestionServices().updateCounters(question);
		} else if (req.getRequestURI().contains("question/get")) { ///////////////////////////////////////////////
			Question question = mapper.readValue(req.getReader(), Question.class);
			return QuestionServices.getQuestionServices().getQuestion(question.getQuestionID());
		} else if (req.getRequestURI().contains("question/Cat")) { ///////////////////////////////////////////////
			Question question = mapper.readValue(req.getReader(), Question.class);
			return QuestionServices.getQuestionServices().getQuestionsByCategory(question.getQuestionCategory());
		} else if (req.getRequestURI().contains("stats")) { ///////////////////////////////////////////////////////////
			Question question = mapper.readValue(req.getReader(), Question.class);
			return QuestionServices.getQuestionServices().viewStatistics(question.getQuestionID());
		}
		return null;
	}
}
