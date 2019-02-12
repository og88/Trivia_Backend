package com.project2.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloWorld extends HttpServlet {

	private final ObjectMapper mapper = new ObjectMapper();
	final static Logger log = Logger.getLogger(HelloWorld.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("Hello World");
		resp.setContentType("json");

		resp.setStatus(200);
		resp.getWriter().append(mapper.writeValueAsString("Hello World, This is a test to see if CI works"));
	}

}
