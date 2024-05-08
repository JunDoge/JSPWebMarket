package config;

import java.io.IOException;

import db.PrdDb;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewResolver {
	public static void mainforward(String view, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setAttribute("view", view);
		request.setAttribute("cats", PrdDb.selCat());
		String jsp = String.format("/WEB-INF/%s.jsp", "main");
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	public static void commonforward(String view, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String jsp = String.format("/WEB-INF/%s.jsp", view);
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	public static void adminforward(String view, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setAttribute("view", view);
		request.setAttribute("cats", PrdDb.selCat());
		String jsp = String.format("/WEB-INF/%s.jsp", "admin");
		request.getRequestDispatcher(jsp).forward(request, response);
	}
}
