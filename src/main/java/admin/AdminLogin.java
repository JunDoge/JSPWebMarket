package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import config.Utils;
import config.ViewResolver;

/**
 * Servlet implementation class AdminLogin
 */
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ViewResolver.commonforward("/admin/login", request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			if (Utils.isAdmin(request)) {
				HttpSession session = request.getSession();
				session.setAttribute("id", request.getParameter("id"));
				response.sendRedirect("/admin/manager");
				
			}else {
				response.sendRedirect("/admin/login");
			}
			
		
		
	}

}
