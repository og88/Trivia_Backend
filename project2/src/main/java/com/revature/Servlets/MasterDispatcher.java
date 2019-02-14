package com.revature.Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterDispatcher {

	private MasterDispatcher() {
	}

	public static Object process(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		if (req.getRequestURI().contains("login")) {
			return new String("Hello, World!");
		} else if (req.getRequestURI().contains("rank/calculate")) {
			return new String("Calculating rank");
		} else if (req.getRequestURI().contains("leader")) {
			return new String("Getting leaderboard");
		} else if (req.getRequestURI().contains("user")) {
			return Users(req, resp);
		} else if (req.getRequestURI().contains("question")) {
			return Questions(req, resp);
		}

		return null;
	}

	public static Object Users(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getRequestURI().contains("user/register")) {
			return new StringBuffer("register a new user");
		} else if (req.getRequestURI().contains("user/update")) {
			return new StringBuffer("updating User");
		} else if (req.getRequestURI().contains("score/update")) {
			return new StringBuffer("Updating String");
		} else if (req.getRequestURI().contains("user/get")) {
			return new StringBuffer("Get a specific User");
		} else if (req.getRequestURI().contains("user/all")) {
			return new StringBuffer("Getting all users");
		}
		return null;
	}

	public static Object Questions(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getRequestURI().contains("question/insert")) {
			return new StringBuffer("Inserting a question");
		} else if (req.getRequestURI().contains("question/update")) {
			return new StringBuffer("Updating a question");
		} else if (req.getRequestURI().contains("question/counter")) {
			return new StringBuffer("Updating question counter");
		} else if (req.getRequestURI().contains("question/get")) {
			return new StringBuilder("Getting a specific question");
		} else if (req.getRequestURI().contains("question/getCat")) {
			return new StringBuilder("Getting questions by category");
		} else if (req.getRequestURI().contains("stats")) {
			return new StringBuilder("Getting Statistics");
		}
		return null;
	}
}
