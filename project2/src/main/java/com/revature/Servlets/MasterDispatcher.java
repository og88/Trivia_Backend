package com.revature.Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterDispatcher {

	
	private MasterDispatcher() {
	}

	public static Object process(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		if (req.getRequestURI().contains("login")) {
			return new String("Hello, World!");
		}

		return null;
	}
}
